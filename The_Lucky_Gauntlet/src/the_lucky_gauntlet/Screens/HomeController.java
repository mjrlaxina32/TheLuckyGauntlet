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
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Monique Jhoienyl
 */
public class HomeController extends SuperController implements Initializable {

    @FXML
    private VBox Home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML public void openSetup(ActionEvent e) throws IOException {
		openNewWindow("Setup.fxml",e); //TO CHANGE TO SET-UP SCREEN!!!!
    }
    
    @FXML public void exit(ActionEvent e) throws IOException {
		Platform.exit();
    }
    
}
