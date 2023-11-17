
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author terra6partner
 */
public class Tools 
{
   public static final String delimiter = ",";
   public static void read(String csvFile) throws java.io.IOException {
      try {
                File file = new File(csvFile);
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line = "";
                String[] tempArr;
                while((line = br.readLine()) != null) 
                   {
                       tempArr = line.split(delimiter);
                       for(String tempStr : tempArr) {
                          System.out.print(tempStr + " ");
                       }
                       System.out.println();
                   }
                br.close();
         } 
            catch(IOException ioe) 
         {
            ioe.printStackTrace();
         }
   }
   public  void test() throws IOException
   {// csv file to read
      String csvFile = "norad_id_match.csv";
      Tools.read(csvFile);
   };
   
      public static void main(String[] args) throws IOException {
      // csv file to read
      String csvFile = "norad_id_match.csv";
      Tools.read(csvFile);
   }
      

    void tools() throws IOException {
        String csvFile = "norad_id_match.csv";
      Tools.read(csvFile);
        }
    
}
