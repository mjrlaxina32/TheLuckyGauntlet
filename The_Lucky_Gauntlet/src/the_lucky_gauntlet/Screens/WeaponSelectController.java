/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the_lucky_gauntlet.Screens;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author MUON
 */
public class WeaponSelectController extends SuperController implements Initializable {

    @FXML Text weaponSelectATK, weaponSelectDurabilityText;
    @FXML ProgressBar weaponSelectDurabilityBar;
    @FXML Button prevWeaponButton, nextWeaponButton, equipWeaponButton, returnButton;
    @FXML ImageView weaponSelectImage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
