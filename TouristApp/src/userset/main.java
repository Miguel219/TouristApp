package userset;

import java.sql.*;
import java.util.Scanner;
import java.util.Random;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class main extends Application {
	

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("userset.fxml"));
			Scene scene = new Scene(root,550,400);
			primaryStage.setTitle("TouristApp User");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void Main(String[] args) {
		launch(args);
	}
	
}	
	
	