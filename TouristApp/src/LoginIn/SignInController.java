package LoginIn;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import DataBase.Lugaresdb;
import DataBase.Usuariosdb;
import application.Main;
import application.usuarioseditController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SignInController implements Initializable {

	private Main main;
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField phoneTextField;
	@FXML
	private TextField passwordTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private ComboBox<String> tipeComboBox;
	@FXML
	private DatePicker birthDay;
	
	private String name;
	private int phone;
	private int tipo;
	private String password;
	private String email;
	private Date birth;
	
	private Usuariosdb miUsuario;
	
	public void crearCuenta(ActionEvent event) throws IOException {	
		Boolean verificado = verificarDatos();
		if (verificado == true) {
			try {
				boolean verifCrearUsuario = miUsuario.crearUsuario(name,password,email,phone,tipo,birth);
				if(verifCrearUsuario == true) {
					//Se guarda la informacion del usuario
					ResultSet usuarioEnSesion = miUsuario.buscarUsuario(name,password);
					Usuario userLoggedIn = new Usuario();
					userLoggedIn.ingresarUsuario(usuarioEnSesion);
					
					if (userLoggedIn.getAccountType() == 1) {
						
						main = new Main();
						main.changeToAdmin();
						
					}else if (userLoggedIn.getAccountType() == 2) {
						main = new Main();
						main.changeToUserEdit(userLoggedIn);
						
					}
				}else if(verifCrearUsuario == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("El nomre de usario ya existe");
					alert.setContentText("cambia el nombre del usuario para continuar");
					
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
			alert.setHeaderText("Error en datos ingresado");
			alert.setContentText("Verifica tus datos ingresados");

			alert.showAndWait();
		}
	}
	public Boolean verificarDatos() {
		try {
			miUsuario = new Usuariosdb();
			name = nameTextField.getText();
			email = emailTextField.getText();	
			password = passwordTextField.getText();
		    phone = Integer.parseInt(phoneTextField.getText());
		    //se encuentra la opcion seleccionada en el combobox
		    String tipoString = tipeComboBox.getValue();
		    if (tipoString.equals("Usuario Final")) {
		    	tipo = 2;
		    }else if(tipoString.equals("Administrador")){
		    	tipo = 1;
		    }
		    //se encuentra la fecha
			birth = new Date();
			LocalDate localDate = birthDay.getValue();
			birth = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		    return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public void initialize(URL url, ResourceBundle rb) {
		tipeComboBox.getItems().addAll("Usuario Final","Administrador");
	}  
	
}
	
