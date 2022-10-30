/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import java.net.URL;

/**
 * FXML Controller class
 *
 * @author terra6partner
 */
public class MainFXMLController implements Initializable {

    static int i = 1;
    static int y = 0;
    static int x = 0;

    @FXML
    private TextArea satellite;
    @FXML
    private TextArea launch_year;
    @FXML
    private TextArea launch_number;
    @FXML
    private TextArea day;
    @FXML
    private TextArea month;
    @FXML
    private TextArea epoch_time;
    @FXML
    private TextArea epoch_date;
    @FXML
    private TextArea launch_number_1;
    @FXML
    private TextArea launch_number_2;
    @FXML
    private TextArea piece_number;
    
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
    
    private static String line_one_array[] = new String[20];
    private static String line_two_array[] = new String[20];
    
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
        //String x = line1_fields.getText();
        //Pattern p=Pattern.compile("\\s");  
        //String[] str=p.split(x);  
        
        //for(String str1:str)  
        //{  
       //    count++; 
       // }     
       //Pattern replace = Pattern.compile("[2]");
       //Matcher matcher2 = replace.matcher(lf1.getText());
       //System.out.println(lf1.getText());
       //System.out.println(matcher2.replaceAll("\t"));
       
       //String text    =
       // "This is the text to be searched " +
       // "for occurrences of the http:// pattern.";
     
       String tleLineOne = lf1.getText();
       String tleLineTwo = lf2.getText();

        //trim both lines of text
       tleLineOne = tleLineOne.substring(3,tleLineOne.length());
       tleLineTwo = tleLineTwo.substring(3,tleLineTwo.length());

        // extract from tle line 1
       Pattern pattern = Pattern.compile("((\\-)?(\\+)?(\\s)?[1234567890]+)([.1234567890]+)");
       Matcher matcher = pattern.matcher(tleLineOne);

        while(matcher.find())
        {

            x = y;
            y = matcher.end();
            //System.out.println("x"+x);
            //System.out.println("y"+y);
            //System.out.println(">"+tleLineOne.substring(x+1,y));
            line_one_array[i]= (tleLineOne.substring(x+1,y));
            i++;
        }
        
        satellite.setText((line_one_array[1]));
        int launch_y = Integer.valueOf(line_one_array[2].substring(1,3));
        int launch_num = Integer.valueOf(line_one_array[2].substring(4,6));
        launch_number.setText("19"+String.valueOf(launch_num));
        if (launch_y < 99)
        {
            launch_year.setText("19"+String.valueOf(launch_y));
        }
        else
        {
            launch_year.setText("20"+String.valueOf(launch_y));
        };
        day.setText((line_one_array[3]));
        launch_number_1.setText((line_one_array[4]));
        launch_number_2.setText((line_one_array[5]));
        piece_number.setText((line_one_array[6]));
        epoch_date.setText((line_one_array[7]));
        epoch_time.setText((line_one_array[8]));
        
        // extract from tle line 2
        Matcher matcher_next = pattern.matcher(tleLineTwo);

        i = 1; y = 0; x = 0;
        while(matcher_next.find())
        {
            x = y;
            y = matcher_next.end();
            //System.out.println("x"+x);
            //System.out.println("y"+y);
            //System.out.println("><><"+tleLineTwo.substring(x+1,y));
            line_two_array[i]= (tleLineTwo.substring(x+1,y));
            i++;
        }
        label_slot_status.setText("OK - Set");
        label_slot_status.setTextFill(Color.web("#228B22"));

    }
    
    @FXML
    private void updateObserverStatus() {
        
       label_observer_status.setText("OK - Set");
       label_observer_status.setTextFill(Color.web("#228B22"));
    }
    
}
