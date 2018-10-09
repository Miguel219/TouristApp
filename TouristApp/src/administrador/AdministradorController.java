package administrador;

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

import DataBase.Lugaresdb;
import application.Main;

public class AdministradorController {
	
	private Main main;
	
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
	private Lugar lugar;

	public void showImage() {
		Boolean verificado = verificarPath();
		if (verificado == true) {
			imageContainer.setImage(image);
		}else if (verificado == false) {
			Alert alert = new Alert(AlertType.ERROR);
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
				
				boolean ingresado = miLugar.ingresarLugar(name,country,imagePath);
				if(ingresado==true) {
					
					ResultSet result = miLugar.buscarLugar(name, country);
					lugar = new Lugar();
					lugar.ingresarLugar(result);
				
					//Mostrar mansaje de que se guardo el lugar
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Exito");
					alert.setHeaderText("Se ha guardado el lugar correctamente correctamente");

					alert.showAndWait();
					
					main = new Main();
					main.changeToTags(lugar);
					
				}else if(ingresado==false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Error al subir el lugar");
					alert.setContentText("El lugar ya existe en la base de datos");

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
	
	public void editarLugar() {
		
		main = new Main();
		main.changeToEditPlace();
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
			if(name==null)
				return false;
			country = countryTextField.getText();
			if(country==null)
				return false;
			imagePath = imagePathTextField.getText();
			if(imagePath==null)
				return false;
		    image = new Image(new FileInputStream(imagePath));
		    return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
}
