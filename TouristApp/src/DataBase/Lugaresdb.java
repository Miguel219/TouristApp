package DataBase;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

import javafx.scene.image.Image;

public class Lugaresdb {

	private Conexion miConexion;
	
	public Lugaresdb() throws SQLException {
		// TODO Auto-generated constructor stub
		miConexion = new Conexion();
	}
	//Ingresa un lugar a base de datos
	public boolean ingresarLugar(String name, String country, String imagePath) throws Exception {
		Connection con = miConexion.getConexion();
		
		//Buscar la imagen en base de datos
		PreparedStatement pStatement = con.prepareStatement("SELECT * FROM LUGARES WHERE placeName = '"+name+"' AND  placeCountry = '"+country+"'");
		ResultSet result = pStatement.executeQuery();
		
		if (!result.first()) {
			//Convertir imagen a BLOB
			File newFile = new File(imagePath);
			BufferedImage originalImage= ImageIO.read(newFile);
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos );
			byte[] imageInByte=baos.toByteArray();
			Blob image = new SerialBlob(imageInByte);
			
			//Guardar los datos
			pStatement = con.prepareStatement("INSERT INTO LUGARES (placeName, placeCountry, placeImage)"+"values(?,?,?)");
			pStatement.setString(1, name);
			pStatement.setString(2, country);
			pStatement.setBlob(3, image);
			pStatement.executeUpdate();
			return true;
		}
		return false;
	}
	//Busca un lugar por nombre y ciudad
	public ResultSet buscarLugarPorId(int placeId) throws Exception {
		Connection con = miConexion.getConexion();	
		
		//Buscar la imagen en base de datos
		PreparedStatement pStatement = con.prepareStatement("SELECT * FROM LUGARES WHERE placeId = '"+placeId+"'");
		ResultSet result = pStatement.executeQuery();
		if (result.first())
			return result;
		return null;
	}
	
	//Busca un lugar por Id
		public ResultSet buscarLugar(String name,String country) throws Exception {
			Connection con = miConexion.getConexion();	
			
			//Buscar la imagen en base de datos
			PreparedStatement pStatement = con.prepareStatement("SELECT * FROM LUGARES WHERE placeName = '"+name+"' AND  placeCountry = '"+country+"'");
			ResultSet result = pStatement.executeQuery();
			if (result.first())
				return result;
			return null;
		}
	
	//Busca un lugar por Tag
		public ResultSet buscarLugarPorTag(int tagId) throws Exception {
			Connection con = miConexion.getConexion();	
			
			//Buscar la imagen en base de datos
			PreparedStatement pStatement = con.prepareStatement("SELECT * FROM LUGARES INNER JOIN RelacionTagsLugares ON Lugares.placeId = RelacionTagsLugares.placeId WHERE RelacionTagsLugares.tagId = '"+tagId+"'");
			ResultSet result = pStatement.executeQuery();
			if (result.first())
				return result;
			return null;
		}
	
	//Hace la relacion entre un tag y un lugar
	public boolean ingresarTag(int placeId, String tag) throws Exception {
		Connection con = miConexion.getConexion();
		
		//Buscar el tag en base de datos
		PreparedStatement pStatement = con.prepareStatement("SELECT * FROM Tags WHERE tag = '"+tag+"'");
		ResultSet result = pStatement.executeQuery();
		
		if (!result.first()) {
			
			//Guardar los datos
			pStatement = con.prepareStatement("INSERT INTO Tags (tag)"+"values(?)");
			pStatement.setString(1, tag);
			pStatement.executeUpdate();
			
			//Buscar el Id del tag
			pStatement = con.prepareStatement("SELECT * FROM Tags WHERE tag = '"+tag+"'");
			result = pStatement.executeQuery();
		}
		
		if(result.first()) {
			//Se guarda el Id del tag
			int tagId = result.getInt("tagId");
			
			//Buscar la relacion del Id del tag con la de lugares
			pStatement = con.prepareStatement("SELECT * FROM RelacionTagsLugares WHERE placeId = '"+placeId+"' AND  tagId = '"+tagId+"'");
			ResultSet resultRelacion = pStatement.executeQuery();
			
			if(!resultRelacion.first()) {
				
				//Guardar la relacion
				pStatement = con.prepareStatement("INSERT INTO RelacionTagsLugares (placeId,tagId)"+"values(?,?)");
				pStatement.setInt(1, placeId);
				pStatement.setInt(2, tagId);
				pStatement.executeUpdate();
				return true;
			}
			
		}
		//Significa que este tag ya esta relacionado con ese lugar
		return false;
		
	}
	
	//Hace la relacion entre un tag y un lugar
	public boolean ingresarComentario(int placeId, int userId, String comment, int qualification) throws Exception {
		Connection con = miConexion.getConexion();
		
		//Buscar la relacion en base de datos
		PreparedStatement pStatement = con.prepareStatement("SELECT * FROM Relacion WHERE userId = '"+userId+"' AND placeId = '"+placeId+"'");
		ResultSet result = pStatement.executeQuery();
		
		if (!result.first()) {
			
			java.util.Date currentDate = new java.util.Date();
			
			Date sqlCurrentDate = new Date(currentDate.getTime());
			
			//Guardar el comentario
			pStatement = con.prepareStatement("INSERT INTO Comentarios (comment,commentDate)"+"values(?,?)");
			pStatement.setString(1, comment);
			pStatement.setDate(2, sqlCurrentDate);
			pStatement.executeUpdate();
			
			pStatement = con.prepareStatement("SELECT * FROM Comentarios WHERE comment = '"+comment+"' AND commentDate = '"+sqlCurrentDate+"'");
			ResultSet resultComment = pStatement.executeQuery();
			
			//Guardar calificacion
			pStatement = con.prepareStatement("INSERT INTO Calificaciones (qualification,qualificationDate)"+"values(?,?)");
			pStatement.setInt(1, qualification);
			pStatement.setDate(2, sqlCurrentDate);
			pStatement.executeUpdate();
			
			pStatement = con.prepareStatement("SELECT * FROM Calificaciones WHERE qualification = '"+qualification+"' AND qualificationDate = '"+sqlCurrentDate+"'");
			ResultSet resultQualification = pStatement.executeQuery();
			
			if((resultComment.first())&&(resultQualification.first())) {
				
				int commentId = resultComment.getInt("commentId");
				int qualificationId = resultQualification.getInt("qualificationId");
				//Guardar la relacion
				pStatement = con.prepareStatement("INSERT INTO Relacion (userId,placeId,commentId,qualificationId)"+"values(?,?,?,?)");
				pStatement.setInt(1, userId);
				pStatement.setInt(2, placeId);
				pStatement.setInt(3, commentId);
				pStatement.setInt(4, qualificationId);
				pStatement.executeUpdate();
				return true;
			}
		}
		return false;
		
	}
	
	public void eliminarlugar(String name) throws SQLException {
		Connection con = miConexion.getConexion();
		Statement statement = con.createStatement();
		String q = "DELETE FROM LUGARES WHERE placeName = '" + name + "'";
		statement.executeUpdate(q);
	}
}
