/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package the_lucky_gauntlet.Screens;

// Java-FX Set-up
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;

// Events
import javafx.event.ActionEvent;

// Exceptions
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author Athena Kimwell
 */
public class BattleController extends SuperController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
	@FXML void openPause(ActionEvent e) throws IOException{
		openNewWindow("Pause.fxml", e);
	}
}

