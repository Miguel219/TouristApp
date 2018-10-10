package application;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import DataBase.Usuariosdb;
import LoginIn.Usuario;


public class usuarioseditController {

	private Main main;
	
	@FXML	
	private TextField username;
	@FXML
	private TextField phone;
	@FXML
	private TextField email;
	@FXML
	private Button account;
	@FXML
	private Button home;
	@FXML
	private Button search;
	@FXML
	private Button pin;

	private String nombre;
	private Integer numero;
	private String correoe;
	
	private Usuariosdb miedit;
	
	private Usuario userLoggedIn;
	
	public void guardarUsuario() {
		
		Boolean verificado = verificarDatos();
		if (verificado == true) {
			try {
				boolean editado = miedit.editarUsuario(userLoggedIn.getUserId(),nombre,numero,correoe);
				if(editado==true) {
					//Mostrar mansaje de que se edito correctamente el usuario
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Exito");
					alert.setHeaderText("Se ha editado el usuario correctamente");
					alert.showAndWait();
					
					//Se actualiza la informacion
					ResultSet result = miedit.buscarUsuario(nombre, userLoggedIn.getUserPassword());
					Usuario usuarioActualizado = new Usuario();
					usuarioActualizado.ingresarUsuario(result);
					setUserLoggedIn(usuarioActualizado);
					llenarClase();
					
				}else if(editado==false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Error al actualizar el usuario");
					alert.setContentText("El nombre seleccionado ya existe");
					alert.showAndWait();
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Error de conexion");
				alert.setContentText("Error al guardar en base de datos");

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
			miedit = new Usuariosdb();
			nombre = username.getText();
			String numeroString = phone.getText();			
			numero = Integer.parseInt(numeroString);
			correoe = email.getText();
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
		username.setText(userLoggedIn.getUserName());
		phone.setText(Integer.toString(userLoggedIn.getUserPhone()));
		email.setText(userLoggedIn.getEmail());
		
		//Botones
		try {
			ImageView accountImage = new ImageView(new Image(this.getClass().getResource("/application/Images/account.png").toString()));
			accountImage.setFitHeight(50);
			accountImage.setFitWidth(50);
			account.setGraphic(accountImage);
			account.setDisable(true);
			
			ImageView homeImage = new ImageView(new Image(this.getClass().getResource("/application/Images/home.png").toString()));
			homeImage.setFitHeight(50);
			homeImage.setFitWidth(50);
			home.setGraphic(homeImage);
			
			ImageView searchImage = new ImageView(new Image(this.getClass().getResource("/application/Images/search.png").toString()));
			searchImage.setFitHeight(50);
			searchImage.setFitWidth(50);
			search.setGraphic(searchImage);

			ImageView pinImage = new ImageView(new Image(this.getClass().getResource("/application/Images/pin.png").toString()));
			pinImage.setFitHeight(50);
			pinImage.setFitWidth(50);
			pin.setGraphic(pinImage);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
