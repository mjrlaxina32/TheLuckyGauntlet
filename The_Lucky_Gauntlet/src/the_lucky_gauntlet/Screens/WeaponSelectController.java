/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the_lucky_gauntlet.Screens;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static the_lucky_gauntlet.Screens.SuperController.actionsource;
import the_lucky_gauntlet.*;
/**
 * FXML Controller class
 *
 * @author MUON
 */
public class WeaponSelectController extends SuperController implements Initializable {

    @FXML Text weaponSelectName, weaponSelectATK, weaponSelectDurabilityText;
    @FXML ProgressBar weaponSelectDurabilityBar;
    @FXML Button prevWeaponButton, nextWeaponButton, equipWeaponButton, returnButton;
    @FXML ImageView weaponSelectImage;
    private Label label;
    private int curr;
    private Weapon disWeapon, eqWeapon;
    private Player currUser;
    private String playerBr = actionsource;
    private ArrayList weaponList;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

            switch (playerBr) {
            case "mc":
                currUser = tlg.mc;
                break;
            case "partner":
                currUser = tlg.partner;
                break;
            default:
                currUser = tlg.partner;
                break;
            } 
        update(0); 
    }    
        
     public void update(int curr){
        this.curr = curr;
        eqWeapon = currUser.getWeapon();
        System.out.println("eqW: "+ eqWeapon.getName());
        disWeapon = currUser.getWeaponFromList(curr); 
        weaponSelectName.setText(disWeapon.getName());
        weaponSelectATK.setText("ATK Bonus: " + String.valueOf(disWeapon.getAtkBonus()));
        weaponSelectDurabilityBar.setProgress((float)disWeapon.getDurability() /disWeapon.getMaxDurability());      
        weaponSelectDurabilityText.setText(String.valueOf(disWeapon.getDurability()) + "/" + String.valueOf(disWeapon.getMaxDurability()));
        weaponSelectImage.setImage(disWeapon.getImg());
        weaponList = currUser.getWeaponList();
        
        for(int i = 0; i < weaponList.size(); i++) {
            Weapon l = (Weapon) weaponList.get(i);
            System.out.println(l.getName());
        }  
        
        int ind = currUser.getWeaponList().size()-1;
        if(curr<ind){
            nextWeaponButton.setDisable(false); 
        }
        else{
            nextWeaponButton.setDisable(true);
        }
        
        if(curr>0){
            prevWeaponButton.setDisable(false); 
        }
        else{
            prevWeaponButton.setDisable(true);
        }
        System.out.println("pg."+ curr + " |updated");
        
        if(disWeapon == eqWeapon){
            equipWeaponButton.setDisable(true); 
        }
        else{
            equipWeaponButton.setDisable(false);
        }
    }
    
    @FXML private void next(){
        curr++;
        update(curr);
        System.out.println("pg."+ curr + " |updating");
    }
    
    @FXML private void prev(){
        curr--;
        update(curr);
        System.out.println("pg."+ curr + " |updating");
    }
    
    @FXML private void equip(){
        eqWeapon = disWeapon;
        currUser.changeWeapon(curr);
        update(curr);
    }
    
    @Override 
    @FXML public void openPreviousWindow() {
		switch (playerBr) {
		case "mc":
			tlg.mc = currUser;
			break;
		case "partner":
			tlg.partner = currUser;
			break;
		default:
			tlg.mc = currUser;
			break;
		}
		actionsource = "x";
		super.openPreviousWindow();
	}
}   
    
