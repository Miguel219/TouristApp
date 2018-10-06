package application;
	
import LoginIn.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/LoginIn/Login.fxml"));;
			Scene scene = new Scene(root,400,550);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("TouristApp");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeStage(Usuario userLoggedIn) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/application/userset.fxml"));
			Parent newScene = loader.load();
			//Se envian los datos del usuario
			usuarioseditController uec = loader.getController();
			uec.setUserLoggedIn(userLoggedIn);
			uec.llenarUsuarioLogged();
			loader.setController(userLoggedIn);
			
			Scene scene = new Scene(newScene,400,550);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
