package GUI;

import java.io.File;
import java.io.IOException;

import Logic.DocHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class test extends Application {
	private File file1;
	private File file2;
    @FXML
    private Button browseBtn;
    private TextField textfield1;
    private TextField textfield2;
    private Button browseBtn1;
    private Stage primaryStage;
	@Override
	public void start(Stage stage) throws IOException {
		primaryStage = stage;
	      Parent root = FXMLLoader.load(getClass().getResource("Test.fxml"));
	      
	        Scene scene = new Scene(root, 600, 400);
	    
	        primaryStage.setTitle("FXML Welcome");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
    @FXML
    void ButtonHandler(ActionEvent event) {
    	FileChooser filechooser = new FileChooser();
    	filechooser.setTitle("Open Resource File");
    	file1 = filechooser.showOpenDialog(primaryStage);
    	textfield1.setText(file1.getAbsolutePath());
    }
    @FXML
    void ButtonHandler1(ActionEvent event){
    	FileChooser filechooser = new FileChooser();
    	filechooser.setTitle("Open Resource File");
    	file2 = filechooser.showOpenDialog(primaryStage);
    	textfield2.setText(file2.getAbsolutePath());
    }
    @FXML
    void ButtonHandler2(ActionEvent event){
		DocHandler extractor = new DocHandler(file1,file2);
    }

}
