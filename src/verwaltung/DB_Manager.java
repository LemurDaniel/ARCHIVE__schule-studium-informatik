package verwaltung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import verwaltung.entitaeten.Genre;
import verwaltung.entitaeten.Rolle;
import verwaltung.verwaltungen.Filmverwaltung;
import verwaltung.verwaltungen.Listenverwaltung;
import verwaltung.verwaltungen.unterverwaltungen.Personenverwaltung;


public class DB_Manager {
	
	
//	private static String url = "jdbc:sqlserver://testdaniel001.database.windows.net:1433;databaseName=FilmDB";
//	private static String user = "demolandau";
//	private static String password = "bj14zC2O4zHg9Rtr7yUj";
	
	private static String url = "jdbc:sqlserver://localhost:1433;databaseName=FilmDB";
	private static String user = "DanielTest";
	private static String password = "Test";
	
	public static Map<String, Integer> maxSize = new TreeMap<>();
	
   static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Kein Treiber gefunden");
			e.printStackTrace();
		}
		
		try (Connection con = con()){
			getDaten(con);
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	
	private static int connectionsCreated = 0;	
	private static int ApplikationsId;
	public static int getApplikationsId() {
		return ApplikationsId;
	}
	protected static void setApplikationsId(int ApplikationsId) {
		DB_Manager.ApplikationsId = ApplikationsId;
	}
	
	protected static Connection con() throws SQLException {
		System.out.println("Connection: "+ ++connectionsCreated);
		return DriverManager.getConnection(url, user, password);
	}
	

	
	public static Connection getCon() throws SQLException {
		if(!Nutzer.getNutzer().isAngemeldet())	throw new SQLException("Sie sind nicht angemeldet");
		Connection con = null;	
		try {	
			con = con();
			if(!Nutzer.getNutzer().getRechte().isMultiLogin()) {
				try(PreparedStatement ps2 = con.prepareStatement("Select iid from angemeldet where nid=? and iid=?");){
					ps2.setInt(1, Nutzer.getNutzer().getId());
					ps2.setInt(2, ApplikationsId);
						
					try(ResultSet rs = ps2.executeQuery();){							
						if(!rs.next()) {
							Nutzer.getNutzer().abmelden(con);
							throw new SQLException("Sie wurden von einer anderen Applikation ausgeloggt");
						}
					}
				}
			}
			
			return con;
		} catch (SQLException e) {
			if(con!=null)	con.close();
			throw e;
		}
	}
	
	
	private static void getDaten(Connection con) throws SQLException {
		if(maxSize.size()>0)	return;
		
		try(ResultSet rs = con.getMetaData().getColumns(null, "dbo", null, null)){

			while(rs.next()) {
				String col = rs.getString("COLUMN_NAME");
				String tab = rs.getString("TABLE_NAME");
				int size = rs.getInt("COLUMN_SIZE");
				
				if(tab.equals("nutzer")) {
					if(col.equals("name")) 			maxSize.put("NameMax", size);
				}
				else if(tab.equals("rezension")) {
					if(col.equals("titel") ) 		maxSize.put("RezTitelMax", size);
					else if(col.equals("inhalt") ) 	maxSize.put("RezInhaltMax", size);
				}
				else if(tab.equals("film")) {
					if(col.equals("titel")) 		maxSize.put("FilmTitelMax", size);
				}
				else if(tab.equals("person")) {
					if(col.equals("vorname"))		maxSize.put("PerVornameMax", size);
					else if(col.equals("name"))		maxSize.put("PerNameMax", size);
				}
				else if(tab.equals("liste")) {
					if(col.equals("name"))			maxSize.put("ListeNameMax", size);
				}
			}
		}
		
		maxSize.put("NameMin", 6);
		
		maxSize.put("RezTitelMin", 10);
		maxSize.put("RezInhaltMin",0);
		
		maxSize.put("FilmTitelMin", 10);
		
		maxSize.put("ListeNameMin", 2);
		
		maxSize.put("PasswortMax", 20);
		maxSize.put("PasswortMin", 6);
		//Experimental Movies: Logistics 51420 Minuten (857h / 35d, 17h)
		//Cinematic Movies: Resan 873 Minuten (14h, 33min)
		maxSize.put("FilmDauerMax", 999);
		maxSize.put("FilmDauerMin", 0);
		maxSize.put("JahrMin", 1878);	//The Horse In Motion
		maxSize.put("JahrMax", LocalDate.now().getYear());
		maxSize.put("GenreMax", 5);
		maxSize.put("GenreMin", 1);
		maxSize.put("ErgebnisseMin", 10);
		maxSize.put("ErgebnisseMax", 1000);
		
		Filmverwaltung.ladeGerne(con);
		Personenverwaltung.ladeRollen(con);

		maxSize.forEach((k,v)->System.out.println(k+": "+v));
	}

	public static int get(String string) {
		return maxSize.get(string);
	}

	
}
