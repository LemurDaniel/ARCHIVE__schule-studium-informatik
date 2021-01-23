package application;
	

import java.sql.SQLException;

import gui.FensterManager;
import javafx.application.Application;
import javafx.stage.Stage;
import verwaltung.DB_Manager;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception{
			
		Thread[] th = new Thread[200];
		
		Thread ts = new test();
		System.out.println("test");
		for(int i=0; i<th.length; i++) {
			th[i] = new test();
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("New");
			th[i].start();
		}
		
		//FensterManager.setPrimaryStage( FensterManager.getAnmelden() );
		//FensterManager.getHauptSeite().show();

		//FensterManager.setSecondary(FensterManager.getListensicht());
//		Nutzer.getNutzer().anmeldenGast();

	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public class test extends Thread{
		
		int count = 1000;
		
		public void run() {
			while(--count>=0) {
				try {
					System.out.println("Test: "+count);
					try {
						DB_Manager.con();
					} catch (SQLException e) {
						System.out.println("I am Dead :(");
						break;
					}
					sleep(20);
				}catch(InterruptedException e) {
				}
			}
		}
	}
	
}
