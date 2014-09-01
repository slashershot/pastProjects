package GUI;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
import Logic.DocHandler;
import Logic.ThreadsHandler;

public class test extends Application {
	//javafx variables
    public Button browseBtn;
    public TextField textfield1;
    public TextField textfield2;
    public Button browseBtn1;
    public Button submitBtn;
    //end of javafx variables
	private File file1;
	private File file2;
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
    void SubmitButtonHandler(ActionEvent event){
    	ThreadsHandler stuff = new ThreadsHandler(file1,file2);
    	//System.out.println(stuff.stuff);
    	System.out.println(stuff.stuff1);
    }

}
