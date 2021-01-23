package verwaltung;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;
import exceptions.LogInException;
import exceptions.RegisterException;
import gui.FensterManager;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import verwaltung.verwaltungen.Listenverwaltung;

public class Nutzer extends DB_Manager {
	
	private static Nutzer instance;
	public static Nutzer getNutzer() {
		if(instance == null) instance = new Nutzer();
		return instance;
	}

	private int id;
	private String name;
	private Rechte rechte = new Rechte();
	private ReadOnlyBooleanWrapper angemeldet = new ReadOnlyBooleanWrapper(false);

	private static boolean logingIn = false;
	
	private Nutzer() {};
	
	private void setNutzer(ResultSet rs) throws SQLException{
		id = rs.getInt("id");
		name = rs.getString("name");
		rechte.setRechte(rs.getString("berechtigung"), rs.getBoolean("read"), rs.getBoolean("add"), rs.getBoolean("update"), rs.getBoolean("updateAll"), rs.getBoolean("multiLogin"), rs.getBoolean("reviewRead"), rs.getBoolean("reviewWrite"), rs.getBoolean("reviewWriteAll"));
	
		System.out.println(rechte.toString());
	}
	
	public static void anmeldenGast() throws SQLException, LogInException {
		anmeldenKonto("Gast", "NurEinGast");
	}
	
	public static void anmeldenKonto(String name, String passwort) throws SQLException, LogInException {	
		if(logingIn)	return;
		try(Connection con = con()){
			anmeldenKonto(name, passwort, con);
		}
	}	
	private static void anmeldenKonto(String name, String passwort, Connection con) throws SQLException, LogInException {	
		if(logingIn)	return;
		logingIn = true;
		
		try(Statement st = con.createStatement()){			
			//Existiert Nutzer?
			if(!nameExistiert(con, name)) throw new LogInException("Der Nutzer: '"+name+"' existiert nicht", LogInException.NO_USER);
						
			boolean multilogin;
			//Stimmt das Passwort?
			String sql =  "Select nutzer.id, passwort, registrierungsDatum, multilogin from nutzer join rechte on rechte.id=rechte where name=?";
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setString(1, name);
				try(ResultSet rs = ps.executeQuery()){
					rs.next();
					
					String pwhash = rs.getString("passwort");
					String pass = (passwort+name+rs.getString("registrierungsDatum"));
					if(pass.length()>71) pass = pass.substring(0,71);
					
					Result verifyResult = BCrypt.verifyer().verify( pass.toCharArray(), pwhash.toCharArray());
					if(!verifyResult.verified)	throw new LogInException("Das verwendete Passwort ist falsch", LogInException.WRONG_PASSWORD);
					
					getNutzer().id = rs.getInt("id");
					multilogin = rs.getBoolean("multilogin");
				}
			}
			

			if(!multilogin) {
				try(PreparedStatement ps = con.prepareStatement("Select * from angemeldet where nid=?")){
					ps.setInt(1, instance.id);
					try(ResultSet rs = ps.executeQuery()){
						if(rs.next()) throw new LogInException("Sie sind bereits in einer anderen Instanz angemeldet", LogInException.ALREADY_LOGGED_IN);
					}
				}
				
				int random;
				while(true) {
					random = (int)(Math.random()*Integer.MAX_VALUE);
					try(PreparedStatement ps = con.prepareStatement("Select * from angemeldet where iid=?")){
						ps.setInt(1, random);
						try(ResultSet rs = ps.executeQuery()){
							if(!rs.next())	break;
						}
					}
				}
				//Anmelden
				sql = "Insert into angemeldet(nid, iid) values(?, ?);";
				try(PreparedStatement ps = con.prepareStatement(sql)){
					ps.setInt(1, instance.getId());
					ps.setInt(2, random);
					ps.executeUpdate();
				}
				DB_Manager.setApplikationsId(random);
			}
			
			//Hole Daten	
			instance = getNutzer();	
			sql =  "Select nutzer.id, name, rechte.* from Nutzer "
								+ "inner join rechte on nutzer.rechte=rechte.id where name=?"; 
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setString(1, name);
				try(ResultSet rs = ps.executeQuery()){
					rs.next();
					instance.setNutzer(rs);
				}
			}	
			instance.angemeldet.set(true);
			Thread th = new Thread(()->{
				try (Connection conn = DB_Manager.con()){
					Listenverwaltung.instance().ladeListen(conn);
					FensterManager.logErreignis("\nSie haben sich Erfolgreich mit dem Konto "+instance.getName()+" angemeldet", Color.GREEN);	
					instance.getRechte().log();
					Platform.runLater(()->{
						FensterManager.setPrimaryStage(FensterManager.getHauptSeite());
						logingIn = false;
					});
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
			th.start();
		}
	}
	
	public static void registrieren(String name, String passwort, String passwort1) throws SQLException, RegisterException, LogInException {	
			if(name.length() < getMinName()) 				throw new RegisterException("Der Name muss mindestens "+getMinName()+" Zeichen lang sein", RegisterException.ILLEGAL_NAME);
			if(!passwort.equals(passwort1)) 				throw new RegisterException("Die Passwörter stimmen nicht überein", RegisterException.NON_MATCHING_PASSWORDS);
			if(passwort.length() < getMinPasswort()) 		throw new RegisterException("Das Passwort muss mindestens "+getMinPasswort()+" Zeichen lang sein", RegisterException.ILLEGAL_PASSWORD);
			if(passwort.length() > getMaxPasswort()) 		throw new RegisterException("Das Passwort darf höchstens "+getMaxPasswort()+" Zeichen lang sein", RegisterException.ILLEGAL_PASSWORD);
		
		try(Connection con = con();){
			
			//Name bereits vorhanden?
			if(nameExistiert(con, name))
				throw new RegisterException("Der Name: '"+name+"' existiert bereits", RegisterException.NAME_EXISTS);
			
			//Anlegen
			String regDat = LocalDateTime.now().toString();
			if(regDat.length()>27) regDat = regDat.substring(0, 27);
			regDat = regDat.replaceFirst("T", " ");
			String pass =  passwort+name+regDat;
			if(pass.length()>71) pass = pass.substring(0, 71);
			String hashedPw = BCrypt.withDefaults().hashToString(10, pass.toCharArray());		
			String sql = "insert into nutzer(name, passwort, rechte, registrierungsDatum) values(?, ?, 1, ?);";
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setString(1, name);
				ps.setString(2, hashedPw);
				ps.setString(3, regDat);
				ps.executeUpdate();
			}			
			FensterManager.logErreignis("\nDas Konto "+name+" wurde erfolgreich erstellt", Color.GREEN);
			anmeldenKonto(name, passwort, con);
		}
	}
	
