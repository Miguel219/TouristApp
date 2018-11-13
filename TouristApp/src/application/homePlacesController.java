package application;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import DataBase.Lugaresdb;
import DataBase.Usuariosdb;
import LoginIn.Usuario;
import administrador.Lugar;


public class homePlacesController {

	private Main main;
	
	@FXML	
	private FlowPane searchFlowPane;
	@FXML
	private Button account;
	@FXML
	private Button home;
	@FXML
	private Button search;
	@FXML
	private Button pin;
	
	private Usuariosdb miUsuario;
	
	private Lugaresdb miLugar;
	
	private Usuario userLoggedIn;	
	
	/**
	 * @return the userLoggedIn
	 */
	public Usuario getUserLoggedIn() {
		return userLoggedIn;
	}



	/**
	 * @param userLoggedIn the userLoggedIn to set
	 */
	public void setUserLoggedIn(Usuario userLoggedIn) {
		this.userLoggedIn = userLoggedIn;
	}

	public void goToAccount(){
		
		main = new Main();
		main.changeToUserEdit(userLoggedIn);
		
	}
	
	public void goToHome(){
		
		main = new Main();
		main.changeToHome(userLoggedIn);
		
	}
	
	public void goToSearch(){
		
		main = new Main();
		main.changeToSearch(userLoggedIn);
		
	}
	
	public void goToPin(){
		
		main = new Main();
		main.changeToRecentPlace(userLoggedIn);
		
	}

	@FXML
    public void initialize() {
		userLoggedIn = new Usuario();
		
    }
	
	public void llenarClase() {
		
		//Botones
		try {
			
			ImageView accountImage = new ImageView(new Image(this.getClass().getResource("/application/Images/account.png").toString()));
			accountImage.setFitHeight(50);
			accountImage.setFitWidth(50);
			account.setGraphic(accountImage);
			
			ImageView homeImage = new ImageView(new Image(this.getClass().getResource("/application/Images/home.png").toString()));
			homeImage.setFitHeight(50);
			homeImage.setFitWidth(50);
			home.setGraphic(homeImage);
			home.setDisable(true);
			
			ImageView searchImage = new ImageView(new Image(this.getClass().getResource("/application/Images/search.png").toString()));
			searchImage.setFitHeight(50);
			searchImage.setFitWidth(50);
			search.setGraphic(searchImage);

			ImageView pinImage = new ImageView(new Image(this.getClass().getResource("/application/Images/pin.png").toString()));
			pinImage.setFitHeight(50);
			pinImage.setFitWidth(50);
			pin.setGraphic(pinImage);
			
			//Se buscan los lugares segun los tags seguidos por el usuario

			miLugar = new Lugaresdb();
			miUsuario = new Usuariosdb();
			
			ArrayList<Tag> tagsSeguidos = userLoggedIn.getTagList();
			userLoggedIn.setPlaceList(new ArrayList<Lugar>());
			for (int i = 0; i < tagsSeguidos.size(); i++) {
				int tagId = tagsSeguidos.get(i).getTagId();
				ResultSet lugares = miLugar.buscarLugarPorTag(tagId);	
				if(lugares!=null)
					userLoggedIn.ingresarLugares(lugares);
			}
			//Se inicializan los lugares
			ArrayList<Lugar> lugaresSeguidos = userLoggedIn.getPlaceList();
			for (int i = 0; i < lugaresSeguidos.size(); i++) {
				
				int placeId = lugaresSeguidos.get(i).getPlaceId();
				String placeName = lugaresSeguidos.get(i).getPlaceName();
				String placeCountry = lugaresSeguidos.get(i).getPlaceCountry();
				Image placeImage = lugaresSeguidos.get(i).getPlaceImage();
				
				Label nameLabel = new Label();
				nameLabel.setFont(new Font(18));
				nameLabel.setText(placeName+" - ");
				
				Label countryLabel = new Label();
				countryLabel.setFont(new Font(18));
				countryLabel.setText(placeCountry+"  ");
				
				Button button = new Button();
				button.setId(Integer.toString(placeId));
				button.setText("Calificar");
				button.setStyle("-fx-background-color: lime;");
				button.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						try {							

							Button currentButton = (Button)event.getSource();
							int placeId = Integer.parseInt(currentButton.getId());
							ResultSet lugares = miLugar.buscarLugarPorId(placeId);	
							if(lugares!=null) {
								Lugar lugar = new Lugar();
								lugar.ingresarLugar(lugares);
								main = new Main();
								main.changeToCommentPlace(lugar,userLoggedIn);
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				});
				
				Region p = new Region();
				p.setPrefSize(347.0, 4.0);
				
				Region p1 = new Region();
				p1.setPrefSize(24.0, 4.0);
				
				ImageView imageContainer = new ImageView();
				imageContainer.setFitWidth(300);
				imageContainer.setFitHeight(200);
				imageContainer.setImage(placeImage);
				
				Region p2 = new Region();
				p2.setPrefSize(347.0, 4.0);
				
				Line line = new Line(0, 0, 350, 0);
				
				Region p3 = new Region();
				p3.setPrefSize(347.0, 4.0);
				
				//Se agrega al panel
				searchFlowPane.getChildren().add(nameLabel);
				searchFlowPane.getChildren().add(countryLabel);
				searchFlowPane.getChildren().add(button);
				searchFlowPane.getChildren().add(p);
				searchFlowPane.getChildren().add(p1);
				searchFlowPane.getChildren().add(imageContainer);
				searchFlowPane.getChildren().add(p2);
				searchFlowPane.getChildren().add(line);
				searchFlowPane.getChildren().add(p3);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
