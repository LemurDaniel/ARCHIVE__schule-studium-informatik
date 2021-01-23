package bla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.LogInException;
import exceptions.RegisterException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import test.Sprache;

public class Nutzer extends DB_Manager {
	
	private static Nutzer instance;
	public static Nutzer getNutzer() {
		return instance;
	}

	private int id;
	private String name;
	private Rechte rechte;
	
	private Nutzer(ResultSet rs) throws SQLException{
		this.id = rs.getInt("id");
		this.name = rs.getString("name");
		rechte = new Rechte(rs.getBoolean("read"), rs.getBoolean("add"), rs.getBoolean("change"), rs.getBoolean("masterChange"), rs.getBoolean("multiAccount"));
	}

	public static void anmeldenGast() throws Exception {
		anmeldenKonto("Gast", "Gast");
	}
	
	public static void anmeldenKonto(String name, String passwort) throws Exception {	
		try(Connection con = getCon();){
			ResultSet rs = con.createStatement().executeQuery("Select Count(*) from Nutzer where name='"+name+"'");
			if(!rs.next()) throw new LogInException(LogInException.NO_USER);
			rs.close();
			
			PreparedStatement ps = con.prepareStatement("Select nutzer.id, name, rechte.berechtigung, [read], [add], change, masterChange, multiAccount from Nutzer inner join rechte on nutzer.rechte=rechte.id where name=? and passwort=?");
			ps.setString(1, name);
			ps.setString(2, passwort);
			rs = ps.executeQuery();
			if(!rs.next()) throw new LogInException(LogInException.WRONG_PASSWORD);		
			if(!rs.getBoolean("multiAccount")) {
				con.createStatement().executeUpdate("Delete from instanzen_nutzer where nid="+rs.getInt(1));
				//throw new LogInException(LogInException.ALREADY_LOGGED_IN);
			}
			
			ps = con.prepareStatement("insert into instanzen_nutzer values(?, ?)");
			ps.setInt(1, rs.getInt(1));
			ps.setInt(2, DB_Manager.getApplikationsId());
			ps.execute();
			instance = new Nutzer(rs);
		}
	}
	
//	public static void vonAllenInstanzenAbmelden(String name, String passwort) throws Exception{
//		try(Connection con = getCon();){
//			PreparedStatement ps = con.prepareStatement("Select id from Nutzer where name=? and passwort=?");
//			ps.setString(1, name);
//			ps.setString(2, passwort);
//		}
//	}
	
	public static void registrieren(String name, String passwort) throws Exception {	
			if(name.length()<3) throw new RegisterException(RegisterException.ILLEGAL_NAME);
			if(passwort.length() < 6) throw new RegisterException(RegisterException.ILLEGAL_PASSWORD);
		
		try(Connection con = getCon();){
			ResultSet rs = con.createStatement().executeQuery("Select id from nutzer where name='"+name+"'");
			if(rs.next()) throw new RegisterException(RegisterException.NAME_EXISTS);
			
			PreparedStatement ps = con.prepareStatement("insert into nutzer(name, passwort, rechte) values(?, ?, ?)");
			ps.setString(1, name);
			ps.setString(2, passwort);
			ps.setInt(3, 1);
			ps.execute();
		}
		anmeldenKonto(name, passwort);
	}
	
	public void abmelden() throws Exception {
		abmelden(id);
	}
	public void abmelden(int id) throws Exception {
		instance = null;
		rechte = null;
		try(Connection con = getCon();){
			con.createStatement().executeUpdate("Delete instanzen_nutzer where nid="+id+"and iid="+DB_Manager.getApplikationsId());
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
	
	
	public class Rechte {
		private String berechtigung;
		private boolean read;
		private boolean add;
		private boolean change;
		private boolean masterChange;
		private boolean multiAccount;
		
		private Rechte(boolean read, boolean add, boolean change, boolean masterChange, boolean multiAccount) {
			this.read = read;
			this.add = add;
			this.change = change;
			this.masterChange = masterChange;
			this.multiAccount = multiAccount;
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
		public boolean isChange() {
			return change;
		}
		public boolean isMasterChange() {
			return masterChange;
		}	
		public boolean isMultiAccount() {
			return multiAccount;
		}	
	}
}
