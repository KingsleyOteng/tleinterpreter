
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author kingsley-k-oteng-amoako
 */

interface string_distance {
      
    // all are the abstract methods.
    void setDictionary();
    void setErrors();
    void calculateDistance();
    void findCandidateWords();
    void applyChange();
}

//https://www.geeksforgeeks.org/interfaces-in-java/

 class distances implements string_distance {
    String[] dictionary = new String[10000];
    String[] errors = new String[10000];
    String word;
    
    // to change gear
    @Override
    public void setDictionary()  {
          
        BufferedReader br = null;
    try {
            File file = new File("/Users/kingsley-k-oteng-amoako/NetBeansProjects/JavaFX/src/dictlist.txt");
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) System.out.println(st);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(distances.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(distances.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(distances.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
      
    // to increase speed
    @Override
    public void setErrors(){
          
        errors = new String[] { "A", "B", "C", "D", "E" };
    }
      
    // to decrease speed
    @Override
    public void calculateDistance(){
          
    
    }
    
    // to decrease speed
    @Override
    public void applyChange(){
          
    
    }
      
    public void findCandidateWords() {
        System.out.println("errors: ");
    }

}
