package application;
	


import gui.FensterManager;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static Application instance;
	public static Application getApplication() {
		return instance;
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception{
			
		instance = this;	
//		  String url = "jdbc:sqlserver://testserverdaniel.database.windows.net:1433;databaseName=FilmDB";
//		  String user = "daniel";
//		  String password = "`nfTV|H_y7~PUNh\"'*\\}\\tbyqObcN|8Y]O$lq3Q`Vvt7U5uSNG]_j>vo@RQ<g)\"?";
//		  
//	try(Connection con = DriverManager.getConnection(url, user, password)){
//		con.setAutoCommit(false);
//		for(int i=4001; i<100_000; i++) {
//			try(Statement st = con.createStatement()){
//				st.executeUpdate("Insert into Film(ersteller, titel, dauer, erscheinungsjahr, bewertung) values(1, 'TEssssssssssssssssssT"+i+"', 120, 1999, 0) Insert into genre_film values(0, SCOPE_Identity())");
//				if(i%100==0)System.out.println(i);
//			}
//			con.commit();
//		}
//	}
		


		FensterManager.setPrimaryStage( FensterManager.getAnmelden() );
	}

	public static void main(String[] args) {
		launch(args);
	}
		
}
