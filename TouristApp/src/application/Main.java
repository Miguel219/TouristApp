package application;
	
import LoginIn.Usuario;
import administrador.Lugar;
import administrador.TagsController;
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
	
	public void changeToUserEdit(Usuario userLoggedIn) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/application/userset.fxml"));
			Parent newScene = loader.load();
			
			//Se envian los datos del usuario
			usuarioseditController uec = loader.getController();
			uec.setUserLoggedIn(userLoggedIn);
			uec.llenarClase();
			
			Scene scene = new Scene(newScene,400,550);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeToAdmin() {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/administrador/Administrador.fxml"));
			Parent newScene = loader.load();			
			Scene scene = new Scene(newScene,400,550);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeToTags(Lugar lugar) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/administrador/Tags.fxml"));
			Parent newScene = loader.load();	
			
			//Se envian los datos del usuario
			TagsController tc = loader.getController();
			tc.setLugar(lugar);
			tc.llenarInfoLugar();
			
			Scene scene = new Scene(newScene,400,550);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeToEditPlace() {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/administrador/Lugares.fxml"));
			Parent newScene = loader.load();			
			Scene scene = new Scene(newScene,400,550);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeToRecentPlace(Usuario userLoggedIn) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/application/recentPlaces.fxml"));
			Parent newScene = loader.load();
			
			//Se envian los datos del usuario
			recentPlacesController rpc = loader.getController();
			rpc.setUserLoggedIn(userLoggedIn);
			rpc.llenarClase();
			
			Scene scene = new Scene(newScene,400,550);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeToSearch(Usuario userLoggedIn) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/application/searchTag.fxml"));
			Parent newScene = loader.load();
			
			//Se envian los datos del usuario
			searchTagController stc = loader.getController();
			stc.setUserLoggedIn(userLoggedIn);
			stc.llenarClase();
			
			Scene scene = new Scene(newScene,400,550);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeToHome(Usuario userLoggedIn) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/application/homePlaces.fxml"));
			Parent newScene = loader.load();
			
			//Se envian los datos del usuario
			homePlacesController hpc = loader.getController();
			hpc.setUserLoggedIn(userLoggedIn);
			hpc.llenarClase();
			
			Scene scene = new Scene(newScene,400,550);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeToCommentPlace(Lugar lugar, Usuario userLoggedIn) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/application/commentPlace.fxml"));
			Parent newScene = loader.load();	
			
			//Se envian los datos del usuario
			commentPlaceController cpc = loader.getController();
			cpc.setLugar(lugar);
			cpc.setUserLoggedIn(userLoggedIn);
			cpc.llenarInfoLugar();
			
			Scene scene = new Scene(newScene,400,550);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeToViewCommentPlace(Lugar lugar, Usuario userLoggedIn) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/application/viewCommentPlace.fxml"));
			Parent newScene = loader.load();	
			
			//Se envian los datos del usuario
			viewCommentPlaceController vcpc = loader.getController();
			vcpc.setLugar(lugar);
			vcpc.setUserLoggedIn(userLoggedIn);
			vcpc.llenarInfoLugar();
			
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
