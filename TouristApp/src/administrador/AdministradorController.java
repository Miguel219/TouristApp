package administrador;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class AdministradorController {
	
	@FXML	
	private ImageView imageContainer;
	
	@FXML
	private TextField imagePathTextField;
	private Image image;

	public void showImage() throws FileNotFoundException {
	    String imagePath = imagePathTextField.getText();
	    image = new Image(new FileInputStream(imagePath));
	    imageContainer.setImage(image);
	  }
}
