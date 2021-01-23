package bla;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DB_Manager {
	
	static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Kein Treiber gefunden");
			e.printStackTrace();
		}
	}
	
	
	private static int connectionsCreated = 0;
	
	private static int ApplikationsId;
	public static int getApplikationsId() {
		return ApplikationsId;
	}
	
	public static void InstanzAnmelden() {
		try(Connection con = getCon();){
			PreparedStatement ps = con.prepareStatement("insert into instanz(angemeldet) values(?); select SCOPE_IDENTITY();");
			ps.setString(1, LocalDateTime.now().toString());
			ResultSet rs = ps.executeQuery();
			rs.next();
			ApplikationsId = rs.getInt(1);
			System.out.println(ApplikationsId);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void InstanzAbmelden() {
			try {
				if(Nutzer.getNutzer()!=null) Nutzer.getNutzer().abmelden();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		try(Connection con = getCon();){
			PreparedStatement ps = con.prepareStatement("Update instanz set abgemeldet=? where id=?;");
			ps.setString(1, LocalDateTime.now().toString());
			ps.setInt(2, ApplikationsId);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	
	public static Connection getCon() throws Exception {
		try(Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=FilmDB", "DanielTest", "Test")) {	
			if(Nutzer.getNutzer()!=null) {
				if(!Nutzer.getNutzer().getRechte().isMultiAccount()) {
					ResultSet rs = con.createStatement().executeQuery("Select iid from instanzen_nutzer where nid="+Nutzer.getNutzer().getId());
					rs.next();
					if(rs.getInt(1)!=ApplikationsId) {
						Nutzer.getNutzer().abmelden();
						throw new Exception("Sie wurden von einer anderen Applikation ausgelogt");
					}
				}
			}

			System.out.println(++connectionsCreated);
			return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=FilmDB", "DanielTest", "Test");
		} catch (SQLException e) {
			throw e;
		}
	}
	

}
