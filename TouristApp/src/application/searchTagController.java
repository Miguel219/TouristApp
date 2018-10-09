package application;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
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

import DataBase.Usuariosdb;
import LoginIn.Usuario;


public class searchTagController {

	private Main main;
	
	@FXML	
	private FlowPane searchFlowPane;
	@FXML	
	private TextField tagNameTextField;
	@FXML
	private Button account;
	@FXML
	private Button home;
	@FXML
	private Button search;
	@FXML
	private Button pin;
	
	private Usuariosdb miUsuario;
	
	private Usuario userLoggedIn;
	
	private String tagName;
	
	public void buscarTag() {
		
		Boolean verificado = verificarDatos();
		if (verificado == true) {
			try {
				
				
				ResultSet result = miUsuario.buscarTag(tagName);
				if(result!=null) {
					searchFlowPane.getChildren().clear();
					
					while (result.next()) {
						//Se inicializa las variables
						String tagName = result.getString("tag");
						int tagId = result.getInt("tagId");
						ArrayList<Tag> tagsSeguidos = userLoggedIn.getTagList();
						
						Label label = new Label(tagName);
						label.setFont(new Font(18));
						Button button = new Button();
						button.setId(Integer.toString(tagId));
						button.setText(null);
						for (int i = 0; i < tagsSeguidos.size(); i++) {
							if(tagsSeguidos.get(i).getTagId()==tagId) {
								button.setText("Seguido");
								button.setDisable(true);
							}
							
						}
						if(button.getText()==null) {
							button.setText("Seguir");
						}
						Region p = new Region();
						p.setPrefSize(347.0, 4.0);
						Line line = new Line(0, 0, 350, 0);
						Region p1 = new Region();
						p1.setPrefSize(347.0, 4.0);
						//Se agregan al FlowPane
						searchFlowPane.getChildren().add(label);
						searchFlowPane.getChildren().add(button);
						searchFlowPane.getChildren().add(p);
						searchFlowPane.getChildren().add(line);
						searchFlowPane.getChildren().add(p1);
						
					}
					
				}else if(result==null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Error al buscar tags");
					alert.setContentText("No hay ningun tag con ese nombre");
					alert.showAndWait();
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Error de conexion");
				alert.setContentText("Error al buscar en base de datos");

				alert.showAndWait();
			}
		}else if (verificado == false) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error en datos ingresados");
			alert.setContentText("Verifica tus datos ingresados");

			alert.showAndWait();
		}
	}
	
	
	
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



	public Boolean verificarDatos() {
		try {
			miUsuario = new Usuariosdb();
			tagName = tagNameTextField.getText();
		    return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
	public void goToAccount(){
		
		main = new Main();
		main.changeToUserEdit(userLoggedIn);
		
	}
	
	public void goToHome(){
		
		//main = new Main();
		//main.changeToTags(lugar);
		
	}
	
	public void goToSearch(){
		
		main = new Main();
		main.changeToSearch(userLoggedIn);
		
	}
	
	public void goToPin(){
		
		//main = new Main();
		//main.changeToTags(lugar);
		
	}

	@FXML
    public void initialize() {
		userLoggedIn = new Usuario();
		
    }
	
	public void llenarClase() {
		
		//Botones
		try {
			
			ImageView accountImage = new ImageView(new Image(new FileInputStream(this.getClass().getResource("/application/Images/account.png").toString())));
			accountImage.setFitHeight(50);
			accountImage.setFitWidth(50);
			account.setGraphic(accountImage);
			
			ImageView homeImage = new ImageView(new Image(new FileInputStream(this.getClass().getResource("/application/Images/home.png").toString())));
			homeImage.setFitHeight(50);
			homeImage.setFitWidth(50);
			home.setGraphic(homeImage);
			
			ImageView searchImage = new ImageView(new Image(new FileInputStream(this.getClass().getResource("/application/Images/search.png").toString())));
			searchImage.setFitHeight(50);
			searchImage.setFitWidth(50);
			search.setGraphic(searchImage);
			search.setDisable(true);

			ImageView pinImage = new ImageView(new Image(new FileInputStream(this.getClass().getResource("/application/Images/pin.png").toString())));
			pinImage.setFitHeight(50);
			pinImage.setFitWidth(50);
			pin.setGraphic(pinImage);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
