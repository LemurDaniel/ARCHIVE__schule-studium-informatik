package aufgabe4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Manager {

	public DB_Manager(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getCon() throws SQLException {
		//return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Datenbank", "Daniel_Test", "Test");
		return DriverManager.getConnection("jdbc:sqlserver://testserverdaniel2.database.windows.net:1433;databaseName=FilmDB", "daniel", "`nfTV|H_y7~PUNh\"'*\\}\\tbyqObcN|8Y]O$lq3Q`Vvt7U5uSNG]_j>vo@RQ<g)\"?");
	}
	
	
}
