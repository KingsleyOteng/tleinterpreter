/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

// to do: double occultation.
//import java.io.Serializable;
import java.io.File;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import org.orekit.utils.PVCoordinates;
import org.orekit.time.AbsoluteDate;
import org.hipparchus.util.FastMath;
import org.orekit.propagation.analytical.tle.TLE;

// import org.orekit.utils.TimeStampedPVCoordinates;
// import org.orekit.utils.PVCoordinatesProvider;
// import org.orekit.time.TimeStamped;
// import org.orekit.time.TimeInterpolable;
// import org.orekit.time.TimeShiftable;
// import org.orekit.errors.OrekitIllegalArgumentException;
// import org.orekit.errors.OrekitInternalError;
// import org.orekit.errors.OrekitMessages;
// import org.orekit.frames.Frame;
// import org.orekit.frames.Transform;
// import org.hipparchus.util.MathArrays;
// import org.hipparchus.linear.RealMatrix;
// import org.hipparchus.linear.QRDecomposition;
// import org.hipparchus.linear.MatrixUtils;
// import org.hipparchus.linear.DecompositionSolver;
// import org.hipparchus.geometry.euclidean.threed.Vector3D;
// import org.orekit.bodies.GeodeticPoint;
// import org.orekit.bodies.OneAxisEllipsoid;
// import org.orekit.frames.FramesFactory;
// import org.orekit.frames.TopocentricFrame;
import org.orekit.data.DataContext;
import org.orekit.data.DataProvidersManager;
import org.orekit.data.DirectoryCrawler;
import org.orekit.frames.FactoryManagedFrame;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.analytical.tle.TLEPropagator;
import org.orekit.time.TimeScalesFactory;
import org.orekit.utils.Constants;
import org.orekit.utils.IERSConventions;
import org.orekit.propagation.events.ElevationDetector;
import org.orekit.bodies.CelestialBodyFactory;
import org.orekit.bodies.CelestialBodyFactory.*;

// import java.io.IOException;
import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
// import org.orekit.attitudes.InertialProvider;
// import org.orekit.bodies.BodyShape;
// import org.orekit.frames.Frame;
// import org.orekit.propagation.events.handlers.EventHandler;
import org.orekit.frames.*;
import org.orekit.propagation.sampling.OrekitFixedStepHandler;
import org.orekit.bodies.*;
import org.orekit.bodies.BodyShape;
import org.orekit.bodies.CelestialBody;
import org.orekit.bodies.CelestialBodyFactory;
import org.orekit.bodies.GeodeticPoint;
import org.orekit.bodies.OneAxisEllipsoid;
import org.orekit.errors.OrekitException;
//import org.orekit.errors.;
import org.orekit.frames.Frame;
import org.orekit.frames.FramesFactory;
import org.orekit.frames.L2Frame;
import org.orekit.frames.TopocentricFrame;
import org.orekit.models.AtmosphericRefractionModel;
import org.orekit.models.earth.EarthStandardAtmosphereRefraction;
import org.orekit.orbits.EquinoctialOrbit;
// import org.orekit.orbits.KeplerianOrbit;
import org.orekit.orbits.Orbit;
import org.orekit.propagation.Propagator;
import org.orekit.propagation.analytical.EcksteinHechlerPropagator;
// import org.orekit.propagation.events.BooleanDetector;
import org.orekit.propagation.events.EclipseDetector;
import org.orekit.propagation.events.EventDetector;
import org.orekit.propagation.events.EventsLogger;
import org.orekit.propagation.events.GroundAtNightDetector;
import org.orekit.propagation.events.handlers.ContinueOnEvent;
import org.orekit.time.TimeScale;
import org.orekit.utils.PVCoordinatesProvider;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.hipparchus.util.MathUtils;
// import javafx.stage.Stage;
// import javax.swing.JOptionPane;

/**
 * FXML Controller class
 * @date        May 27th 2022
 * @updated     August 27th 2023
 * @author      kingsley oteng-amoako
 */

public class MainFXMLController implements Initializable {
    
