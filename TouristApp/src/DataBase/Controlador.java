package DataBase;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controlador {
	@FXML
    private TextField user;

    @FXML
    private PasswordField pass;

    @FXML
    private Button login;

    @FXML
    private Button sign;
    
    private Login log = new Login();
    public void Log() {
    	user = new TextField();
    	pass = new PasswordField();
    	String usercurrent = user.getText();
    	String passcurrent = pass.getText();
    	log.conec(usercurrent, passcurrent);
    }

}
