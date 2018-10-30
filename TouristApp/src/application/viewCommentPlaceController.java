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

public class viewCommentPlaceController {
	
	private Main main;
	
	@FXML	
	private ImageView imageContainer;
	@FXML
	private Label nameLabel;
	@FXML
	private Label countryLabel;
	@FXML 
	private Pane commentFlowPane;

	
	private Lugaresdb miLugar;
	private Lugar lugar;
	private Usuario userLoggedIn;
	
	public void atras() {
		main = new Main();
		main.changeToRecentPlace(userLoggedIn);
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
				
		nameLabel.setText(lugar.getPlaceName());
		countryLabel.setText(lugar.getPlaceCountry());
		imageContainer.setImage(lugar.getPlaceImage());
		
		verComentarios();
		
	}
	
}
