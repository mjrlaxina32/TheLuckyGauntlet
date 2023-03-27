/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package the_lucky_gauntlet.Screens;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Athena Kimwell
 */
public class LoseController extends SuperController implements Initializable {
    @FXML VBox BoxLayout;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    @FXML private void openMenu(ActionEvent event) throws IOException {
        openNewWindow("Home.fxml", event);		
    }

    @FXML private void setBlackBG(Event event)throws IOException{
        BoxLayout.setStyle("-fx-background-color: black");
    }
    
    @FXML private void setWhiteBG(Event event)throws IOException{
        BoxLayout.setStyle("-fx-background-color: white");
        //sets bg to white and not the prev/default bg color
    }
}
