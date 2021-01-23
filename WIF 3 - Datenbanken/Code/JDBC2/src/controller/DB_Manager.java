package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Manager {
	private static String pwd = "Test";
	private static String user = "Daniel_Test";
	private static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=DB0_Landau";
	private static Connection con;
	
	static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("wo ist der jdbc driver?");
			e.printStackTrace();
		}
	}
	
	public static Connection getCon() throws SQLException {
		try {
			con = DriverManager.getConnection(url, user, pwd);
			return con;
		} catch (SQLException e) {
			System.out.println("keine DB-Verbindung!");
			e.printStackTrace();
			throw e;
		}
	}
}	
