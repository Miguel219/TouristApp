package DataBase;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

import javafx.scene.image.Image;

public class Usuariosdb {

	private Conexion miConexion;
	
	public Usuariosdb() throws SQLException {
		// TODO Auto-generated constructor stub
		miConexion = new Conexion();
	}
	public void crearUsuario(String name, String password, String email, int phone, int tipe, Date birth) throws Exception {
		Connection con = miConexion.getConexion();
		//convertir fecha

		java.sql.Date sqlBirth = new java.sql.Date(birth.getTime());
		
		//Guardar los datoss
		PreparedStatement pStatement = con.prepareStatement("INSERT INTO USUARIOS (userName, userPassword, userPhone, email, accountType, birthDate)"+"values(?,?,?,?,?,?)");
		pStatement.setString(1, name);
		pStatement.setString(2, password);
		pStatement.setInt(3, phone);
		pStatement.setString(4, email);
		pStatement.setInt(5, tipe);
		pStatement.setDate(6, sqlBirth);
		pStatement.executeUpdate();
	}

	public int IniciarSesion(String name,String password) throws Exception {
		int accountType = 0;
		Connection con = miConexion.getConexion();		
		//Guardar los datos
		PreparedStatement pStatement = con.prepareStatement("SELECT * FROM USUARIOS WHERE userName = '"+name+"' AND userPassword = '"+password+"'");
		ResultSet result = pStatement.executeQuery();
		if (result.first()) {
			accountType = result.getInt("accountType");
		}
		return accountType;
	}
	
	public void editarUsuario(String nombre, Integer numero, String correoe) throws SQLException{
		Connection con = miConexion.getConexion();
				
		//Guardar los datos
		PreparedStatement pStatement = con.prepareStatement("INSERT INTO USUARIOS (userName, userPhone, email)"+"values(?,?,?)");
		pStatement.setString(1, nombre);
		pStatement.setInt(2, numero);
		pStatement.setString(3, correoe);
	}
}
