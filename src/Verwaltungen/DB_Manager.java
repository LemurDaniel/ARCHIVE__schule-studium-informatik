package Verwaltungen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import Verwaltungen.entitaeten.Nutzer;

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
		try(Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TEST", "Daniel_Test", "Test");) {	
			
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

			System.out.println(++connectionsCreated);
			return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TEST", "Daniel_Test", "Test");
		} catch (SQLException e) {
			throw e;
		}
	}
	

}
