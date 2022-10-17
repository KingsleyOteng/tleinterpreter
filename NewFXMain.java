    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// https://github.com/skyfielders/python-skyfield/tree/master/examples
// https://rhodesmill.org/skyfield/
// https://www.afahc.ro/ro/afases/2016/MATH&IT/CROITORU_OANCEA.pdf
// http://www.satobs.org/element.html
// https://rhodesmill.org/skyfield/examples.html#what-time-is-solar-noon-when-the-sun-transits-the-meridian
// https://snyk.io/advisor/python/skyfield/functions/skyfield.api
// https://rhodesmill.org/skyfield/earth-satellites.html
// https://github.com/skyfielders/python-skyfield/blob/master/skyfield/almanac.py
// https://www.javatpoint.com/how-to-get-utc-time-in-java
// https://www.orekit.org/static/apidocs/org/orekit/propagation/analytical/tle/TLE.html
// https://www.w3schools.com/java/java_arrays.asp
// https://www.mdpi.com/1424-8220/22/8/2902/htm
// google: satellite tracking using two line element


/**
 *
 * @author terra6partner
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage stage) throws Exception
    {
       Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
        
        Scene scene = new Scene(root, 860, 700);
        
        
       
        stage.setTitle("TLE Interpreter");
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
