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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Athena Kimwell
 */
public class LoseController extends SuperController implements Initializable {
    @FXML VBox BoxLayout;
    @FXML Text roundNo, loseMsg, gameOverMsg;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    @FXML private void openMenu(ActionEvent event) throws IOException {
        openNewWindow("Home.fxml", event);		
    }

    @FXML private void setBlackBG(Event event)throws IOException{
        BoxLayout.setStyle("-fx-background-color: black");
        roundNo.setStyle("-fx-fill: white");
        loseMsg.setStyle("-fx-fill: white");
        gameOverMsg.setStyle("-fx-fill: white");
    }
    
    @FXML private void setWhiteBG(Event event)throws IOException{
        BoxLayout.setStyle("-fx-background-color: white");
        roundNo.setStyle("-fx-fill: black");
        loseMsg.setStyle("-fx-fill: black");
        gameOverMsg.setStyle("-fx-fill: black");
        //sets bg to white and not the prev/default bg color
    }
}