	private static boolean nameExistiert(Connection con, String name) throws SQLException {
		String sql = "Select name from Nutzer where name=?";
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, name);
			try(ResultSet rs = ps.executeQuery()){
				return rs.next();
			}
		}
	}
	
	
	public void vonAnderenInstanzenAbmelden(String name, String passwort) throws SQLException {
		String sql = "Delete from angemeldet where nid=?;";
	
		try(Connection con = con();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.executeUpdate();
			
			FensterManager.logErreignis("\nSie wurden von allen bestehenden Anmeldungen abgemeldet");
			try {
				anmeldenKonto(name, passwort, con);
			} catch (LogInException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void abmelden() {
		try(Connection con = con()){
			abmelden(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void abmelden(Connection con)  {
		String s = "Delete angemeldet where nid="+id+"and iid="+getApplikationsId();
		id = -1;
		name = null;
		rechte.reset();
		angemeldet.set(false);
		try(Statement st = con.createStatement()){
			st.executeUpdate(s);
		}catch (Exception e) {}
	}
	
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Rechte getRechte() {
		return rechte;
	}
	public boolean isAngemeldet() {
		return angemeldet.get();
	}
	public ReadOnlyBooleanProperty angemeldetProperty() {
		return angemeldet.getReadOnlyProperty();
	}
	 
	public static int getMaxName() {
		return maxSize.get("NameMax");
	}
	public static int getMinName() {
		return maxSize.get("NameMin");
	}
	public static int getMaxPasswort() {
		return maxSize.get("PasswortMax");
	}
	public static int getMinPasswort() {
		return maxSize.get("PasswortMin");
	}
	
	public class Rechte {
		private String berechtigung;
		private boolean read;
		private boolean add;
		private boolean update;
		private boolean updateAll;
		private boolean multiLogin;
		private boolean reviewRead;
		private boolean reviewWrite;
		private boolean reviewWriteAll;
		
		private Rechte() {}
		
		public void log() {
			FensterManager.logErreignis("\nRechtestatus: 	"+berechtigung	);
			FensterManager.logErreignis("Film einsehen", 	read? Color.GREEN:Color.RED);
			FensterManager.logErreignis("Film hinzufügen",	add? Color.GREEN:Color.RED);
			FensterManager.logErreignis("Eigene Film modifizieren: ",		update? Color.GREEN:Color.RED);
			FensterManager.logErreignis("Beliebige Filme modifizieren: ",	updateAll? Color.GREEN:Color.RED);
			FensterManager.logErreignis("Rezension lesen ",		reviewRead? Color.GREEN:Color.RED);
			FensterManager.logErreignis("Rezension verfassen ", reviewWrite? Color.GREEN:Color.RED);
		}

		private void setRechte(String berechtigung, boolean read, boolean add, boolean update, boolean updateAll, boolean multiLogin, boolean reviewRead, boolean reviewWrite, boolean reviewWriteAll) {
			this.berechtigung = berechtigung;
			this.read = read;
			this.add = add;
			this.update = update;
			this.updateAll = updateAll;
			this.multiLogin = multiLogin;
			this.reviewRead = reviewRead;
			this.reviewWrite = reviewWrite;
			this.reviewWriteAll = reviewWriteAll;
		}
		
		private void reset() {
			setRechte(null, false, false, false, false, false, false, false, false);
		}

		public String getBerechtigung() {
			return berechtigung;
		}

		public boolean isRead() {
			return read;
		}

		public boolean isAdd() {
			return add;
		}

		public boolean isUpdate() {
			return update;
		}

		public boolean isUpdateAll() {
			return updateAll;
		}

		public boolean isMultiLogin() {
			return multiLogin;
		}

		public boolean isReviewRead() {
			return reviewRead;
		}

		public boolean isReviewWrite() {
			return reviewWrite;
		}

		public boolean isReviewWriteAll() {
			return reviewWriteAll;
		}

		@Override
		public String toString() {
			return "Rechte [berechtigung=" + berechtigung + ", read=" + read + ", add=" + add + ", update=" + update
					+ ", updateAll=" + updateAll + ", multiLogin=" + multiLogin + ", reviewRead=" + reviewRead
					+ ", reviewWrite=" + reviewWrite + ", reviewWriteAll=" + reviewWriteAll + "]";
		}

		
	}
	

}
