package application;
	

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gui.FensterManager;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import verwaltung.DB_Manager;


public class Main extends Application {
	
	private static Application instance;
	public static Application getApplication() {
		return instance;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
			
		instance = this;		

		FensterManager.setPrimaryStage( FensterManager.getAnmelden() );

	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public static class t extends DB_Manager{
		
		public static void t() throws SQLException {
			List<String> genre = new ArrayList<>();
			try(Connection con = con()){
				try(PreparedStatement ps = con.prepareStatement("Select * from genre")){
					try(ResultSet rs = ps.executeQuery()){
						while(rs.next()) genre.add(rs.getString("genre"));
					}
				}
			}
			
			genre.sort(String::compareTo);
			genre.forEach(s->System.out.println(s));
		
		
		try(Connection con = con()){
			try(PreparedStatement ps = con.prepareStatement("Select * from nutzer")){
				try(ResultSet rs = ps.executeQuery()){
					while(rs.next()) System.out.println(rs.getString("passwort"));
				}
			}
		}
		}
	}

	
}
