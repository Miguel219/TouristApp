package DataBase;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.gluonhq.charm.glisten.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.fxml.Initializable;

public class LugaresControler implements Initializable {
	
	@FXML
	 private javafx.scene.control.TextField eliminar_text;

	@FXML
	 private Button eliminar_but;

	Lugaresdb funciones;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			funciones = new Lugaresdb();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminar() throws SQLException {
		funciones.eliminarlugar(eliminar_text.getText());
	}
	
	
}
