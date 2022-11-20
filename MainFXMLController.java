/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author terra6partner
 */

public class MainFXMLController implements Initializable {

    //ObservableList list = FXCollections.observableArrayList();
    String tleLineOne;
    String tleLineTwo;
    String launch_y;
    String launch_num;
    String launch_catalogue_sequence;

    int subLen;
    int i, y, x;
    int obs_y;
    int obs_day;
    int epochdate;

    double obs_hour;
    double obs_min;
    double obs_sec;
    double epochtime;

    @FXML
    private TextArea satellite;
    @FXML
    private TextArea observation_sec;
    @FXML
    private TextArea observation_year;
    @FXML
    private TextArea observation_day;
    @FXML
    private TextArea observation_hour;
   // @FXML
   // private TextArea observation_time;
    @FXML
    private TextArea launch_year;
    @FXML
    private TextArea launch_number;
    @FXML
    private TextArea lcsequence;
   // @FXML
   // private TextArea day;
   // @FXML
   // private TextArea month;
    @FXML
    private TextArea epoch_time;
    @FXML
    private TextArea epoch_date;
    //@FXML
    //private TextArea launch_number_1;
    //@FXML
   // private TextArea launch_number_2;
    //@FXML
   // private TextArea piece_number;
    @FXML
    private TextArea lf1;
    @FXML
    private TextArea lf2;
  //  @FXML
   // private TextArea lf3;
    @FXML
    private Label label_slot_status;
    @FXML
    private Label label_observer_status;
    @FXML
    private Button btn_load_element;
    @FXML
    private TextArea orbitinclination;
    @FXML
    private TextArea eccentricity;
    @FXML
    private TextArea rightascension;
    @FXML
    private TextArea elementnumber;
    @FXML
    private TextArea revolutionnumber;
    @FXML
    private TextArea ephemeristype;
    @FXML
    private TextArea meanmotion;
    @FXML
    private TextArea meananomaly;
    @FXML
    private TextArea perigree;
   // @FXML
   // private TextArea dragterm;
    @FXML
    private TextArea firsttimederiv;
    @FXML
    private TextArea secondtimederiv;
    @FXML
    private TextArea secondtimederivexp;
    @FXML
    private TextArea bstardrag;
    @FXML
    private TextArea bstardragexp;
    @FXML
    private ChoiceBox<String> choiceBox 
            = new ChoiceBox();
    @FXML
    private Label mount_label_1;
    @FXML
    private Label mount_label_2;
    @FXML
    private final MenuItem item1 = new MenuItem();

    private ObservableList<String> mountConfigurationList = FXCollections.observableArrayList();

   

    /**
     * Update JavaFX file
     *
     * @param url
     * @param rb
     */


