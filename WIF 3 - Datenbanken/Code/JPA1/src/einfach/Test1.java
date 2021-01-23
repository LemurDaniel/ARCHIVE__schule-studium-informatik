package einfach;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test1 {
	private static String pwd = "student";
	private static String user = "wifuser";
	private static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=DB0_Landau";
	private static Connection con;
	private static DatabaseMetaData dbmd;
	
	static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("wo ist der jdbc driver?");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			con = DriverManager.getConnection(url, user, pwd);
			dbmd = con.getMetaData();
			ResultSet columns = dbmd.getColumns(null, null, "Artikel", null); 
			while(columns.next()) {
				String cname = columns.getString("COLUMN_NAME");
				String typ = columns.getString("DATA_TYPE");
				System.out.println(cname + ", " + typ);
			}
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Artikel");
			while(rs.next()) {
				System.out.printf("%d, %s, %d, %f\n", rs.getInt("id"), rs.getString("bezeichnung"), rs.getInt("menge"), rs.getFloat("preis"));
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}
	}

}
