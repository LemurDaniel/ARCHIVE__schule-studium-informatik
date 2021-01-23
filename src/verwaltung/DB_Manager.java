package verwaltung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import verwaltung.entitaeten.Nutzer;

public class DB_Manager {
	
	
	private static String url = "jdbc:sqlserver://localhost:1433;databaseName=TEST";
	private static String user = "DanielTest";
	private static String password = "Test";
	
	protected static Map<String, Integer> maxSize = new HashMap<>();
	
   static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Kein Treiber gefunden");
			e.printStackTrace();
		}
		
		
//		try {
//			getCon();
//		} catch (SQLException e) {
//			user = "student";
//			password = "wifuser";
//		}
		InstanzAnmelden();
		metaDaten();
		System.out.println(maxSize.size());
		maxSize.forEach( (k,o)->System.out.println(k+"   "+o));
	}
	
	
	private static int connectionsCreated = 0;
	
	private static int ApplikationsId;
	public static int getApplikationsId() {
		return ApplikationsId;
	}
	
	public static void InstanzAnmelden() {
		try(Connection con = getCon();){
			PreparedStatement ps = con.prepareStatement("insert into instanzen(angemeldet, online) values(?, 1); select SCOPE_IDENTITY();");
			ps.setString(1, LocalDateTime.now().toString());
			ResultSet rs = ps.executeQuery();
			rs.next();
			ApplikationsId = rs.getInt(1);
			System.out.println("app  "+ApplikationsId);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void InstanzAbmelden() {
			try {
				if(Nutzer.getNutzer().isAngemeldet()) Nutzer.getNutzer().abmelden();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		try(Connection con = getCon();){
			PreparedStatement ps = con.prepareStatement("Update instanzen set abgemeldet=?, online=0 where id=?;");
			ps.setString(1, LocalDateTime.now().toString());
			ps.setInt(2, ApplikationsId);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	
	protected static Connection getCon() throws SQLException {
		Connection con = DriverManager.getConnection(url, user, password);
		try {	
			
			if(Nutzer.getNutzer().isAngemeldet()) {
				if(!Nutzer.getNutzer().getRechte().isMultiLogin()) {
					PreparedStatement ps = con.prepareStatement("Select iid from instanzen_nutzer where nid=? and iid=?;");
					ps.setInt(1, Nutzer.getNutzer().getId());
					ps.setInt(2, ApplikationsId);
					ResultSet rs = ps.executeQuery();
					
					if(!rs.next()) {
						Nutzer.getNutzer().abmelden();
						throw new SQLException("Sie wurden von einer anderen Applikation ausgeloggt");
					}
				}
			}

			System.out.println("Connection: "+ ++connectionsCreated);
			return con;
		} catch (SQLException e) {
			con.close();
			throw e;
		}
	}
	
	
	private static void metaDaten() {
		try(Connection con = getCon()){
			ResultSet rs = con.getMetaData().getColumns(null, "dbo", null, null);

			while(rs.next()) {
				String col = rs.getString("COLUMN_NAME");
				String tab = rs.getString("TABLE_NAME");
				int size = rs.getInt("COLUMN_SIZE");
				
				if(tab.equals("Nutzer")) {
					if(col.equals("name")) 			maxSize.put("name", size);
					else if(col.equals("passwort")) maxSize.put("passwort", size);
				}
				else if(tab.equals("rezensionen")) {
					if(col.equals("titel")			&& tab.equals("rezensionen")) maxSize.put("rezTitel", size);
					else if(col.equals("inhalt")	&& tab.equals("rezensionen")) maxSize.put("rezInhalt", size);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
