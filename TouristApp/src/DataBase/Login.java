package DataBase;

import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
public class Login {
	    
	    private Conexion miConexion;
		
		public Login() {
			// TODO Auto-generated constructor stub
		}
	    
	    public void conec(String user, String pass) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/t","","");
	    	Statement statement = con.createStatement();
	    	String sql = ("SELECT user, pass FROM Usuarios");
	    	ResultSet rs = statement.executeQuery(sql);
	    	while(rs.next()) {
	    		String us = rs.getString("userName");
	    		if (us == user) {
	    			String pas = rs.getString("userPassword");
	    			if(pass == pas) {
	    				
	    			}
	    		}
	    	}
	    	
	    }


}
