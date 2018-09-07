package userset;
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

import DataBase.Usuariosedit;


public class usuarioseditController {

	@FXML	
	private TextField username;
	@FXML
	private TextField phone;
	@FXML
	private TextField email;


	private String nombre;
	private Integer numero;
	private String correoe;
	
	private Usuariosedit miedit;
	
	public void guardarUsuario() {
		Boolean verificado = verificarDatos();
		if (verificado == true) {
			try {
				miedit.guardarDatos(nombre,numero,correoe);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error");
				alert.setHeaderText("Error de conexion");
				alert.setContentText("Error al guardar en base de datos");

				alert.showAndWait();
			}
		}else if (verificado == false) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Error en datos ingresado");
			alert.setContentText("Verifica tus datos ingresados");

			alert.showAndWait();
		}
	}
	
	
	
	public Boolean verificarDatos() {
		try {
			miedit = new Usuariosedit();
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
	
}
