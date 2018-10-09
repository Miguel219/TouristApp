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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import DataBase.Lugaresdb;
import application.Main;

public class TagsController {
	
	private Main main;
	
	@FXML	
	private ImageView imageContainer;
	@FXML
	private TextField tagTextField;
	@FXML
	private Label nameLabel;
	@FXML
	private Label countryLabel;

	private int placeId;
	private String tag;
	
	private Lugaresdb miLugar;
	private Lugar lugar;
	
	public void ingresarTag() {
		Boolean verificado = verificarDatos();
		if (verificado == true) {
			try {
				
				boolean ingresado = miLugar.ingresarTag(placeId,tag);
				if(ingresado==true) {
				
					//Mostrar mansaje de que se guardo la imagen
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Exito");
					alert.setHeaderText("Se ha agregado el lugar al tag");

					alert.showAndWait();

					tagTextField.setText(null);
					
				}else if(ingresado==false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Error al agregar el lugar");
					alert.setContentText("El lugar ya se ha agregado a ese tag");

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
			alert.setContentText("Verifica que tu tag empiece con '#'");

			alert.showAndWait();
		}
	}
	
	public void finalizar() {
		
		main = new Main();
		main.changeToAdmin();
		
	}
	
	public Boolean verificarDatos() {
		try {
			miLugar = new Lugaresdb();		
			tag = tagTextField.getText();
			if(!tag.substring(0,1).equals("#"))
				return false;
			placeId = lugar.getPlaceId();
		    return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
	
	
	/**
	 * @return the lugar
	 */
	public Lugar getLugar() {
		return lugar;
	}

	/**
	 * @param lugar the lugar to set
	 */
	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	@FXML
    public void initialize() {
		 lugar = new Lugar();
		
    }
	
	public void llenarInfoLugar() {
		nameLabel.setText(lugar.getPlaceName());
		countryLabel.setText(lugar.getPlaceCountry());
		imageContainer.setImage(lugar.getPlaceImage());
	}
	
}
