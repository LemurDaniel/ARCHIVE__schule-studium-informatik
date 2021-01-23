package bla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.LogInException;
import exceptions.RegisterException;

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
		rechte = new Rechte(rs.getString("berechtigung"), rs.getBoolean("read"), rs.getBoolean("add"), rs.getBoolean("update"), rs.getBoolean("updateAll"), rs.getBoolean("multiLogin"), rs.getBoolean("reviewRead"), rs.getBoolean("reviewWrite"), rs.getBoolean("reviewWriteAll"));
	}

	public static void anmeldenGast() throws Exception {
		anmeldenKonto("Gast", "Gast");
	}
	
	public static void anmeldenKonto(String name, String passwort) throws Exception {	
		try(Connection con = getCon();){
			ResultSet rs = con.createStatement().executeQuery("Select Count(*) from Nutzer where name='"+name+"'");
			if(!rs.next()) throw new LogInException(LogInException.NO_USER);
			rs.close();
			
			PreparedStatement ps = con.prepareStatement("Select nutzer.id, name, rechte.* from Nutzer inner join rechte on nutzer.rechte=rechte.id where name=? and passwort=?");
			ps.setString(1, name);
			ps.setString(2, passwort);
			rs = ps.executeQuery();
			if(!rs.next()) throw new LogInException(LogInException.WRONG_PASSWORD);		
			if(!rs.getBoolean("multiLogin")) {
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
		private boolean update;
		private boolean updateAll;
		private boolean multiLogin;
		private boolean reviewRead;
		private boolean reviewWrite;
		private boolean reviewWriteAll;
		
		private Rechte(String berechtigung, boolean read, boolean add, boolean update, boolean updateAll, boolean multiLogin, boolean reviewRead, boolean reviewWrite, boolean reviewWriteAll) {
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

	}
}
