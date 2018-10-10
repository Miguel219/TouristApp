package mostrar;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import DataBase.Conexion;
import DataBase.Lugaresdb;
import DataBase.Conexion;
import administrador.Lugar;
import application.Main;

public class controladormostrar {

	@FXML	
	private ImageView imageContainer;
	@FXML
	private TextField imagePathTextField;
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField countryTextField;
	@FXML
	private TableView table;

	private String name;
	private String country;
	private String imagePath;
	private Image image;
	
	private Lugaresdb miLugar;
	private Lugar lugar;
	
	public void mostrarlugares() {
		
		
		
	}
	
}
