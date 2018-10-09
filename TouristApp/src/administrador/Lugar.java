package administrador;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;

public class Lugar {

	private int placeId;
	private String placeName;
	private String placeCountry;
	private Image placeImage;

	public Lugar() {
		// TODO Auto-generated constructor stub
		placeId = 0;
		placeName = "";
		placeCountry = "";
		placeImage = null;
		
	}
	
	public void ingresarLugar(ResultSet lugar) throws SQLException, IOException {
		
		Blob placeImageBlob = null;
		
		if (lugar.first()) {
			placeId = lugar.getInt("placeId");
			placeName = lugar.getString("placeName");
			placeCountry = lugar.getString("placeCountry");
			placeImageBlob = lugar.getBlob("placeImage");
			InputStream placeImageIS = placeImageBlob.getBinaryStream();
			Image imageModel = new Image(placeImageIS);
			placeImage = imageModel;
		}
	}

	/**
	 * @return the placeId
	 */
	public int getPlaceId() {
		return placeId;
	}

	/**
	 * @param placeId the placeId to set
	 */
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	/**
	 * @return the placeName
	 */
	public String getPlaceName() {
		return placeName;
	}

	/**
	 * @param placeName the placeName to set
	 */
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	/**
	 * @return the placeCountry
	 */
	public String getPlaceCountry() {
		return placeCountry;
	}

	/**
	 * @param placeCountry the placeCountry to set
	 */
	public void setPlaceCountry(String placeCountry) {
		this.placeCountry = placeCountry;
	}

	/**
	 * @return the placeImage
	 */
	public Image getPlaceImage() {
		return placeImage;
	}

	/**
	 * @param placeImage the placeImage to set
	 */
	public void setPlaceImage(Image placeImage) {
		this.placeImage = placeImage;
	}

}
