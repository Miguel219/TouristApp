package administrador;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DataBase.Lugaresdb;
import DataBase.Usuariosdb;
import application.Main;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LugaresController {
	
	private Main main;
	
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField countryTextField;
	
	private String namePlace;
	private String countryPlace;
	
	private Lugaresdb miLugar;
	private Lugar lugar;
	
	public void agregarTag() {
		Boolean verificado = verificarDatos();
		if (verificado == true) {
			try {
				ResultSet result = miLugar.buscarLugar(namePlace,countryPlace);
				if(result!=null) {
					
					lugar = new Lugar();
					lugar.ingresarLugar(result);
					
					main = new Main();
					main.changeToTags(lugar);
					
				}else if(result==null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Error al encontrar el lugar");
					alert.setContentText("El lugar no existe en la base de datos");

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
	
	public Boolean verificarDatos() {
		try {
			miLugar = new Lugaresdb();
			namePlace = nameTextField.getText();	
			if(namePlace==null)
				return false;
			countryPlace = countryTextField.getText();
			if(countryPlace==null)
				return false;
		    return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
}
