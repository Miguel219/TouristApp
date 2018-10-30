package LoginIn;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataBase.Usuariosdb;
import administrador.Lugar;
import application.Tag;
import javafx.scene.image.Image;

public class Usuario {

	private int userId;
	private String userName;
	private String userPassword;
	private int userPhone;
	private String email;
	private int accountType;
	private int age;
	private ArrayList<Tag> tagList;
	private ArrayList<Lugar> placeList;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
		userId = 0;
		userName = "";
		userPassword = "";
		userPhone = 0;
		email = "";
		accountType = 0;
		age = 0;
		tagList = new ArrayList<Tag>();
		placeList = new  ArrayList<Lugar>();
	}
	//Ingresa a un usuario en especifico
	public void ingresarUsuario(ResultSet usuario) throws SQLException {
		
		Date birthDate = null;
		java.util.Date fechaActual = new java.util.Date();
		
		if (usuario.first()) {
			userId = usuario.getInt("userId");
			userName = usuario.getString("userName");
			userPassword = usuario.getString("userPassword");
			userPhone = usuario.getInt("userPhone");
			email = usuario.getString("email");
			accountType = usuario.getInt("accountType");
			birthDate = usuario.getDate("birthDate");
			age = (fechaActual.getYear()-birthDate.getYear());
		}
		
	}
	//Ingresa los tags seguidos por el usuario
	public void ingresarTags(ResultSet tags) throws SQLException {
		
		do {
			int tagId = tags.getInt("tagId");
			String tagName = tags.getString("tag");
			//Se guarda el objeto modelo 			
			Tag tagModelo = new Tag();
			tagModelo.setTag(tagName);
			tagModelo.setTagId(tagId);
			//Se agrega al arraylist
			tagList.add(tagModelo);
		}while (tags.next());
		
	}
	
	//Ingresa los lugares seguidos por el usuario
		public void ingresarLugares(ResultSet lugares) throws SQLException {
			
			do {
				int placeId = lugares.getInt("placeId");
				String placeName = lugares.getString("placeName");
				String placeCountry = lugares.getString("placeCountry");
				Blob placeImageBlob = lugares.getBlob("placeImage");
				InputStream placeImageIS = placeImageBlob.getBinaryStream();
				Image imageModel = new Image(placeImageIS);
				Image placeImage = imageModel;
				
				//Se guarda el objeto modelo 			
				Lugar lugarModelo = new Lugar();
				lugarModelo.setPlaceId(placeId);
				lugarModelo.setPlaceCountry(placeCountry);
				lugarModelo.setPlaceName(placeName);
				lugarModelo.setPlaceImage(placeImage);
				
				//Se agrega al arraylist
				boolean encontrado = false;
				for (int i = 0; i < placeList.size(); i++) {
					if(placeId==placeList.get(i).getPlaceId()) {
						encontrado = true;
					}
				}
				if(encontrado == false)
					placeList.add(lugarModelo);
				
			}while (lugares.next());
			
		}
	
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the userPhone
	 */
	public int getUserPhone() {
		return userPhone;
	}

	/**
	 * @param userPhone the userPhone to set
	 */
	public void setUserPhone(int userPhone) {
		this.userPhone = userPhone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public  void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the accountType
	 */
	public int getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the tagList
	 */
	public ArrayList<Tag> getTagList() {
		return tagList;
	}
	/**
	 * @param tagList the tagList to set
	 */
	public void setTagList(ArrayList<Tag> tagList) {
		this.tagList = tagList;
	}
	/**
	 * @return the placeList
	 */
	public ArrayList<Lugar> getPlaceList() {
		return placeList;
	}
	/**
	 * @param placeList the placeList to set
	 */
	public void setPlaceList(ArrayList<Lugar> placeList) {
		this.placeList = placeList;
	}

	
}
