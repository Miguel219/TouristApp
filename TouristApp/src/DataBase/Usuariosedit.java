package DataBase;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

import javafx.scene.image.Image;


public class Usuariosedit {

private Conexion miConexion;
	
	public Usuariosedit() throws SQLException {
		
		miConexion = new Conexion();
	}
	
	public void guardarDatos(String nombre, Integer numero, String correoe) throws SQLException{
		Connection con = miConexion.getConexion();
				
		//Guardar los datos
		Statement statement = con.createStatement();
		PreparedStatement pStatement = con.prepareStatement("INSERT INTO USUARIOS (userName, userPhone, email)"+"values(?,?,?)");
		pStatement.setString(1, nombre);
		pStatement.setInt(2, numero);
		pStatement.setString(3, correoe);
	}

	
	
}
