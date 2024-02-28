    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

/*
@Dates: May 26th 2022
@recent: Feburary 1st 2024
@author kingsley oteng-amoako
*/

import java.lang.reflect.InvocationTargetException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;

    /**
     * @author  kingsley oteng-amoako
     * @dated   july 19th 2023
     */



public class NewFXMain extends Application 
    {
        /**
         * construct the UI
         * @param stage
         * @throws java.lang.Exception
         * @throws java.lang.reflect.InvocationTargetException
         */ 
    
        @Override
        public void start(Stage stage) throws Exception, InvocationTargetException
            {
                Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
                Scene scene = new Scene(root, 860, 880);
                stage.setTitle("TLE Interpreter");
                stage.setScene(scene);
                stage.show();
            }
       
        
        
        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) 
            {
                launch(args);
            }
    }
