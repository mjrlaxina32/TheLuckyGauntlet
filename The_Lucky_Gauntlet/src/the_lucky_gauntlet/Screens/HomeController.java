/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package the_lucky_gauntlet.Screens;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Monique Jhoienyl
 */
public class HomeController extends SuperController implements Initializable {

    @FXML private VBox Home;
    @FXML private Text title;
    //ayaw magload :sob:

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML public void openSetup(ActionEvent e) throws IOException {
		openNewWindow("Setup.fxml",e);
    }
    
    @FXML private void setBlackBG(Event event)throws IOException{
        Home.setStyle("-fx-background-color: black");
        title.setStyle("-fx-fill: white");
    }
    
    @FXML private void setWhiteBG(Event event)throws IOException{
        Home.setStyle("-fx-background-color: white");
        title.setStyle("-fx-fill: black");
        //sets bg to white and not the prev/default bg color
    }
    
    @FXML public void exit(ActionEvent e) throws IOException {
		Platform.exit();
    }
    
}
