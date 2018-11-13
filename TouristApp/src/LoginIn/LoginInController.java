package LoginIn;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import DataBase.Usuariosdb;
import application.Main;
import application.usuarioseditController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class LoginInController {

	@FXML
    private Main main;
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField passwordTextField;
	
	private String name;
	private String password;
	private Usuariosdb miUsuario;
	
	public void login(ActionEvent event) throws IOException {	
		Boolean verificado = verificarDatos();
		if (verificado == true) {
			try {
				//Se guarda la informacion del usuario
				ResultSet usuarioEnSesion = miUsuario.buscarUsuario(name,password);
				if(usuarioEnSesion != null) {
					Usuario userLoggedIn = new Usuario();
					userLoggedIn.ingresarUsuario(usuarioEnSesion);
					
					//Se buscan los tags seguidos por el usuario
					ResultSet tagsSeguidosPorUsuario = miUsuario.buscarTagsSeguidosPorUsuario(userLoggedIn.getUserId());
					if(tagsSeguidosPorUsuario!=null) {
						userLoggedIn.ingresarTags(tagsSeguidosPorUsuario);
					}
					
					if (userLoggedIn.getAccountType() == 1) {
						
						main = new Main();
						main.changeToAdmin();
						
					}else if (userLoggedIn.getAccountType() == 2) {
						
						main = new Main();
						main.changeToUserEdit(userLoggedIn);
						
					}
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Error al iniciar sesion");
					alert.setContentText("Verifica que los datos esten correctos");
					
					alert.showAndWait();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Error al iniciar sesion");
				alert.setContentText("Verifica que los datos esten correctos");

				alert.showAndWait();
			}
		}else if (verificado == false) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error en datos ingresado");
			alert.setContentText("Verifica tus datos ingresados");

			alert.showAndWait();
		}
	}
	public Boolean verificarDatos() {
		try {
			miUsuario = new Usuariosdb();
			name = nameTextField.getText();	
			if(name==null)
				return false;
			password = passwordTextField.getText();
			if(password==null)
				return false;
		    return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	public void crearCuenta(ActionEvent event) throws IOException {	
		Parent newScene = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
		Scene scene = new Scene(newScene,400,550);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
}
	
