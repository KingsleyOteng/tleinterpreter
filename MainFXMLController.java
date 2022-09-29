/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
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
        
       label_slot_status.setText("OK");
        label_slot_status.setTextFill(Color.web("#228B22"));
    }
    
     @FXML
    private void updateObserverStatus() {
        
       label_observer_status.setText("OK");
       label_observer_status.setTextFill(Color.web("#228B22"));
    }
    
}
