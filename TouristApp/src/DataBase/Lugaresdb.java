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

public class Lugaresdb {

	private Conexion miConexion;
	
	public Lugaresdb() throws SQLException {
		// TODO Auto-generated constructor stub
		miConexion = new Conexion();
	}
	
	public void ingresarLugar(String name, String country, String imagePath) throws Exception {
		Connection con = miConexion.getConexion();
		//Convertir imagen a BLOB
		File newFile = new File(imagePath);
		BufferedImage originalImage= ImageIO.read(newFile);
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ImageIO.write(originalImage, "jpg", baos );
		byte[] imageInByte=baos.toByteArray();
		Blob image = new SerialBlob(imageInByte);
		
		//Guardar los datos
		Statement statement = con.createStatement();
		PreparedStatement pStatement = con.prepareStatement("INSERT INTO LUGARES (placeName, placeCountry, placeImage)"+"values(?,?,?)");
		pStatement.setString(1, name);
		pStatement.setString(2, country);
		pStatement.setBlob(3, image);
		pStatement.executeUpdate();
	}

}
