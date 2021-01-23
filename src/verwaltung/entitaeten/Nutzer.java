package verwaltung.entitaeten;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.LogInException;
import exceptions.RegisterException;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import verwaltung.DB_Manager;

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

	private void setNutzer(ResultSet rs) throws SQLException{
		id = rs.getInt("id");
		name = rs.getString("name");
		rechte.setRechte(rs.getString("berechtigung"), rs.getBoolean("read"), rs.getBoolean("add"), rs.getBoolean("update"), rs.getBoolean("updateAll"), rs.getBoolean("multiLogin"), rs.getBoolean("reviewRead"), rs.getBoolean("reviewWrite"), rs.getBoolean("reviewWriteAll"));
	
		System.out.println(rechte.toString());
	}

	public static void anmeldenGast() throws SQLException, LogInException {
		anmeldenKonto("Gast", "Gast");
	}
	
	public static void anmeldenKonto(String name, String passwort) throws SQLException, LogInException {	
		try(Connection con = getCon();){
			ResultSet rs = con.createStatement().executeQuery("Select name from Nutzer where name='"+name+"'");
			if(!rs.next()) throw new LogInException("Der Nutzer: '"+name+"' existiert nicht", LogInException.NO_USER);
			rs.close();
			
			PreparedStatement ps = con.prepareStatement("Select nutzer.id, name, rechte.* from Nutzer "
														+ "inner join rechte on nutzer.rechte=rechte.id where name=? and passwort=?;");
			ps.setString(1, name);
			ps.setString(2, passwort);
			rs = ps.executeQuery();
			if(!rs.next()) throw new LogInException("Das verwendete Passwort ist falsch", LogInException.WRONG_PASSWORD);	
			
			Nutzer ntz = getNutzer();
			ntz.setNutzer(rs);
		
			rs = con.createStatement().executeQuery("Select * from instanzen_nutzer where nid="+ntz.id+"and not iid="+getApplikationsId());
			if( !ntz.getRechte().isMultiLogin() && rs.next()) 
				throw new LogInException("Sie sind mit diesem Konto bereits in einer anderen Instanz angemeldet", LogInException.ALREADY_LOGGED_IN);		
			
			ps = con.prepareStatement("Insert into instanzen_nutzer(nid, iid) values(?, ?);");
			ps.setInt(1, ntz.getId());
			ps.setInt(2, getApplikationsId());
			ps.executeUpdate();
			ntz.angemeldet.set(true);
		}
	}
	
	public static void registrieren(String name, String passwort, String passwort1) throws SQLException, RegisterException, LogInException {	
			if(name.length()<3) throw new RegisterException("Der Name muss mindestens 3 Zeichen lang sein", RegisterException.ILLEGAL_NAME);
			if(!passwort.equals(passwort1)) throw new RegisterException("Das Passwort muss mindestens 6 Zeichen lang sein", RegisterException.NON_MATCHING_PASSWORDS);
			if(passwort.length() < 6) throw new RegisterException("Das Passwort muss mindestens 6 Zeichen lang sein", RegisterException.ILLEGAL_PASSWORD);
		
		try(Connection con = getCon();){
			ResultSet rs = con.createStatement().executeQuery("Select id from nutzer where name='"+name+"'");
			if(rs.next()) throw new RegisterException("Der Name: '"+name+"' existiert bereits", RegisterException.NAME_EXISTS);
			
			PreparedStatement ps = con.prepareStatement("insert into nutzer(name, passwort, rechte) values(?, ?, ?)");
			ps.setString(1, name);
			ps.setString(2, passwort);
			ps.setInt(3, 1);
			ps.executeUpdate();
		}
		anmeldenKonto(name, passwort);
	}
	
	public void vonAnderenInstanzenAbmelden() throws SQLException {
		try(Connection con = getCon();) {
			PreparedStatement ps = con.prepareStatement("Delete from instanzen_nutzer where nid=?;"
														+"Insert into instanzen_nutzer(nid, iid) values(?, ?);");
			ps.setInt(1, id);
			ps.setInt(2, id);
			ps.setInt(3, getApplikationsId());
			ps.executeUpdate();
			angemeldet.set(true);
		}
	}
	
	public void abmelden() throws SQLException {
		String s = "Delete instanzen_nutzer where nid="+id+"and iid="+DB_Manager.getApplikationsId();
		id = -1;
		name = null;
		rechte.reset();
		angemeldet.set(false);
		try(Connection con = getCon();){
			con.createStatement().executeUpdate(s);
		}

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
		return maxSize.get("name");
	}
	public static int getMaxPasswort() {
		return maxSize.get("passwort");
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
	
	
//	public void test() throws SQLException {
//		try(Connection con = getCon()){
//			ResultSet rs = con.getMetaData().getColumns(null, "dbo", null, "name");
//			while(rs.next()) {
//				if(rs.getString("COLUMN_NAME").equals("name")) System.out.println();
//				for(int i = rs.getMetaData().getColumnCount(); i>0; i--) {
//					System.out.println(rs.getMetaData().getColumnName(i));
//					System.out.println(rs.getObject(i));
//				}
//			}
//		}
//	}

}
