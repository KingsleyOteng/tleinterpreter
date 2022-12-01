/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */


import java.io.File;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.io.Serializable;
import javafx.fxml.FXML;
import java.net.URL;
import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.fxml.Initializable;import javafx.scene.control.MenuItem;
// import org.orekit.utils.TimeStampedPVCoordinates;
// import org.orekit.utils.PVCoordinatesProvider;
import org.orekit.utils.PVCoordinates;
// import org.orekit.time.TimeStamped;
// import org.orekit.time.TimeInterpolable;
import org.orekit.time.AbsoluteDate;
// import org.orekit.time.TimeShiftable;
// import org.orekit.errors.OrekitIllegalArgumentException;
// import org.orekit.errors.OrekitInternalError;
import org.orekit.propagation.analytical.tle.TLE;
// import org.orekit.errors.OrekitMessages;
// import org.orekit.frames.Frame;
// import org.orekit.frames.Transform;
// import org.hipparchus.util.MathArrays;
import org.hipparchus.util.FastMath;
//import org.hipparchus.linear.RealMatrix;
//import org.hipparchus.linear.QRDecomposition;
//import org.hipparchus.linear.MatrixUtils;
//import org.hipparchus.linear.DecompositionSolver;
//import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.bodies.GeodeticPoint;
import org.orekit.bodies.OneAxisEllipsoid;
import org.orekit.data.DataContext;
import org.orekit.data.DataProvidersManager;
import org.orekit.data.DirectoryCrawler;
import org.orekit.frames.FactoryManagedFrame;
import org.orekit.frames.FramesFactory;
import org.orekit.frames.TopocentricFrame;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.analytical.tle.TLEPropagator;
import org.orekit.time.TimeScalesFactory;
import org.orekit.utils.Constants;
import org.orekit.utils.IERSConventions;

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
    
    TopocentricFrame aoiTopoFrame;

    int subLen;
    int i, y, x;
    int obs_y;
    int obs_day;
    int epochdate;

    double obs_hour;
    double obs_min;
    double obs_sec;
    double epochtime;
    double aoi_lat = 0;
    double aoi_lon = 0;
    double aoi_alt = 0;
    double latitude;
    double longitude;
    double azimuth;
    double elevation;
  
        
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
    private ChoiceBox<String> obsMonBox 
            = new ChoiceBox();
    @FXML
    private ChoiceBox<String> obsDayBox 
            = new ChoiceBox();
    @FXML
    private ChoiceBox<String> obsTimeHourBox 
            = new ChoiceBox();
    @FXML
    private ChoiceBox<String> obsYearBox 
            = new ChoiceBox();
    @FXML
    private ChoiceBox<String> obsTimeMnBox 
            = new ChoiceBox();
    @FXML
    private Label mount_label_1;
    @FXML
    private Label mount_label_2;
    @FXML
    private final MenuItem item1 = new MenuItem();

    private ObservableList<String> mountConfigurationList = FXCollections.observableArrayList();
    private ObservableList<String> obsDateYearList = FXCollections.observableArrayList();
    private ObservableList<String> obsDateMonList = FXCollections.observableArrayList();
    private ObservableList<String> obsDateDayList = FXCollections.observableArrayList();
    private ObservableList<String> obsTimeHourList = FXCollections.observableArrayList();
    private ObservableList<String> obsTimeMinList = FXCollections.observableArrayList();

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
        
        DecimalFormat df = new DecimalFormat("0.00");
        // initial orbit propogation constants
        File orekitData = new File("/Users/terra6partner/Downloads/orekit-data-master/");
        DataProvidersManager manager = DataContext.getDefault().getDataProvidersManager();
        manager.addProvider(new DirectoryCrawler(orekitData));
        
        btn_load_element.setTextFill(Color.RED);
        this.populateMounts();
        choiceBox.setItems(mountConfigurationList);
        obsMonBox.setItems(obsDateMonList);
        obsDayBox.setItems(obsDateDayList);
        obsTimeHourBox.setItems(obsTimeHourList);
        obsYearBox.setItems(obsDateYearList);
        obsTimeMnBox.setItems(obsTimeMinList);

        
        // 
        FactoryManagedFrame ITRF = FramesFactory.getITRF(IERSConventions.IERS_2010, true);
        OneAxisEllipsoid earth = new OneAxisEllipsoid(Constants.WGS84_EARTH_EQUATORIAL_RADIUS,
            Constants.WGS84_EARTH_FLATTENING,
            ITRF);
        
        // set sensor location
        GeodeticPoint aoiPoint = new GeodeticPoint(FastMath.toRadians(aoi_lat), FastMath.toRadians(aoi_lon), aoi_alt);
    
        // determine topocentric frame of reference
        aoiTopoFrame = new TopocentricFrame(earth, aoiPoint, "AOI");
        
        // create a TLE object
        final String line1 = "1 54155U 22140A   22326.36465914  .00009471  00000+0  17282-3 0  9995";
        final String line2 = "2 54155  51.6438 272.9968 0007038 101.0576  43.4609 15.50137650369715";
        final TLE tle = new TLE(line1, line2);
        final TLEPropagator propagator = TLEPropagator.selectExtrapolator(tle);
        
        // obtain current time
        AbsoluteDate date = new AbsoluteDate(2022, 9, 29, 10, 23, 0.0, TimeScalesFactory.getUTC());
  
        // obtain spacecraft state
        SpacecraftState spaceCraftState = propagator.propagate(date);
    
        // determine PVCoordinates
        PVCoordinates coord = spaceCraftState.getPVCoordinates(ITRF);
    
        // transform to earths geodectic points
        GeodeticPoint geodetic = earth.transform
        (
            coord.getPosition(),
            ITRF,
            date
        );

        // determine the latitude and longitude of propogaed item
        
        latitude = FastMath.toDegrees(geodetic.getLatitude());
        longitude = FastMath.toDegrees(geodetic.getLongitude());

        // from the sensor determine the observation parameters in azimuth-elevation
        
        azimuth = aoiTopoFrame.getAzimuth(coord.getPosition(), spaceCraftState.getFrame(), date);
        azimuth = FastMath.toDegrees(azimuth); 
        elevation = FastMath.toDegrees(aoiTopoFrame.getElevation(coord.getPosition(), spaceCraftState.getFrame(), date));

        // let us see how this has been propogated
        
        System.out.println("Propagated at " + date + ": lat=" + latitude + "; lon=" + longitude + "; azimuth=" + azimuth + "; elevation=" + elevation);
        
        
        // generate labels consistent with true output
        mount_label_1.setText("Alt. : " + String.valueOf(df.format(azimuth)));
        mount_label_2.setText("Elev. : " + String.valueOf(df.format(elevation)));
        
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

        //String hello;
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

        y = 0; x = 0; i = 1;
        while (matcher.find()) 
        {

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

        i = 1; y = 0;  x = 0;
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

        i = 1; y = 0; x = 0;
        while (matcher_next.find()) 
            {
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
        DecimalFormat df = new DecimalFormat("0.00");
        
        // 
        FactoryManagedFrame ITRF = FramesFactory.getITRF(IERSConventions.IERS_2010, true);
        OneAxisEllipsoid earth = new OneAxisEllipsoid(Constants.WGS84_EARTH_EQUATORIAL_RADIUS,
            Constants.WGS84_EARTH_FLATTENING,
            ITRF);
        
        
        // create a TLE object
        final String line1 = "1 54155U 22140A   22326.36465914  .00009471  00000+0  17282-3 0  9995";
        final String line2 = "2 54155  51.6438 272.9968 0007038 101.0576  43.4609 15.50137650369715";
        final TLE tle = new TLE(line1, line2);
        final TLEPropagator propagator = TLEPropagator.selectExtrapolator(tle);
        
        // obtain current time
        AbsoluteDate date = new AbsoluteDate(2022, 9, 29, 10, 23, 0.0, TimeScalesFactory.getUTC());
  
        // obtain spacecraft state
        SpacecraftState spaceCraftState = propagator.propagate(date);
    
        // determine PVCoordinates
        PVCoordinates coord = spaceCraftState.getPVCoordinates(ITRF);
    
        // transform to earths geodectic points
        GeodeticPoint geodetic = earth.transform
        (
            coord.getPosition(),
            ITRF,
            date
        );

        // determine the latitude and longitude of propogaed item
        latitude = FastMath.toDegrees(geodetic.getLatitude());
        longitude = FastMath.toDegrees(geodetic.getLongitude());

        // from the sensor determine the observation parameters in azimuth-elevation
        
        azimuth = aoiTopoFrame.getAzimuth(coord.getPosition(), spaceCraftState.getFrame(), date);
        azimuth = FastMath.toDegrees(azimuth); 
        elevation = FastMath.toDegrees(aoiTopoFrame.getElevation(coord.getPosition(), spaceCraftState.getFrame(), date));

        // generate labels consistent with true output
        mount_label_1.setText("Alt. : " + String.valueOf(df.format(azimuth)));
        mount_label_2.setText("Elev. : " + String.valueOf(df.format(elevation)));
        
        
            if (null == choiceBox.getValue()) 
        {
            // set to dobsonian mount parameters
            mount_label_1.setText("Altitude : ");
            mount_label_2.setText("Azimuth : ");
        } 
            else switch (choiceBox.getValue()) 
        {
            case "Equatorial":
                // set to equatorial mount parameters
                mount_label_1.setText("Right ascension : ");
                mount_label_2.setText("Declination: ");
                break;
        //menu_button_orientationx.setText("OK - Set");
        // hello2.setText(menu_button_orientationx.getText());
        //label_observer_status.setTextFill(Color.web("#228B22"));
            case "Altitude-azimuth":
                // set to altitude azimuth mount parameters
                mount_label_1.setText("Altitude : ");
                mount_label_2.setText("Azimuth : ");
                break;
            case "German equatorial":
                // set to german equatorial mount parameters
                mount_label_1.setText("Declination axis : ");
                mount_label_2.setText("Polar axis : ");
                break;
            default:
                // set to dobsonian mount parameters
                mount_label_1.setText("Altitude : ");
                mount_label_2.setText("Azimuth : ");
                break;
        }
    }

    private int checkSum(String strArray) 
    {
        int stringLen;
        stringLen = strArray.length();
        int i = 1;
        int total = 0;

        while (i < stringLen) {
            //String stringPhrase = strArray;
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
        
         // hours list for choicebox
        obsTimeHourList.add("00");
        obsTimeHourList.add("01");
        obsTimeHourList.add("02");
        obsTimeHourList.add("03");
        obsTimeHourList.add("04");
        obsTimeHourList.add("05");
        obsTimeHourList.add("06");
        obsTimeHourList.add("07");
        obsTimeHourList.add("08");
        obsTimeHourList.add("09");
        obsTimeHourList.add("10");
        obsTimeHourList.add("11");
        obsTimeHourList.add("12");
        obsTimeHourList.add("13");
        obsTimeHourList.add("14");
        obsTimeHourList.add("15");
        obsTimeHourList.add("16");
        obsTimeHourList.add("17");
        obsTimeHourList.add("18");
        obsTimeHourList.add("19");
        obsTimeHourList.add("20");
        obsTimeHourList.add("21");
        obsTimeHourList.add("22");
        obsTimeHourList.add("23");
   
        // minutes list for choicebox
        obsTimeMinList.add("00");
        obsTimeMinList.add("01");
        obsTimeMinList.add("02");
        obsTimeMinList.add("03");
        obsTimeMinList.add("04");
        obsTimeMinList.add("05");
        obsTimeMinList.add("06");
        obsTimeMinList.add("07");
        obsTimeMinList.add("08");
        obsTimeMinList.add("09");
        obsTimeMinList.add("10");
        obsTimeMinList.add("11");
        obsTimeMinList.add("12");
        obsTimeMinList.add("13");
        obsTimeMinList.add("14");
        obsTimeMinList.add("15");
        obsTimeMinList.add("16");
        obsTimeMinList.add("17");
        obsTimeMinList.add("18");
        obsTimeMinList.add("19");
        obsTimeMinList.add("20");
        obsTimeMinList.add("21");
        obsTimeMinList.add("22");
        obsTimeMinList.add("23");
        obsTimeMinList.add("24");
        obsTimeMinList.add("25");
        obsTimeMinList.add("26");
        obsTimeMinList.add("27");
        obsTimeMinList.add("28");
        obsTimeMinList.add("29");
        obsTimeMinList.add("30");
        obsTimeMinList.add("31");
        obsTimeMinList.add("32");
        obsTimeMinList.add("33");
        obsTimeMinList.add("34");
        obsTimeMinList.add("35");
        obsTimeMinList.add("36");
        obsTimeMinList.add("37");
        obsTimeMinList.add("38");
        obsTimeMinList.add("39");
        obsTimeMinList.add("40");
        obsTimeMinList.add("41");
        obsTimeMinList.add("42");
        obsTimeMinList.add("43");
        obsTimeMinList.add("44");
        obsTimeMinList.add("45");
        obsTimeMinList.add("46");
        obsTimeMinList.add("47");
        obsTimeMinList.add("48");
        obsTimeMinList.add("49");
        obsTimeMinList.add("50");
        obsTimeMinList.add("51");
        obsTimeMinList.add("52");
        obsTimeMinList.add("53");
        obsTimeMinList.add("54");
        obsTimeMinList.add("55");
        obsTimeMinList.add("56");
        obsTimeMinList.add("57");
        obsTimeMinList.add("58");
        obsTimeMinList.add("59");
        
        // years list for choicebox
        obsDateYearList.add("1999");
        obsDateYearList.add("2001");
        obsDateYearList.add("2002");
        obsDateYearList.add("2003");
        obsDateYearList.add("2004");
        obsDateYearList.add("2005");
        obsDateYearList.add("2006");
        obsDateYearList.add("2007");
        obsDateYearList.add("2008");
        obsDateYearList.add("2009");
        obsDateYearList.add("2010");
        obsDateYearList.add("2011");
        obsDateYearList.add("2012");
        obsDateYearList.add("2013");
        obsDateYearList.add("2014");
        obsDateYearList.add("2015");
        obsDateYearList.add("2016");
        obsDateYearList.add("2017");
        obsDateYearList.add("2018");
        obsDateYearList.add("2019");
        obsDateYearList.add("2020");
        obsDateYearList.add("2021");
        obsDateYearList.add("2022");
        obsDateYearList.add("2023");
        
        // day month list for choicebox
        obsDateMonList.add("JAN");
        obsDateMonList.add("FEB");
        obsDateMonList.add("MAR");
        obsDateMonList.add("APR");
        obsDateMonList.add("MAY");
        obsDateMonList.add("JUN");
        obsDateMonList.add("JUL");
        obsDateMonList.add("AUG");
        obsDateMonList.add("SEPT");
        obsDateMonList.add("OCT");
        obsDateMonList.add("NOVE");
        obsDateMonList.add("DEC");
       
        // day date list for choicebox
        obsDateDayList.add("01");
        obsDateDayList.add("02");
        obsDateDayList.add("03");
        obsDateDayList.add("04");
        obsDateDayList.add("05");
        obsDateDayList.add("06");
        obsDateDayList.add("07");
        obsDateDayList.add("08");
        obsDateDayList.add("09");
        obsDateDayList.add("10");
        obsDateDayList.add("11");
        obsDateDayList.add("12");
        obsDateDayList.add("13");
        obsDateDayList.add("14");
        obsDateDayList.add("15");
        obsDateDayList.add("16");
        obsDateDayList.add("17");
        obsDateDayList.add("18");
        obsDateDayList.add("19");
        obsDateDayList.add("20");
        obsDateDayList.add("21");
        obsDateDayList.add("22");
        obsDateDayList.add("23");
        obsDateDayList.add("24");
        obsDateDayList.add("25");
        obsDateDayList.add("26");
        obsDateDayList.add("27");
        obsDateDayList.add("28");
        obsDateDayList.add("29");
        obsDateDayList.add("30");
        obsDateDayList.add("31");

    }
    
}
