/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package the_lucky_gauntlet.Screens;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Athena Kimwell
 */
public class LoseController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    private FXMLLoader openNewWindow(String fileName, ActionEvent e) throws IOException{
            // Getting the current Window
            Stage currentStage = (Stage)((Node) e.getSource()).getScene().getWindow();

            // Getting the next Screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
            Parent root = loader.load();
            Scene newScene = new Scene(root);

            // Updating the screen
            currentStage.hide();
            currentStage.setScene(newScene);
            currentStage.show();

            return loader;
    }
    @FXML private void openMenu(ActionEvent event) throws IOException {
            openNewWindow("Home.fxml", event);		
    }    
}
