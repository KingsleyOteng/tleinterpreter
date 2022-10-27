/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author terra6partner
 */
public class MainFXMLController implements Initializable {

    @FXML
    private TextArea lf1;
    
    @FXML
    private TextArea lf2;
    
    @FXML
    private TextArea lf3;
    
    @FXML
    private Label label_slot_status;
    
    @FXML
    private Label label_observer_status;
    
    @FXML
    private Button btn_load_element;
    
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
        System.out.println("");
        label_slot_status.setText("");
    }
    
    @FXML
    private void updateLoadStatus() {
        int count = 0;
        //String x = line1_fields.getText();
        //Pattern p=Pattern.compile("\\s");  
        //String[] str=p.split(x);  
        
        //for(String str1:str)  
        //{  
       //    count++; 
       // }     
       Pattern replace = Pattern.compile("[2]");
       Matcher matcher2 = replace.matcher(lf1.getText());
       System.out.println(lf1.getText());
       System.out.println(matcher2.replaceAll("\t"));
       
       String text    =
        "This is the text to be searched " +
        "for occurrences of the http:// pattern.";
     
String tleLineOne = lf1.getText();
String tleLineTwo = lf2.getText();

//trim both lines of text
tleLineOne = tleLineOne.substring(3,tleLineOne.length());
tleLineTwo = tleLineTwo.substring(3,tleLineTwo.length());

// extract from tle line 1
Pattern pattern = Pattern.compile("([1234567890]+)([.1234567890]+)");
Matcher matcher = pattern.matcher(tleLineOne);

int i = 1;
int y = 0;
int x = 0;
while(matcher.find())
{
    x = y;
    y = matcher.end();
    System.out.println("x"+x);
    System.out.println("y"+y);
    System.out.println(">"+tleLineOne.substring(x+1,y));
    i++;
}

// extract from tle line 2
Pattern pattern_next = Pattern.compile("([1234567890]+)([.1234567890]+)");
Matcher matcher_next = pattern.matcher(tleLineTwo);

i = 1;
y = 0;
x = 0;
while(matcher.find())
{
    x = y;
    y = matcher_next.end();
    System.out.println("x"+x);
    System.out.println("y"+y);
    System.out.println("><><"+tleLineTwo.substring(x+1,y));
    i++;
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
