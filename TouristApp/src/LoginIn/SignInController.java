package LoginIn;

import java.io.FileInputStream;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import DataBase.Lugaresdb;
import DataBase.Usuariosdb;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;

public class SignInController implements Initializable {

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
	
	public void crearCuenta() {
		Boolean verificado = verificarDatos();
		if (verificado == true) {
			try {
				miUsuario.crearUsuario(name,password,email,phone,1,birth);
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
	
