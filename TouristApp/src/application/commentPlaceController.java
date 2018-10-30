package application;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import DataBase.Lugaresdb;
import LoginIn.Usuario;
import administrador.Lugar;
import application.Main;

public class commentPlaceController {
	
	private Main main;
	
	@FXML	
	private ImageView imageContainer;
	@FXML
	private TextField commentTextField;
	@FXML
	private Label nameLabel;
	@FXML
	private Label countryLabel;
	@FXML
	private ImageView imageContainer1;
	@FXML
	private ImageView imageContainer2;
	@FXML
	private ImageView imageContainer3;
	@FXML
	private ImageView imageContainer4;
	@FXML
	private ImageView imageContainer5;
	@FXML 
	private Pane commentFlowPane;

	private int placeId;
	private String comment;
	private int calificacion;
	
	private Lugaresdb miLugar;
	private Lugar lugar;
	private Usuario userLoggedIn;
	
	public void ingresarComentario() {
		Boolean verificado = verificarDatos();
		if (verificado == true) {
			try {
				
				boolean ingresado = miLugar.ingresarComentario(placeId,userLoggedIn.getUserId(),comment,calificacion);
				if(ingresado==true) {
				
					//Mostrar mansaje de que se guardo la imagen
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Exito");
					alert.setHeaderText("Se ha agregado el comentario y la calificacion al lugar");

					alert.showAndWait();

					main = new Main();
					main.changeToHome(userLoggedIn);
					
				}else if(ingresado==false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Error al agregar el comentario");
					alert.setContentText("Ya tienes un comentario en el lugar");

					alert.showAndWait();					
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Error de conexion");
				alert.setContentText("Error al guardar en base de datos");

				alert.showAndWait();
			}
		}else if (verificado == false) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error en datos ingresados");
			alert.setContentText("Verifica que hayas ingresado todos los datos");

			alert.showAndWait();
		}
	}
	
	public void calificar(MouseEvent event) {
		
		ImageView currentButton = (ImageView)event.getSource();
		String imageId = currentButton.getId();
		
		if(imageId.equals("imageContainer1"))
			calificacion = 1;
		if(imageId.equals("imageContainer2"))
			calificacion = 2;
		if(imageId.equals("imageContainer3"))
			calificacion = 3;
		if(imageId.equals("imageContainer4"))
			calificacion = 4;
		if(imageId.equals("imageContainer5"))
			calificacion = 5;
		
		if((imageId.equals("imageContainer1")) || (imageId.equals("imageContainer2")) || (imageId.equals("imageContainer3")) || (imageId.equals("imageContainer4")) || (imageId.equals("imageContainer5")))
			imageContainer1.setImage(new Image(this.getClass().getResource("/application/Images/yellowStar.png").toString()));
		else
			imageContainer1.setImage(new Image(this.getClass().getResource("/application/Images/whiteStar.png").toString()));
		if((imageId.equals("imageContainer2")) || (imageId.equals("imageContainer3")) || (imageId.equals("imageContainer4")) || (imageId.equals("imageContainer5")))
			imageContainer2.setImage(new Image(this.getClass().getResource("/application/Images/yellowStar.png").toString()));
		else
			imageContainer2.setImage(new Image(this.getClass().getResource("/application/Images/whiteStar.png").toString()));
		if((imageId.equals("imageContainer3")) || (imageId.equals("imageContainer4")) || (imageId.equals("imageContainer5")))
			imageContainer3.setImage(new Image(this.getClass().getResource("/application/Images/yellowStar.png").toString()));
		else 
			imageContainer3.setImage(new Image(this.getClass().getResource("/application/Images/whiteStar.png").toString()));
		if((imageId.equals("imageContainer4")) || (imageId.equals("imageContainer5")))
			imageContainer4.setImage(new Image(this.getClass().getResource("/application/Images/yellowStar.png").toString()));
		else
			imageContainer4.setImage(new Image(this.getClass().getResource("/application/Images/whiteStar.png").toString()));
		if(imageId.equals("imageContainer5"))
			imageContainer5.setImage(new Image(this.getClass().getResource("/application/Images/yellowStar.png").toString()));
		else
			imageContainer5.setImage(new Image(this.getClass().getResource("/application/Images/whiteStar.png").toString()));
	}
	