    private static final String line_one_array[] = new String[20];
    private static final String line_two_array[] = new String[20];

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        btn_load_element.setTextFill(Color.RED);
        //mount_label_1.setText("hello");
        populateMounts();
        choiceBox.setItems(mountConfigurationList);
    }

    // @FXML
    //private void printHelloWorld(ActionEvent event) {
    //    event.consume();
    //    System.out.println("");
    //    label_slot_status.setText("");
    //}
    /**
     *
     * Update the load status
     *
     * @param url
     * @param rb
     */
    @FXML
    private void updateLoadStatus() 
    {
        // string array
        String st[] = {"Arnab", "Andrew", "Ankit", "None"};

        // create a choiceBox
        choiceBox = new ChoiceBox(FXCollections.observableArrayList(st));

        String hello;
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
        tleLineOne = lf1.getText();
        tleLineTwo = lf2.getText();
        
        if ((tleLineOne.equals("")) || (tleLineTwo.equals("")))
            {
                System.out.println("hello");
                return;
            }
             
        //System.out.println("hello" + this.checkSum(tleLineOne));
        //trim both lines of text
        tleLineOne = tleLineOne.substring(3, tleLineOne.length());
        tleLineTwo = tleLineTwo.substring(3, tleLineTwo.length());

        // extract from tle line 1
        Pattern pattern = Pattern.compile("((\\-)?(\\+)?(\\s)?[1234567890+][-1234567890+]+?)([.1234567890]+)");
        Matcher matcher = pattern.matcher(tleLineOne);

        y = 0;
        x = 0;
        i = 1;
        while (matcher.find()) {

            y = matcher.end();
            //System.out.println("x"+x);
            //System.out.println("y"+y);
            //System.out.println(">"+tleLineOne.substring(x+1,y));
            line_one_array[i] = (tleLineOne.substring(x, y));
            line_one_array[i] = line_one_array[i].trim();
            i++;
            x = y + 1;
        }

        subLen = tleLineOne.length();
        launch_y = (line_one_array[2].substring(0, 2));
        launch_num = (line_one_array[2].substring(2, 5));
        // String launch_catalogue_number = (line_one_array[2].substring(6,6));
        obs_y = Integer.parseInt(line_one_array[3].substring(0, 2));
        obs_day = Integer.parseInt(line_one_array[3].substring(2, 5));
        obs_hour = 24 * (Double.parseDouble(line_one_array[3]) - (int) (Double.parseDouble(line_one_array[3])));
        obs_min = (obs_hour - (int) (obs_hour)) * 60;
        obs_sec = (obs_min - (int) (obs_min)) * 60;
        obs_min = (int) (obs_min);
        obs_hour = (int) (obs_hour);
        epochdate = (int) Double.parseDouble(line_one_array[3]);
        epochtime = Double.parseDouble(line_one_array[3]) - (int) Double.parseDouble(line_one_array[3]);
        epoch_date.setText(String.valueOf(epochdate));
        epoch_time.setText(String.valueOf(epochtime));
        satellite.setText((line_one_array[1]));

        // set the launch number of that year
        launch_number.setText(String.valueOf(launch_num));

        // set the launch year
        if (Double.parseDouble(launch_y) < 60) 
            {
                launch_year.setText("20" + String.valueOf(launch_y));
            } 
            else 
            {
                launch_year.setText("19" + String.valueOf(launch_y));
            }

        observation_day.setText(String.valueOf(obs_day));
        observation_hour.setText(String.valueOf(obs_hour).substring(0, 2) + String.valueOf(obs_min).substring(0, 2));
        observation_sec.setText(String.valueOf(obs_sec));
        //launch_catalogue_sequence.setText(launch_catalogue_number);

        if (obs_y < 60) 
            {
                observation_year.setText("20" + String.valueOf(obs_y));
            } 
            else 
            {
                observation_year.setText("19" + String.valueOf(obs_y));
            }

        //day.setText((line_one_array[3]));
        //launch_number_1.setText((line_one_array[4]));
        //launch_number_2.setText((line_one_array[5]));
        //piece_number.setText((line_one_array[6]));
        //epoch_date.setText((line_one_array[7]));
        //epoch_time.setText((line_one_array[8]));    
        // Let us attempt to determine the ephemeris type from the data
        // By extracting a range of information from the TLE
        Pattern pattern_short = Pattern.compile("[ABCDEFGHIJKLMNOPQRSTUVWXYZ]");
        Matcher matcher_short = pattern_short.matcher(tleLineOne);

        i = 1;
        y = 0;
        x = 0;
        while (matcher_short.find()) 
        {
            x = y;
            y = matcher_short.end();
            //System.out.println("x"+x);
            //System.out.println("y"+y);
            //System.out.println(">"+tleLineOne.substring(x+1,y));

            // extract from the range 10- 11 the ephemeris type
            if (y > 11) 
            {
                launch_catalogue_sequence = tleLineOne.substring(y - 1, y);
                lcsequence.setText(launch_catalogue_sequence);
            }
            i++;
        }
        // extract from tle line 2
        Matcher matcher_next = pattern.matcher(tleLineTwo);

        i = 1;
        y = 0;
        x = 0;
        
        while (matcher_next.find()) {
            x = y;
            y = matcher_next.end();
            //System.out.println("x"+x);
            //System.out.println("y"+y);
            //System.out.println("><><"+tleLineTwo.substring(x+1,y));
            line_two_array[i] = (tleLineTwo.substring(x + 1, y));
            line_two_array[i] = line_two_array[i].trim();
            i++;
        }

        subLen = tleLineTwo.length();
        label_slot_status.setText("OK - Set");
        label_slot_status.setTextFill(Color.web("#228B22"));
        orbitinclination.setText(line_two_array[2]);
        rightascension.setText(line_two_array[3]);
        eccentricity.setText(String.valueOf(tleLineTwo.substring(subLen - 43, subLen - 36)));
        elementnumber.setText(String.valueOf(tleLineOne.substring(subLen - 5, subLen - 1)));
        revolutionnumber.setText(String.valueOf(tleLineTwo.substring(subLen - 6, subLen - 1)));
        ephemeristype.setText(String.valueOf(tleLineOne.substring(subLen - 7, subLen - 5)));
        meanmotion.setText(line_two_array[6]);
        meananomaly.setText(line_two_array[5]);
        perigree.setText(line_two_array[4]);
        //dragterm.setText(line_two_array[1]);

        firsttimederiv.setText(String.valueOf(tleLineOne.substring(subLen - 36, subLen - 24)));
        secondtimederiv.setText(String.valueOf(tleLineOne.substring(subLen - 24, subLen - 19)));
        secondtimederivexp.setText(String.valueOf(tleLineOne.substring(subLen - 19, subLen - 17)));
        bstardrag.setText(String.valueOf(tleLineOne.substring(subLen - 16, subLen - 10)));
        bstardragexp.setText(String.valueOf(tleLineOne.substring(subLen - 10, subLen - 6)));
    }

    /**
     * Update JavaFX file
     *
     * @param url
     * @param rb
     */
    @FXML
    private void updateObserverStatus() 
    {
        label_observer_status.setText("OK - Set");
        label_observer_status.setTextFill(Color.web("#228B22"));
    }

    /**
     * Update JavaFX file
     *
     * @param url
     * @param rb
     */
    @FXML
    private void updateObserverStatus1() 
    {
        if ("Equatorial".equals(choiceBox.getValue())) {
            // set to equatorial mount parameters
            mount_label_1.setText("Right ascension : ");
            mount_label_2.setText("Declination: ");
        } else if ("Altitude-azimuth".equals(choiceBox.getValue())) {
            // set to altitude azimuth mount parameters
            mount_label_1.setText("Altitude : ");
            mount_label_2.setText("Azimuth : ");
        } else if ("German equatorial".equals(choiceBox.getValue())) {
            // set to german equatorial mount parameters
            mount_label_1.setText("Declination axis : ");
            mount_label_2.setText("Polar axis : ");
        } else {
            // set to dobsonian mount parameters
            mount_label_1.setText("Altitude : ");
            mount_label_2.setText("Azimuth : ");
        }

        //menu_button_orientationx.setText("OK - Set");
        // hello2.setText(menu_button_orientationx.getText());
        //label_observer_status.setTextFill(Color.web("#228B22"));
    }

    private int checkSum(String strArray) 
    {
        int stringLen = 0;
        stringLen = strArray.length();
        int i = 1;
        int total = 0;

        while (i < stringLen) {
            String stringPhrase = strArray;
            //System.out.println(strArray.subSequence(i,i+1));
            String x = (String) strArray.subSequence(i, i + 1);

            if (x.equals("-")) {
                total++;
            } else if (x.equals("0") | x.equals("1") | x.equals("2") | x.equals("3") | x.equals("4") | x.equals("5") | x.equals("6") | x.equals("7") | x.equals("8") | x.equals("9")) {
                System.out.println("total>>" + total);
                System.out.println("letter>>" + Integer.valueOf(x));
                total = total + Integer.parseInt(x);
                System.out.println("out>>" + i);
            }
            i++;

        }
        return total;
    }

    private void populateMounts() 
    {
        mountConfigurationList.add("Altitude-azimuth");
        mountConfigurationList.add("Equatorial");
        mountConfigurationList.add("Dobsonian");
        mountConfigurationList.add("German equatorial");
    }
    
}
