package administrador;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

import DataBase.Lugaresdb;

public class AdministradorController {
	
	@FXML	
	private ImageView imageContainer;
	@FXML
	private TextField imagePathTextField;
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField countryTextField;

	private String name;
	private String country;
	private String imagePath;
	private Image image;
	
	private Lugaresdb miLugar;

	public void showImage() {
		Boolean verificado = verificarPath();
		if (verificado == true) {
			imageContainer.setImage(image);
		}else if (verificado == false) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Error al encontrar la imagen");
			alert.setContentText("Verifica la direccion de la imagen");

			alert.showAndWait();
		}
	    
	  }
	
	public void ingresarLugar() {
		Boolean verificado = verificarDatos();
		if (verificado == true) {
			try {
				miLugar.ingresarLugar(name,country,imagePath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (verificado == false) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Error en datos ingresado");
			alert.setContentText("Verifica tus datos ingresados");

			alert.showAndWait();
		}
	}
	
	public Boolean verificarPath() {
		try {
			imagePath = imagePathTextField.getText();
		    image = new Image(new FileInputStream(imagePath));
		    return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
	public Boolean verificarDatos() {
		try {
			miLugar = new Lugaresdb();
			name = nameTextField.getText();
			country = countryTextField.getText();			
			imagePath = imagePathTextField.getText();
		    image = new Image(new FileInputStream(imagePath));
		    return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
}
