/*
 * Author: Kingsley Oteng-Amoako
 */

/**
 *
 * @author kingsley-k-oteng-amoako
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Popup;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
 
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
   

public class NewClass extends Application {
   
    // stores words in area and their count with the text
    // usage: errors.put("difficlt", 3);
    Map<String, Integer> errors = new HashMap<>();
    
    @Override
    public void start(Stage stage) throws Exception
    {
        
        Parent root = FXMLLoader.load(getClass().getResource("/FXML.fxml"));
        
        
        Scene scene = new Scene(root, 800, 430);
        
        stage.setTitle("Language Checker");
        stage.setScene(scene);
        stage.show();
    }
   
    // Main Method
    public static void main(String args[])
    {
        
        // launch the application
        launch(args);
    }
}