	public Boolean verificarDatos() {
		try {
			miLugar = new Lugaresdb();		
			comment = commentTextField.getText();
			if((comment == null)||(calificacion == 0))
				return false;
			placeId = lugar.getPlaceId();
		    return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
	public void atras() {
		main = new Main();
		main.changeToHome(userLoggedIn);
	}
	
	
	/**
	 * @return the lugar
	 */
	public Lugar getLugar() {
		return lugar;
	}

	/**
	 * @param lugar the lugar to set
	 */
	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}
	
	/**
	 * @return the userLoggedIn
	 */
	public Usuario getUserLoggedIn() {
		return userLoggedIn;
	}

	/**
	 * @param userLoggedIn the userLoggedIn to set
	 */
	public void setUserLoggedIn(Usuario userLoggedIn) {
		this.userLoggedIn = userLoggedIn;
	}

	@FXML
    public void initialize() {
		 lugar = new Lugar();
		calificacion = 0;
    }
	
	public void verComentarios() {
		commentFlowPane.getChildren().clear();
		try {
			miLugar = new Lugaresdb();
			ResultSet result = miLugar.buscarComentarios(lugar.getPlaceId());
			if(result!=null) {
				lugar.ingresarComentarios(result);
				ArrayList<Comments> comentarios = lugar.getCommentList();
				
				for (int i = 0; i < comentarios.size(); i++) {
					
					String userName = comentarios.get(i).getUserName();
					String comment = comentarios.get(i).getComment();
					int qualification = comentarios.get(i).getQualification();
					Date date = comentarios.get(i).getCommentDate();
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
	                String strDate = dateFormat.format(date);  
					
					Label userLabel = new Label();
					userLabel.setFont(new Font(12));
					userLabel.setText(userName+": ");
					
					Label commentLabel = new Label();
					commentLabel.setFont(new Font(10));
					commentLabel.setText(comment+"  ");
					
					Region p = new Region();
					p.setPrefSize(347.0, 4.0);
					
					Label qualificationLabel = new Label();
					qualificationLabel.setFont(new Font(10));
					qualificationLabel.setText("Calificación: "+qualification+"/5 ");

					Region p1 = new Region();
					p1.setPrefSize(347.0, 4.0);

					Label dateLabel = new Label();
					dateLabel.setFont(new Font(10));
					dateLabel.setText(strDate);
					
					Region p2 = new Region();
					p2.setPrefSize(347.0, 4.0);
					
					Line line = new Line(0, 0, 350, 0);
					
					Region p3 = new Region();
					p3.setPrefSize(347.0, 4.0);
					
					commentFlowPane.getChildren().add(userLabel);
					commentFlowPane.getChildren().add(commentLabel);
					commentFlowPane.getChildren().add(p);
					commentFlowPane.getChildren().add(qualificationLabel);
					commentFlowPane.getChildren().add(p1);
					commentFlowPane.getChildren().add(dateLabel);
					commentFlowPane.getChildren().add(p2);
					commentFlowPane.getChildren().add(line);
					commentFlowPane.getChildren().add(p3);
					
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	} 
	
	public void llenarInfoLugar() {
		
		imageContainer1.setImage(new Image(this.getClass().getResource("/application/Images/whiteStar.png").toString()));
		imageContainer2.setImage(new Image(this.getClass().getResource("/application/Images/whiteStar.png").toString()));
		imageContainer3.setImage(new Image(this.getClass().getResource("/application/Images/whiteStar.png").toString()));
		imageContainer4.setImage(new Image(this.getClass().getResource("/application/Images/whiteStar.png").toString()));
		imageContainer5.setImage(new Image(this.getClass().getResource("/application/Images/whiteStar.png").toString()));
		
		nameLabel.setText(lugar.getPlaceName());
		countryLabel.setText(lugar.getPlaceCountry());
		imageContainer.setImage(lugar.getPlaceImage());
		
		verComentarios();
		
	}
	
}
