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
	
	//Crea un usuario
	public boolean crearUsuario(String name, String password, String email, int phone, int tipe, Date birth) throws Exception {
		Connection con = miConexion.getConexion();
		//convertir fecha

		java.sql.Date sqlBirth = new java.sql.Date(birth.getTime());
		
		//Buscar el usuario los datos
			PreparedStatement pStatement = con.prepareStatement("SELECT * FROM USUARIOS WHERE userName = '"+name+"'");
			ResultSet result = pStatement.executeQuery();
		if (!result.first()) {
			//Guardar los datoss
			pStatement = con.prepareStatement("INSERT INTO USUARIOS (userName, userPassword, userPhone, email, accountType, birthDate)"+"values(?,?,?,?,?,?)");
			pStatement.setString(1, name);
			pStatement.setString(2, password);
			pStatement.setInt(3, phone);
			pStatement.setString(4, email);
			pStatement.setInt(5, tipe);
			pStatement.setDate(6, sqlBirth);
			pStatement.executeUpdate();
			return true;
		}
		return false;
	}

	//Busca que exista el usuario
	public ResultSet buscarUsuario(String name,String password) throws Exception {
		Connection con = miConexion.getConexion();		
		//Buscar el usuario los datos
		PreparedStatement pStatement = con.prepareStatement("SELECT * FROM USUARIOS WHERE userName = '"+name+"' AND userPassword = '"+password+"'");
		ResultSet result = pStatement.executeQuery();
		if (!result.first()) {
			return null;
		}
		return result;
	}
	
	//edita el usuario
	public boolean editarUsuario(Integer id, String nombre, Integer numero, String correoe) throws SQLException{
		Connection con = miConexion.getConexion();
		
		//Buscar que el nombre
		PreparedStatement pStatement = con.prepareStatement("SELECT * FROM USUARIOS WHERE userName = '"+nombre+"' AND userId != '"+id+"'");
		ResultSet result = pStatement.executeQuery();
		if (!result.first()) {
			//Guardar los datos
			pStatement = con.prepareStatement("UPDATE USUARIOS Set userName = '"+nombre+"', userPhone = '"+numero+"', email = '"+correoe+"' WHERE userId = '"+id+"'");
			pStatement.executeUpdate();
			return true;
		}
		return false;
	}
	
	//Busca un tag especifico 
	public ResultSet buscarTag(String tagName) throws SQLException {
		Connection con = miConexion.getConexion();
		//Buscar la relacion del Id del tag con la de lugares
		PreparedStatement pStatement = con.prepareStatement("SELECT * FROM Tags WHERE tag LIKE '%"+tagName+"%'");
		ResultSet result = pStatement.executeQuery();
		if (result.first())
			return result;
		return null;
	}
	
	//Busca los tags seguidos por un usuario  
	public ResultSet buscarTagsSeguidosPorUsuario(int userId) throws SQLException {
		Connection con = miConexion.getConexion();
		//Buscar la relacion del Id del tag con la de lugares
		PreparedStatement pStatement = con.prepareStatement("SELECT * FROM Tags INNER JOIN RelacionTagsUsuarios ON Tags.tagId = RelacionTagsUsuarios.tagId WHERE RelacionTagsUsuarios.userId = '"+userId+"'");
		ResultSet result = pStatement.executeQuery();
		if (result.first())
			return result;
		return null;
	}
	
	//Relaciona a un tag con un usuario  
		public boolean seguirTag(int tagId,int userId) throws SQLException {
			Connection con = miConexion.getConexion();
			//Buscar si la relacion existe 
			PreparedStatement pStatement = con.prepareStatement("SELECT * FROM RelacionTagsUsuarios WHERE tagId = '"+tagId+"' AND userId = '"+userId+"'");
			ResultSet result = pStatement.executeQuery();
			if (!result.first()) {
				//Guardar los datoss
				pStatement = con.prepareStatement("INSERT INTO RelacionTagsUsuarios (tagId, userId)"+"values(?,?)");
				pStatement.setInt(1, tagId);
				pStatement.setInt(2, userId);
				pStatement.executeUpdate();
				return true;
			}
			return false;
		}
}
