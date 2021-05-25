package verwaltung;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import gui.FensterManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import verwaltung.entitaeten.Nutzer;
import verwaltung.verwaltungen.Filmverwaltung;
import verwaltung.verwaltungen.unterverwaltungen.Personenverwaltung;


public class DB_Manager {
	
	// tServer:		testserverdaniel.database.windows.net
	// Login:		daniel
	// Passwort: 	`nfTV|H_y7~PUNh"'*\}\tbyqObcN|8Y]O$lq3Q`Vvt7U5uSNG]_j>vo@RQ<g)"?
	
	private static String url = "jdbc:sqlserver://daniel-testing.database.windows.net:1433;databaseName=FilmDB";
	private static String user = "daniel";
	private static String password = "plZE3?]<diOv2N_W>>UldVjUf";
	
	public static Map<String, Integer> maxSize = new HashMap<>();
	
   static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			System.exit(0);
		}
		
		try (Connection con = con()){
			getDaten(con);
		}catch(Exception e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Verbindungsfehler");
			a.setHeaderText("Es konnte keine Verbindung mit der Datenbank hergestellt werden");
			a.setContentText(e.getMessage());
			a.show();
		}
	}
	
	
	private static int ApplikationsId;
	public static int getApplikationsId() {
		return ApplikationsId;
	}
	protected static void setApplikationsId(int ApplikationsId) {
		DB_Manager.ApplikationsId = ApplikationsId;
	}
	
	protected static Connection con() throws SQLException {
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
			FensterManager.logErreignis(e);
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
				else if(tab.equals("film_person_rolle")) {
					if(col.equals("weiteres"))		maxSize.put("WeiteresMax", size);
				}
			}
		}
		
		maxSize.put("NameMin", 6);
		
		maxSize.put("RezTitelMin", 2);
		maxSize.put("RezInhaltMin",0);
		
		maxSize.put("FilmTitelMin", 2);
		
		maxSize.put("PerVornameMin", 2);
		maxSize.put("PerNameMin", 2);
		
		maxSize.put("ListeNameMin", 2);
		
		maxSize.put("PasswortMax", 70);
		maxSize.put("PasswortMin", 6);
		
		maxSize.put("Short", 44);
		maxSize.put("FilmDauerMax", 999);
		maxSize.put("FilmDauerMin", 0);
		maxSize.put("JahrMin", 1878);	//The Horse In Motion
		maxSize.put("JahrMax", LocalDate.now().getYear());
		maxSize.put("GenreMax", 5);
		maxSize.put("GenreMin", 1);
		maxSize.put("ErgebnisseMin", 10);
		maxSize.put("ErgebnisseMax", 1000_0);
		
		Filmverwaltung.ladeGerne(con);
		Personenverwaltung.ladeRollen(con);
	}

	public static int get(String string) {
		return maxSize.get(string);
	}

	
}
