/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author terra6partner
 */
public class MainFXMLController implements Initializable {

    @FXML
    private Label label_slot_status;
    
    @FXML
    private Label label_observer_status;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //
    }    
    
    @FXML
    private void printHelloWorld(ActionEvent event) {
        event.consume();
        System.out.println("Hello, World!");
        label_slot_status.setText("Hello World");
    }
    
    @FXML
    private void updateLoadStatus() {
        int count = 0;
        Pattern p=Pattern.compile("\\s");  
        String[] str=p.split("Pattern Split Method in Java regex");  
        
        for(String str1:str)  
        {  
           count++; 
        }     
        
        System.out.println("total: "+count);
       label_slot_status.setText("OK - Set");
        label_slot_status.setTextFill(Color.web("#228B22"));
    }
    
     @FXML
    private void updateObserverStatus() {
        
       label_observer_status.setText("OK - Set");
       label_observer_status.setTextFill(Color.web("#228B22"));
    }
    
}
