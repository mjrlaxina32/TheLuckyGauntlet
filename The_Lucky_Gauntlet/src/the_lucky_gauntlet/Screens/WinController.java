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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Athena Kimwell
 */
public class WinController extends SuperController implements Initializable {
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    @FXML private void openMap(ActionEvent e) {
        this.openNewWindow("Map.fxml", e);
    }
    
    @FXML private void highlight(Event event) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.GREEN);
        dropShadow.setHeight(5); 
        dropShadow.setWidth(5); 
        dropShadow.setRadius(5); 
        
        ((ImageView)event.getSource()).setEffect(dropShadow);
    }
    
    @FXML private void dehighlight(Event event) {      
        ((ImageView)event.getSource()).setEffect(null);
    }
    
}
