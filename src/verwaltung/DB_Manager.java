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

import verwaltung.entitaeten.Genre;
import verwaltung.entitaeten.Rolle;


public class DB_Manager {
	
	
//	private static String url = "jdbc:sqlserver://testdaniel001.database.windows.net:1433;databaseName=FilmDB";
//	private static String user = "demolandau";
//	private static String password = "bj14zC2O4zHg9Rtr7yUj";
	
	private static String url = "jdbc:sqlserver://localhost:1433;databaseName=FilmDB";
	private static String user = "DanielTest";
	private static String password = "Test";
	
	public static Map<String, Integer> maxSize = new HashMap<>();
	protected static Map<Integer, Genre> genre;
	protected static Map<Integer, Rolle> rolleMap;
	
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
		System.out.println(maxSize.size());
		maxSize.forEach( (k,o)->System.out.println(k+"   "+o));
	}
	
	
	private static int connectionsCreated = 0;
	
	private static int ApplikationsId;
	public static int getApplikationsId() {
		return ApplikationsId;
	}
	
	public static void InstanzAnmelden() {
		try(Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement ps = con.prepareStatement("insert into instanz(angemeldet, connectionsMade) values(?, 1); select SCOPE_IDENTITY();");){
			ps.setString(1, LocalDateTime.now().toString());
			
			try(ResultSet rs = ps.executeQuery();){
				rs.next();
				ApplikationsId = rs.getInt(1);
				System.out.println("app  "+ApplikationsId);
			}
			getDaten(con);
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

		try(Connection con = getCon();
				PreparedStatement ps = con.prepareStatement("Update instanz set abgemeldet=? where id=?;");){
			ps.setString(1, LocalDateTime.now().toString());
			ps.setInt(2, ApplikationsId);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	
	public static Connection getCon() throws SQLException {
		Connection con = DriverManager.getConnection(url, user, password);
		try (PreparedStatement ps1 = con.prepareStatement("update instanz set connectionsMade=? where id=?");){	
			
			if(Nutzer.getNutzer().isAngemeldet() && !Nutzer.getNutzer().getRechte().isMultiLogin()) {
				try(PreparedStatement ps2 = con.prepareStatement("Select iid from instanz_nutzer where nid=? and iid=?");){
					ps2.setInt(1, Nutzer.getNutzer().getId());
					ps2.setInt(2, ApplikationsId);
						
					try(ResultSet rs = ps2.executeQuery();){							
						if(!rs.next()) {
							Nutzer.getNutzer().abmelden();
							throw new SQLException("Sie wurden von einer anderen Applikation ausgeloggt");
						}
					}
				}
			}
		
			ps1.setInt(1, ++connectionsCreated);
			ps1.setInt(2, ApplikationsId);
			ps1.executeUpdate();
			System.out.println("Connection: "+ connectionsCreated);
			return con;
		} catch (SQLException e) {
			con.close();
			throw e;
		}
	}
	
	
	private static void getDaten(Connection con) throws SQLException {
			
		try(ResultSet rs = con.getMetaData().getColumns(null, "dbo", null, null)){

			while(rs.next()) {
				String col = rs.getString("COLUMN_NAME");
				String tab = rs.getString("TABLE_NAME");
				int size = rs.getInt("COLUMN_SIZE");
				
				if(tab.equals("nutzer")) {
					if(col.equals("name")) 			maxSize.put("Name", size);
				}
				else if(tab.equals("rezension")) {
					if(col.equals("titel") ) 		maxSize.put("RezTitel", size);
					else if(col.equals("inhalt") ) 	maxSize.put("RezInhalt", size);
				}
				else if(tab.equals("film")) {
					if(col.equals("titel")) 		maxSize.put("FilmTitel", size);
				}
				else if(tab.equals("person")) {
					if(col.equals("vorname"))		maxSize.put("PerVorname", size);
					else if(col.equals("name"))		maxSize.put("PerName", size);
				}
			}
		}
		
		maxSize.put("PasswortMax", 20);
		maxSize.put("PasswortMin", 6);
		maxSize.put("FilmDauer", 999);
		maxSize.put("MinJahr", 1878);
		maxSize.put("MaxJahr", LocalDate.now().getYear());
		maxSize.put("Genre", 5);
		
		genre = new HashMap<>();
		try(Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("Select id, genre, text from genre")){
			while(rs.next()) 
				genre.put(rs.getInt(1), new Genre(rs.getInt(1), rs.getString(2), rs.getString(3)));
		}
		
		rolleMap =  new HashMap<>();
		try(Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("Select id, rolle from rolle")){
			while(rs.next()) 
				rolleMap.put(rs.getInt(1), new Rolle(rs.getInt(1), rs.getString(2)));
		}
	}

	
}
