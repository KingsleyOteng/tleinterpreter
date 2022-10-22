
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author kingsley-k-oteng-amoako
 */


public class FXMLController implements Initializable {
    @FXML
    Button newbtn = new Button(); 
    
    @FXML
    Button acceptBtn = new Button(); 
    
    @FXML
    Button ignoreBtn = new Button(); 
    
     
    @FXML
    private TextArea linesTextArea;
    
    @FXML
    private TextArea filepath;
    /**
     * Initializes the controller class.
     */
    
    @FXML
	@SuppressWarnings("NestedAssignment")
	public void showFileLines() throws InterruptedException, ExecutionException {

		linesTextArea.clear();
                linesTextArea.appendText("kwadwo");
	}
        
         @FXML
	@SuppressWarnings("NestedAssignment")
	public void fileChooser() throws InterruptedException, ExecutionException, IOException {



  
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All files", "*.txt")
        );
        
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) 
        {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                filepath.appendText(file.getAbsolutePath());
                System.out.println(file.getAbsolutePath());
                byte[] data = new byte[(int) file.length()];
                fileInputStream.read(data, 0, (int) file.length());
                fileInputStream.close();
                
                File files = new File(file.getAbsolutePath());
                Scanner sc = new Scanner(files);
 
                // we just need to use \\Z as delimiter
                sc.useDelimiter("\\n");
                linesTextArea.setWrapText(true);
                linesTextArea.setStyle("-fx-highlight-fill: #ADFF2F; -fx-highlight-text-fill: #B22222; -fx-font-size: 18px;");
                
                while (sc.hasNextLine()) 
                {           
                             linesTextArea.setStyle("-fx-highlight-fill: #ADFF2F; -fx-highlight-text-fill: #B22222; -fx-font-size: 18px;");
                            // read text file
                            linesTextArea.appendText(sc.nextLine());
                            linesTextArea.setStyle("-fx-highlight-fill: #ADFF2F; -fx-highlight-text-fill: #B22222; -fx-font-size: 18px;");
                            // carriage return at the end of each line
                            linesTextArea.appendText("\r\n");
                }
                
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

   
	}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