    //ObservableList list = FXCollections.observableArrayList();
    String tleLineOne;
    String tleLineTwo;
    String launch_y;
    String launch_num;
    String launch_catalogue_sequence;
    String[] ConstMonth = new String[] {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEPT", "OCT", "NOV", "DEC"};

    
    int subLen;
    int i, y, x;
    int obs_y;
    int obs_day;
    int epochdate;
    
    int nextDay;
    int nextYear;
    int nextMonth;

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
    double orbit_height;
    double sensor_latitude;
    double sensor_longitude;
    double sensor_altitude;
    Integer sensor_date_yyyy, sensor_date_dd, sensor_date_mm;
    String sensor_timezone_id;
    Calendar sensor_date;
    
    private static double sensor_dobs_altitude;
    private static double sensor_dobs_azimuth;
    private static double sensor_altaz_altitude;
    private static double sensor_altaz_azimuth;
    private static double sensor_altaz_elevation;
    private static double sensor_eq_right_ascension;
    private static double sensor_eq_delination; 
    private static double sensor_ge_declination;
    private static double sensor_ge_polar;
    
    TopocentricFrame aoiTopoFrame;
    
   
  
        
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
    
    @FXML
    private TextArea observation_time;
    
    @FXML
    private TextArea launch_year;
    
    @FXML
    private TextArea launch_number;
    
    @FXML
    private TextArea lcsequence;
    
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
    private ChoiceBox<String> choiceBox1 
            = new ChoiceBox();
    
    @FXML
    private ChoiceBox<String> choiceBoxUV1
            = new ChoiceBox();
    
    @FXML
    private ChoiceBox<String> choiceBoxUV2
            = new ChoiceBox();
    
    @FXML
    private  ChoiceBox<String> obsMonBox 
            = new ChoiceBox();
    
    @FXML
    private ChoiceBox<String> choiceBoxXX
            = new ChoiceBox();
    
    @FXML
    private  ChoiceBox<String> obsDayBox 
            = new ChoiceBox();
    
    @FXML
    private  ChoiceBox<String> obsTimeHourBox 
            = new ChoiceBox();
    
    @FXML
    private  ChoiceBox<String> obsYearBox 
            = new ChoiceBox();
    
    @FXML
    private  ChoiceBox<String> obsTimeMnBox 
            = new ChoiceBox();
    
    @FXML
    private  ChoiceBox<String> obsTimeSecBox 
            = new ChoiceBox();
   
    @FXML
    private  ChoiceBox<String> tleMonBox 
            = new ChoiceBox();
    
    @FXML
    private  ChoiceBox<String> tleYearBox1
            = new ChoiceBox();
    
    @FXML
    private  ChoiceBox<String> tleDayBox 
            = new ChoiceBox();
    
    @FXML
    private Label mount_label_1;
    
    @FXML
    private Label mount_label_2;
    
    @FXML
    private Label layer_label;
    
    @FXML
    private TextArea sen_elevation;
    
    @FXML
    private TextArea sen_longitude;
     
    @FXML
    private TextArea sen_latitude;
    
    @FXML
    private Label start_time_label;
    
    @FXML
    private Label obs_label;
    
    @FXML
    private final MenuItem item1 = new MenuItem();

    // drop down box lists
    private final ObservableList<String> mountConfigurationList = FXCollections.observableArrayList();
    private final ObservableList<String> trackingConfigurationList = FXCollections.observableArrayList();
    private final ObservableList<String> uvIndexRange = FXCollections.observableArrayList();
    private final ObservableList<String> obsDateYearList = FXCollections.observableArrayList();
    private final ObservableList<String> obsDateMonList = FXCollections.observableArrayList();
    private final ObservableList<String> obsDateDayList = FXCollections.observableArrayList();
    private final ObservableList<String> obsTimeHourList = FXCollections.observableArrayList();
    private final ObservableList<String> obsTimeMinList = FXCollections.observableArrayList();
    private final ObservableList<String> obsTimeSecList = FXCollections.observableArrayList();

    // arrays for the two line element entries
    private static final String line_one_array[] = new String[20];
    private static final String line_two_array[] = new String[20];
    
    Location location;
    SunriseSunsetCalculator calculator;
    String officialSunrise;
    String officialSunset;
    long differenceSunriseSunset;

    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    
    
    @Override
    @SuppressWarnings("empty-statement")
    public void initialize(URL url, ResourceBundle rb) 
        {   
            
        try {
            DecimalFormat df = new DecimalFormat("0.00");
            
            // initial orbit propogation constants
            
            File orekitData = new File("/Users/terra6partner/Downloads/orekit-data-master/");
            DataProvidersManager manager = DataContext.getDefault().getDataProvidersManager();
            manager.addProvider(new DirectoryCrawler(orekitData));
            
            //define sensor location and id
            //sensor_latitude = -35.320277777778;
            //sensor_longitude = 149.00694444444;
            //sensor_timezone_id = "Australia/Canberra";
            //sensor_altitude = 0.77;
            
            sensor_latitude = -1.630783;
            sensor_longitude = 6.700071;
            sensor_timezone_id = "Africa/Kumasi";
            sensor_altitude = 0.77;
            sensor_date = Calendar.getInstance();
            sensor_date_yyyy = 2022;
            sensor_date_mm = 12;     
            sensor_date_dd = 19;
            
            // set sensor 
            sensor_date.set(sensor_date_yyyy, sensor_date_mm, sensor_date_dd);
            sen_latitude.setText(String.valueOf(df.format(sensor_latitude)));
            sen_longitude.setText(String.valueOf(df.format(sensor_longitude)));
            sen_elevation.setText(String.valueOf(df.format(sensor_altitude)));
            
            // build UI
            
            btn_load_element.setTextFill(Color.RED);
            this.populateMounts();
            choiceBox.setItems(mountConfigurationList);
            choiceBoxXX.setItems(mountConfigurationList);
            choiceBox1.setItems(trackingConfigurationList);
            
            // build the observation box
            
            obsMonBox.setItems(obsDateMonList);
            obsDayBox.setItems(obsDateDayList);
            obsYearBox.setItems(obsDateYearList);
            obsTimeHourBox.setItems(obsTimeHourList);
            obsTimeMnBox.setItems(obsTimeMinList);
            obsTimeSecBox.setItems(obsTimeSecList);
            choiceBoxUV1.setItems(uvIndexRange);
            choiceBoxUV2.setItems(uvIndexRange);
            
            // build default choicebox values
            choiceBox.setValue("Equitorial");
            choiceBoxUV1.setValue("0.0");
            choiceBoxUV2.setValue("10.0");
            choiceBox1.setValue("Optimal lighting");
            
            
            // build sunrise and sunset data model
            
            location = new Location(sensor_latitude, sensor_longitude);
            calculator = new SunriseSunsetCalculator(location, sensor_timezone_id);
            officialSunrise = calculator.getOfficialSunriseForDate(sensor_date);
            officialSunset = calculator.getOfficialSunsetForDate(sensor_date);
            
            //// Difference betwen two dates
            
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Date date1 = format.parse(officialSunrise);
            System.out.println("officialSunrise" + officialSunrise);
            Date date2 = format.parse(officialSunset);
            System.out.println("officialSunset" + officialSunset);
             differenceSunriseSunset = date2.getTime() - date1.getTime(); 
            System.out.println("differenceSunriseSunset" + differenceSunriseSunset);
           
            
            // Duration timeElapsed = Duration.between(officialSunrise, officialSunset);
            // differenceSunriseSunset = officialSunrise - officialSunset;
            // generate validation output
            
            System.out.println("Official Sunrise  " + officialSunrise + " and Sunset:" + officialSunset);
            
            // build element box
            
            tleMonBox.setItems(obsDateMonList);
            tleDayBox.setItems(obsDateDayList);
            tleYearBox1.setItems(obsDateYearList);

            // Commented out
            //  -----> update the element and the date boxes
            
            //  -----> this.setCurrentDateTime();
            // Commented out
            
            // load orekit conventions
            
            FactoryManagedFrame ITRF = FramesFactory.getITRF(IERSConventions.IERS_2010, true);
            OneAxisEllipsoid earth = new OneAxisEllipsoid(Constants.WGS84_EARTH_EQUATORIAL_RADIUS,
                    Constants.WGS84_EARTH_FLATTENING,
                    ITRF);
            
            // set sensor location
            
            GeodeticPoint aoiPoint = new GeodeticPoint(FastMath.toRadians(aoi_lat), FastMath.toRadians(aoi_lon), aoi_alt);
            
            // determine topocentric frame of reference
            
            aoiTopoFrame = new TopocentricFrame(earth, aoiPoint, "AOI");
            
            // input  TLE
            
            final String line1 = "1 54155U 22140A   22326.36465914  .00009471  00000+0  17282-3 0  9995";
            final String line2 = "2 54155  51.6438 272.9968 0007038 101.0576  43.4609 15.50137650369715";
            
            
            // create a TLE object
            final TLE tle = new TLE(line1, line2);
            TLEPropagator propagator = TLEPropagator.selectExtrapolator(tle);
                            // use the TLE propogator as opposed to using SGP4
                           // final TLEPropagator propagator = TLEPropagator.selectExtrapolator(tle);
                           // AbsoluteDate tleDateAgeAbsolute = tle.getDate();
                           // final TimeScale UTC = TimeScalesFactory.getUTC();
                           // Date tleDateAge = tleDateAgeAbsolute.toDate(UTC);
                           // Date currentDateTime = new Date();  
                           // long difference_In_Time  = currentDateTime.getTime() - tleDateAge.getTime();
                            //System.out.println("Difference in time "+String.valueOf(difference_In_Time));
            
            /* TLEPropagator sgp4 = TLEPropagator.selectExtrapolator(tle,InertialProvider.EME2000_ALIGNED, 1000); */
            
            // set current time
            
            AbsoluteDate date = new AbsoluteDate(2022, 9, 29, 10, 23, 0.0, TimeScalesFactory.getUTC());

            // obtain spacecraft state
            
            SpacecraftState spaceCraftState = propagator.propagate(date);
            Frame itrf = FramesFactory.getITRF(IERSConventions.IERS_2010, true);

PVCoordinates pvInITRF = spaceCraftState.getPVCoordinates(itrf);
Vector3D satellitePositionInITRF = pvInITRF.getPosition();
            
            // determine PVCoordinates
            
                            // PVCoordinates coord = spaceCraftState.getPVCoordinates(ITRF);
            
            PVCoordinates coord = spaceCraftState.getPVCoordinates();
            Vector3D position = coord.getPosition();
            Vector3D velocity = coord.getVelocity();
            
            // Define Washington D.C.'s geodetic point
GeodeticPoint washingtonDC = new GeodeticPoint(Math.toRadians(38.9072), Math.toRadians(-77.0369), 0.0);

// Get the ECEF frame
Frame ecef = FramesFactory.getITRF(IERSConventions.IERS_2010, true);

// Transform the Geodetic Point to ECEF:
Transform transform = ecef.getTransformTo(ecef, AbsoluteDate.J2000_EPOCH);

//Extract and Print the ECEF Coordinates:
Vector3D pvObservorCoordinates = transform.transformPosition(new Vector3D(washingtonDC.getLongitude(), washingtonDC.getLatitude(), washingtonDC.getAltitude()));
Vector3D relativePosition = satellitePositionInITRF.subtract(pvObservorCoordinates);

OneAxisEllipsoid earth_ecef = new OneAxisEllipsoid(Constants.WGS84_EARTH_EQUATORIAL_RADIUS, Constants.WGS84_EARTH_FLATTENING, itrf);
TopocentricFrame topoFrame = new TopocentricFrame( earth_ecef, washingtonDC, "WashingtonDC");
double elevation = Math.toRadians(topoFrame.getElevation(spaceCraftState.getPVCoordinates().getPosition(), itrf, spaceCraftState.getDate()));


OneAxisEllipsoid earthShape = new OneAxisEllipsoid(Constants.WGS84_EARTH_EQUATORIAL_RADIUS, Constants.WGS84_EARTH_FLATTENING, itrf);
CelestialBody sun = CelestialBodyFactory.getSun();
Vector3D satToSun = sun.getPVCoordinates(spaceCraftState.getDate(), itrf).getPosition().subtract(spaceCraftState.getPVCoordinates(itrf).getPosition());

Vector3D earthToSatellite = spaceCraftState.getPVCoordinates().getPosition();
Vector3D earthToSun = sun.getPVCoordinates(spaceCraftState.getDate(), itrf).getPosition();
double angle = Vector3D.angle(earthToSatellite, earthToSun);

// If the angle is less than 90 degrees, then the observer is in darkness.
boolean isSatelliteSunlit = angle < Math.PI / 2;

// Angle between the observer location and the Sun as seen from the Earth's center
Vector3D observerPositionInITRF = earthShape.transform(washingtonDC);
double obsAngle = Vector3D.angle(observerPositionInITRF, earthToSun);

// If the angle is greater than 90 degrees, then the observer is in darkness.
boolean isObserverDark = obsAngle > Math.PI / 2;

if (elevation > 0 && isSatelliteSunlit && isObserverDark) {
    System.out.println("Satellite is observable!");
} else {
    System.out.println("Satellite is not observable.");
}

// final solution to point your telescope <------
double sensor_elevation = Math.toRadians(topoFrame.getElevation(coord.getPosition(), itrf, spaceCraftState.getDate()));
double sensor_azimuth = Math.toRadians(topoFrame.getAzimuth(coord.getPosition(), itrf, spaceCraftState.getDate()));

            
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
            
            
           // Tools tj = new Tools();
            //tj.tools();
            
            // generate labels consistent with true output
            
            mount_label_1.setText("Azi. : " + String.valueOf(df.format(azimuth)));
            mount_label_2.setText("Elev. : " + String.valueOf(df.format(elevation)));
            
            testLogDectors();
        } catch (ParseException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //catch (IOException ex) {Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);}
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
     * 
     */
    
    @FXML
    private void popUpWindow() 
        {
            /* procedure to check the status before attempting to generate a solution */
            
            String status = label_slot_status.getText();
            
            if ("OPEN".equals(status))
                    {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("ERROR!!!");
                        alert.setHeaderText("ERROR: INSUFFICIENT INFORMATION.");
                        alert.setContentText("Please complete form and resubmit request.");
                        alert.showAndWait();
                    }
            
            
        }
    
    @FXML
    private void updateLoadStatus() 
        {
            // string array
            // String st[] = {"Arnab", "Andrew", "Ankit", "None"};

            // create a choiceBox
            //choiceBox = new ChoiceBox(FXCollections.observableArrayList(st));

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
                    line_two_array[i] = (tleLineTwo.substring(x + 1, y));
                    line_two_array[i] = line_two_array[i].trim();
                    i++;
                }

            subLen = tleLineTwo.length();
            label_slot_status.setText("OK - Set");
            label_slot_status.setTextFill(Color.web("#228B22"));
            orbitinclination.setText(line_two_array[2]);
            rightascension.setText(line_two_array[3]);
            eccentricity.setText("0."+String.valueOf(tleLineTwo.substring(subLen - 43, subLen - 36)));
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
            orbit_height = Double.parseDouble(tleLineTwo.substring(subLen - 43, subLen - 36));
            orbit_height = orbit_height * (3.14159267 * 2) / 86400;
            
            double valOfEccentricity = Double.parseDouble(tleLineTwo.substring(subLen - 43, subLen - 36)) / 10000000;
            double valOfMeanMotion = Double.parseDouble(line_two_array[6]);
                    
            if (valOfEccentricity > 0.25)
                {
                    layer_label.setText("Layer: HEO");
                }
            
            // determine a LEO orbit as having a mean motion greater than 11.25 and an eccentricity less than 0.25
            
            else if ((valOfMeanMotion> 11.25) && (valOfEccentricity < 0.25))
                {
                    layer_label.setText("Layer: LEO");
                }
            
            // determine a MEO orbit as having a mean motion between 1.8 and 2.4 plus an eccentricity less than 0.25
            
            else if ((valOfMeanMotion> 1.8) && (valOfMeanMotion < 2.4)  && (valOfEccentricity < 0.25))
                {
                    layer_label.setText("Layer: MEO");
                }
            
            // determine a GEO orbit as having a mean motion less than 1.0 and an eccentricity less than 0.01
            
            else if ((valOfMeanMotion < 1.0)  && (valOfEccentricity < 0.01))
                {
                    layer_label.setText("Layer: GEO");
                }
            
             
        }
    
    /**
     * Update JavaFX file
     *
     * @param url
     * @param rb
     * @param rb
     * 
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
     * @param rb
     * 
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


                // set to Alt-Azimuth
                
                if (null == choiceBox.getValue()) 
                    {
                        // set to dobsonian mount parameters
                        mount_label_1.setText("Altitude : ");
                        mount_label_2.setText("Azimuth : ");
                    } 
                else switch (choiceBox.getValue()) 
                    {
                            case "Equatorial" -> 
                            {
                                        // set to equatorial mount parameters
                                        mount_label_1.setText("Right ascension : ");
                                        mount_label_2.setText("Declination: ");
                            }

                            case "Altitude-azimuth" -> 
                            {
                                        // set to altitude azimuth mount parameters
                                        mount_label_1.setText("Altitude : ");
                                        mount_label_2.setText("Azimuth : ");
                            }

                            case "German equatorial" -> 
                            {
                                        // set to german equatorial mount parameters
                                        mount_label_1.setText("Declination axis : ");
                                        mount_label_2.setText("Polar axis : ");
                            }

                            default -> 
                            {
                                        // generate labels consistent with true output
                                        mount_label_1.setText("Azi. : " + String.valueOf(df.format(azimuth)));
                                        mount_label_2.setText("Elev. : " + String.valueOf(df.format(elevation)));
                            }
                    }
                
            //menu_button_orientationx.setText("OK - Set");
            // hello2.setText(menu_button_orientationx.getText());
            //label_observer_status.setTextFill(Color.web("#228B22"));  
           
                start_time_label.setText("Start time: "  + String.valueOf(officialSunrise));
                obs_label.setText("Obs. time: " + String.valueOf(differenceSunriseSunset)+"s");
            
            
        }

    private int checkSum(String strArray) 
        {
            int stringLen;
            stringLen = strArray.length();
            int i = 1;
            int total = 0;

            while (i < stringLen) 
            {
                //String stringPhrase = strArray;
                //System.out.println(strArray.subSequence(i,i+1));
                String sequnce = (String) strArray.subSequence(i, i + 1);

                if (sequnce.equals("-")) 
                    {
                        total++;
                    } 
                    else if (!(sequnce.equals("9") | sequnce.equals("0") |sequnce.equals("1") | sequnce.equals("2") | sequnce.equals("3") | sequnce.equals("4") | sequnce.equals("5") | sequnce.equals("6") | sequnce.equals("7") | sequnce.equals("8"))) 
                    {
                    } else {
                        System.out.println("total>>" + total);
                        System.out.println("letter>>" + Integer.valueOf(sequnce));
                        total = total + Integer.parseInt(sequnce);
                        System.out.println("out>>" + i);
                }
                i++;
            }
            return total;
        }

    private void populateMounts() 
        {   
            // list of different mounts for choicebox
            mountConfigurationList.add("Altitude-azimuth");
            mountConfigurationList.add("Equatorial");
            mountConfigurationList.add("Dobsonian");
            mountConfigurationList.add("German equatorial");
            
            // list of different mounts for choicebox
            trackingConfigurationList.add("Umbra");
            trackingConfigurationList.add("Twilight");
            trackingConfigurationList.add("Apogee");
            trackingConfigurationList.add("Perigree");
            trackingConfigurationList.add("Zenith");
            trackingConfigurationList.add("Optimal lighting");
            
            

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

            // seconds list for choicebox
            obsTimeSecList.add("00");
            obsTimeSecList.add("15");
            obsTimeSecList.add("30");
            obsTimeSecList.add("45");

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
            obsDateMonList.add("NOV");
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
            
            // uv range index
            uvIndexRange.add("0");
            uvIndexRange.add("0.5");
            uvIndexRange.add("1.0");
            uvIndexRange.add("1.5");
            uvIndexRange.add("2.0");
            uvIndexRange.add("2.5");
            uvIndexRange.add("3.0");
            uvIndexRange.add("3.5");
            uvIndexRange.add("4.0");
            uvIndexRange.add("4.5");
            uvIndexRange.add("5.0");
            uvIndexRange.add("5.5");
            uvIndexRange.add("6.0");
            uvIndexRange.add("6.5");
            uvIndexRange.add("7.0");
            uvIndexRange.add("7.5");
            uvIndexRange.add("8.0");
            uvIndexRange.add("8.5");
            uvIndexRange.add("9.0");
            uvIndexRange.add("9.5");
            uvIndexRange.add("10.0");
            
            
            
             //String st[] = { "Arnab", "Andrew", "Ankit", "None" };
            //xx.setItems(FXCollections.observableArrayList(st));
        }
    
    @SuppressWarnings("empty-statement")
    private void setCurrentDateTime()
        {
            // Clock with the UTC timezone
            LocalDate currentdate = LocalDate.now();
            LocalTime localTime = LocalTime.now();
            //Month cMonth = currentdate.getMonth();
            int currentDay = currentdate.getDayOfMonth();
            int currentYear = currentdate.getYear();
            String currentMonth = (currentdate.getMonth()).toString();
            
            if (null != currentMonth)
        
            // format the month to a three letter format
            switch (currentMonth) 
                {
                    case "JANUARY"      ->  currentMonth =  "JAN";
                    case "FEBURARY"     ->  currentMonth =  "FEB";
                    case "MARCH"        ->  currentMonth =  "MAR";
                    case "APRIL"        ->  currentMonth =  "APR";
                    case "MAY"          ->  currentMonth =  "MAY";
                    case "JUNE"         ->  currentMonth =  "JUN";
                    case "JULY"         ->  currentMonth =  "JUL";
                    case "AUGUST"       ->  currentMonth =  "AUG";
                    case "SEPTEMBER"    ->  currentMonth =  "SEPT";
                    case "OCTOBER"      ->  currentMonth =  "OCT";
                    case "NOVEMBER"     ->  currentMonth =  "NOV";
                    case "DECEMBER"     ->  currentMonth =  "DEC";
                    default             ->                    {}
                };
            
           
            // set default day to today
            
            nextDay = genObsDate(currentMonth, currentDay, currentYear);
            nextYear = currentYear;
            String nextMonth = currentMonth;
            //int nextMonth = ;
            if (nextDay == 1) 
            {
                int this_month = Arrays.asList(ConstMonth).indexOf(currentMonth);
                this_month = (this_month + 1)%12;
                nextMonth = ConstMonth[this_month];
                if (this_month == 1)
                        {
                            nextYear = currentYear + 1;
                        };
            };
            
            
            obsMonBox
                    .setValue(nextMonth);
            obsDayBox
                    .setValue(String.format("%d", nextDay));
            obsYearBox
                    .setValue(String.format("%d", nextYear));
            
            
             // set tle today
            tleMonBox
                    .setValue(currentMonth);
            tleDayBox
                    .setValue(String.format("%d", currentDay));
            tleYearBox1
                    .setValue(String.format("%d", currentYear));
            
            
            // set default observation time to next hour
            obsTimeHourBox
                    .setValue(String.format("%d",localTime.getHour()+1));
            obsTimeMnBox
                    .setValue(String.format("%d",localTime.getMinute()));
            obsTimeSecBox
                    .setValue("00");
        }
    
    private void testLogCombinedDectors() throws OrekitException 
    {
        double const_horizon_altitude = 10.0;   
        double const_dusk_dawn_elevation_rad = FastMath.toRadians(-10);
        
        final TimeScale utc = TimeScalesFactory.getUTC();
        final Vector3D position = new Vector3D(-6142438.668, 3492467.56, -25767.257);
        final Vector3D velocity = new Vector3D(505.848, 942.781, 7435.922);
        final AbsoluteDate date = new AbsoluteDate(2022, 9, 29, TimeScalesFactory.getUTC());

        final Orbit orbit = new EquinoctialOrbit(new PVCoordinates(position,  velocity),
                                                 FramesFactory.getEME2000(), date, 3.9860047e14);
        
        Propagator propagator =
            new EcksteinHechlerPropagator(orbit, 6.378137e6, 3.9860047e14, -1.08263e-3, 2.54e-6, 1.62e-6,  2.3e-7, -5.5e-7);
            
        // Earth and frame
        double ae =  6378137.0; // equatorial radius in meter
        double f  =  1.0 / 298.257223563; // flattening
        Frame ITRF2005 = FramesFactory.getITRF(IERSConventions.IERS_2010, true); // terrestrial frame at an arbitrary date
        BodyShape earth = new OneAxisEllipsoid(ae, f, ITRF2005);
        GeodeticPoint point = new GeodeticPoint(FastMath.toRadians(-1.630783),
                                                FastMath.toRadians(6.700071),
                                                0.0);
        
        TopocentricFrame topo = new TopocentricFrame(earth, point, "Gstation");
       
        ElevationDetector detector =   new ElevationDetector(topo).
            withConstantElevation(FastMath.toRadians(5.0)
            );

        final PVCoordinatesProvider sun = CelestialBodyFactory.getSun();
        final OneAxisEllipsoid earthx = new OneAxisEllipsoid(Constants.WGS84_EARTH_EQUATORIAL_RADIUS,Constants.WGS84_EARTH_FLATTENING, FramesFactory.getITRF(IERSConventions.IERS_2010, true));
        AtmosphericRefractionModel refractionModel = new EarthStandardAtmosphereRefraction();

        final EventDetector is_sat_illuminated_event = new EclipseDetector(sun, 696000000., earthx).withPenumbra().withHandler(new ContinueOnEvent<EclipseDetector>());
        final EventDetector is_ground_at_night_event = new GroundAtNightDetector(topo, sun, const_dusk_dawn_elevation_rad, refractionModel);
        //final EventDetector combined_detector = new BooleanDetector();
            
        EventsLogger logger = new EventsLogger();
           
        // propagator.addEventDetector(logger.monitorDetector(is_sat_illuminated_event));
        // propagator.addEventDetector(logger.monitorDetector(is_ground_at_night_event));
           
        final String line1 = "1 54155U 22140A   22326.36465914  .00009471  00000+0  17282-3 0  9995";
        final String line2 = "2 54155  51.6438 272.9968 0007038 101.0576  43.4609 15.50137650369715";
        final TLE tlex = new TLE(line1, line2);

        final TLEPropagator tlepropagator = TLEPropagator.selectExtrapolator(tlex);

         AbsoluteDate startDate = new AbsoluteDate(2022, 12, 24, 12, 0, 0, TimeScalesFactory.getUTC());
         
         tlepropagator.resetInitialState
        ( tlepropagator.propagate(startDate));
         tlepropagator.addEventDetector
        (logger.monitorDetector(is_sat_illuminated_event));
         tlepropagator.addEventDetector
        (logger.monitorDetector(is_ground_at_night_event));

        //tlepropagator.addEventDetector(detector);
          
        // OrbitHandler dsstHandler = new OrbitHandler();
        // propagator.setMasterMode(10.0, dsstHandler);
        // propagator.setStepHandler(10, dsstHandler);
        // propagator.propagate(startDate.shiftedBy(Constants.JULIAN_DAY));
       
       tlepropagator.propagate(startDate.shiftedBy(Constants.JULIAN_DAY));
    }
    
    private void testLogDectors() throws OrekitException 
    {
        double const_horizon_altitude = 10.0;   
        double const_dusk_dawn_elevation_rad = FastMath.toRadians(-10);
        
        final TimeScale utc = TimeScalesFactory.getUTC();
        final Vector3D position = new Vector3D(-6142438.668, 3492467.56, -25767.257);
        final Vector3D velocity = new Vector3D(505.848, 942.781, 7435.922);
        final AbsoluteDate date = new AbsoluteDate(2022, 9, 29, TimeScalesFactory.getUTC());
       
        final Orbit orbit = new EquinoctialOrbit(new PVCoordinates(position,  velocity),
                                                 FramesFactory.getEME2000(), date, 3.9860047e14);
        
        // create our propagator
        Propagator propagator =
            new EcksteinHechlerPropagator(orbit, 6.378137e6, 3.9860047e14, -1.08263e-3, 2.54e-6, 1.62e-6,  2.3e-7, -5.5e-7);
            
        // Earth and frame
        double ae =  6378137.0; // equatorial radius in meter
        double f  =  1.0 / 298.257223563; // flattening
        Frame ITRF2005 = FramesFactory.getITRF(IERSConventions.IERS_2010, true); // terrestrial frame at an arbitrary date
        BodyShape earth = new OneAxisEllipsoid(ae, f, ITRF2005);
        GeodeticPoint point = new GeodeticPoint(FastMath.toRadians(-1.630783),
                                                FastMath.toRadians(6.700071),
                                                0.0);
        
        TopocentricFrame topo = new TopocentricFrame(earth, point, "Gstation");
   
        final PVCoordinatesProvider sun = CelestialBodyFactory.getSun();
        final OneAxisEllipsoid earthx = new OneAxisEllipsoid(Constants.WGS84_EARTH_EQUATORIAL_RADIUS,Constants.WGS84_EARTH_FLATTENING, FramesFactory.getITRF(IERSConventions.IERS_2010, true));
        AtmosphericRefractionModel refractionModel = new EarthStandardAtmosphereRefraction();
          
        // create three sets of events 
        final ElevationDetector detector =   new ElevationDetector(topo).withConstantElevation(FastMath.toRadians(5.0));
        final EventDetector is_sat_illuminated_event = new EclipseDetector(sun, 696000000., earthx).withPenumbra().withHandler(new ContinueOnEvent<>());
        final EventDetector is_ground_at_night_event = new GroundAtNightDetector(topo, sun, const_dusk_dawn_elevation_rad, refractionModel);
            
        // create a logger    
        EventsLogger logger = new EventsLogger();
           
        // create a date
        AbsoluteDate startDate = new AbsoluteDate(2022, 9, 15, 12, 0, 0, TimeScalesFactory.getUTC());
       
        // start the propagator
        propagator.resetInitialState(propagator.propagate(startDate));
        propagator.addEventDetector(logger.monitorDetector(is_sat_illuminated_event));
        propagator.addEventDetector(logger.monitorDetector(is_ground_at_night_event));
        propagator.addEventDetector(logger.monitorDetector(detector));
         
        // let us create the handler
        OrbitHandler dsstHandler = new OrbitHandler();
        
        //propagator.setStepHandler(10, dsstHandler);
         CelestialBody earthy = CelestialBodyFactory.getEarth();
         CelestialBody moony  = CelestialBodyFactory.getMoon();
         CelestialBody earthMoonBary = CelestialBodyFactory.getEarthMoonBarycenter();
         Frame l2Frame = new L2Frame(earthy, moony);
         Frame earthMoonBaryFrame = earthMoonBary.getInertiallyOrientedFrame();
         Frame inertiaFrame = earthMoonBaryFrame;
         Frame integrationFrame = l2Frame;
         Frame outputFrame = l2Frame;
         PVCoordinates pv = propagator.getPVCoordinates(startDate, outputFrame);
        
         
         propagator.propagate(startDate.shiftedBy(Constants.JULIAN_DAY));
         System.out.println("Propagated at " + date + ": lat=" + latitude + "; lon=" + longitude + "; azimuth=" + azimuth + "; elevation=" + elevation);
       
         double deltaP = Double.POSITIVE_INFINITY;
         double deltaV = Double.POSITIVE_INFINITY;
         
         deltaP = pv.getPosition().getNorm();
         deltaV = pv.getVelocity().getNorm();
         
         System.out.println("deltaP = "+deltaP);
         System.out.println("deltaV = " +deltaV);
    }
    
     private static class OrbitHandler implements OrekitFixedStepHandler {
 
         /** List of orbits. */
         private final List<Orbit> orbits;
 
         private OrbitHandler() {
             // initialise an empty list of orbit
             orbits = new ArrayList<Orbit>();
         }
 
         /** {@inheritDoc} */
         public void init(final SpacecraftState s0, final AbsoluteDate t) {
         }
 
         /** {@inheritDoc} */
         public void handleStep(SpacecraftState currentState, boolean isLast) {
             // fill in the list with the orbit from the current step
             orbits.add(currentState.getOrbit());
         }
 
         /** Get the list of propagated orbits.
          * @return orbits
          */
         public List<Orbit> getOrbits() {
             return orbits;
         }

        @Override
        public void init(SpacecraftState s0, AbsoluteDate t, double step) {
            OrekitFixedStepHandler.super.init(s0, t, step); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        }

        @Override
        public void handleStep(SpacecraftState ss) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void finish(SpacecraftState finalState) {
            OrekitFixedStepHandler.super.finish(finalState); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        }
     }
     
    int genObsDate(String _mon, int _day, int _y)
    {
        
        String[] set_of_months      = new String[] {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEPT", "OCT", "NOV", "DEC"};
        int[] days_of_month         = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 31, 31, 30, 31};
        int[] days_of_month_leap    = new int[] {31, 29, 31, 30, 31, 30, 31, 31, 31, 31, 30, 31};
        boolean leap_year;
        
        
        leap_year = (y%4 == 0);
        
        if (leap_year) 
                {
                  int index = Arrays.asList(set_of_months).indexOf(_mon);
                  if ((_day + 1) > (days_of_month_leap[index]))
                          {
                              return 1;
                          }
                  else 
                          {
                              return _day + 1;
                          }
                }
        else
                {
                  int index = Arrays.asList(set_of_months).indexOf(_mon);
                  if ((_day + 1) > (days_of_month[index]))
                          {
                              return 1;
                          }
                  else 
                          {
                              return _day + 1;
                          }
                }
    };
        
   int genObsMonth(String _mon, int _day, int _y)
   {
       return 0;
   };

};
