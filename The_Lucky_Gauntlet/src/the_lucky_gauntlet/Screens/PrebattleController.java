package the_lucky_gauntlet.Screens;

// Utility
import the_lucky_gauntlet.*;
import the_lucky_gauntlet.Rooms.R_Peaceful;

// JavaFX Set-up
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;

// Components
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

// Events
import javafx.event.ActionEvent;

// Exceptions
import java.io.IOException;
import the_lucky_gauntlet.Exceptions.*;

// Lucky Gauntlet Imports
import the_lucky_gauntlet.*;
import the_lucky_gauntlet.Rooms.R_Peaceful;

public class PrebattleController extends SuperController implements Initializable {
	
	@FXML Text actionText, movesLeftText, mcName, partnerName;
	@FXML Button mcChangeWeaon, mcFindWeapon, mcEnhanceWeapon, mcRepairWeapon;
	@FXML Button partnerChangeWeapon, partnerFindWeapon, partnerEnhanceWeapon, partnerRepairWeapon;
	@FXML Button rest, train;
	@FXML ImageView mcImage, partnerImage;
	
	private R_Peaceful currentRoom;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		mcName.setText(tlg.mc.getName());
		partnerName.setText(tlg.partner.getName());
		mcImage.setImage(tlg.mc.getImg());
		partnerImage.setImage(tlg.partner.getImg());
	}
	public void delayedInitialize() {
		// Lets the current room be updated first before initialization
		actionText.setText(" ");
		movesLeftText.setText("Moves Left: " + currentRoom.getActions());
		checkActionsLeft();
	}	
	
	// Utility Methods
	private void checkActionsLeft() {
		if(currentRoom.getActions() == 0) {
			mcFindWeapon.setDisable(true);
			mcEnhanceWeapon.setDisable(true);
			mcRepairWeapon.setDisable(true);
			
			partnerFindWeapon.setDisable(true);
			partnerEnhanceWeapon.setDisable(true);
			partnerRepairWeapon.setDisable(true);
			
			rest.setDisable(true);
			train.setDisable(true);
			
			currentRoom.completeRoom();
		}
	}
	private void performAction(String textOutput) {
		movesLeftText.setText("Moves Left: " +  + currentRoom.getActions());
		actionText.setText(textOutput);
		
		checkActionsLeft();
	}
	public void enterRoom(R_Peaceful newRoom) {
		currentRoom = newRoom;
	}
	public R_Peaceful getCurrentRoom() {
		return currentRoom;
	}
	
	// Window Opening Methods
	@FXML void openPause(ActionEvent e) throws IOException{
		openNewWindow("Pause.fxml", e);
	}
        
        //Individual Activities
	@FXML void openWeaponSelect(ActionEvent e) throws IOException{
                actionsource = (((Button)e.getSource()).getId()).replace("ChangeWeapon","");
                System.out.println(actionsource);
		openNewWindow("WeaponSelect.fxml", e);
	}
	
        @FXML void findWeapon(ActionEvent e){
                actionsource = (((Button)e.getSource()).getId()).replace("FindWeapon","");
                String textOutput = null;
                int wCou;
                switch (actionsource) {
                case "mc":
                    wCou = tlg.mc.getWeaponList().size();
                    tlg.mc.newWeapon();
                    if (wCou < tlg.mc.getWeaponList().size()){
                    textOutput = tlg.mc.getName() + " recieved new weapon " + tlg.mc.getWeaponFromList(tlg.mc.getWeaponList().size()-1).getName()
                            + "\n with attack bonus " + tlg.mc.getWeaponFromList(tlg.mc.getWeaponList().size()-1).getAtkBonus() 
                            + ". and durability " + tlg.mc.getWeaponFromList(tlg.mc.getWeaponList().size()-1).getMaxDurability() + "!"; 
                    }
                    else{
                        textOutput = tlg.mc.getName() + " failed to find a new weapon.";
                    }
                    wCou = tlg.mc.getWeaponList().size();
                    break;
                case "partner":
                    wCou = tlg.partner.getWeaponList().size();
                    tlg.partner.newWeapon();
                    if (wCou < tlg.partner.getWeaponList().size()){
                    textOutput = tlg.partner.getName() + " recieved new weapon " + tlg.partner.getWeaponFromList(tlg.partner.getWeaponList().size()-1).getName()
                            + "\n with attack bonus " + tlg.partner.getWeaponFromList(tlg.partner.getWeaponList().size()-1).getAtkBonus() 
                            + ". and durability " + tlg.partner.getWeaponFromList(tlg.partner.getWeaponList().size()-1).getMaxDurability() + "!"; 
                    }
                    else{
                        textOutput = tlg.partner.getName() + " failed to find a new weapon.";
                    }
                    wCou = tlg.partner.getWeaponList().size();
                    break;      
                default:
                    tlg.mc.newWeapon();
                    break;      
                }
                performAction(textOutput);
                currentRoom.reduceAc();
        }
        
        @FXML void enhanceWeapon(ActionEvent e){
                String textOutput = null;
                actionsource = (((Button)e.getSource()).getId()).replace("EnhanceWeapon","");
                switch (actionsource) {
                case "mc":
                    tlg.mc.getWeapon().enhance();
                    textOutput = tlg.mc.getName() + " enhanced their " + tlg.mc.getWeapon().getName() 
                            + ".\n The attack bonus is now " + tlg.mc.getWeapon().getAtkBonus() + "."; 
                    break;
                case "partner":
                    tlg.partner.getWeapon().enhance();
                    textOutput = tlg.partner.getName() + " enhanced their " + tlg.partner.getWeapon().getName() 
                            + ".\n The attack bonus is now " + tlg.partner.getWeapon().getAtkBonus() + "."; 
                    break;      
                default:
                    tlg.mc.getWeapon().enhance();
                    break;      
                }
                performAction(textOutput);
                currentRoom.reduceAc();
        }
	
        @FXML void repairWeapon(ActionEvent e){
                String textOutput = null;
                actionsource = (((Button)e.getSource()).getId()).replace("RepairWeapon","");
                switch (actionsource) {
                case "mc":
                    try{
                        tlg.mc.getWeapon().repair();
                        textOutput = tlg.mc.getName() + " repaired their " + tlg.mc.getWeapon().getName() 
                                + ".\n The durability is now " + tlg.mc.getWeapon().getDurability() +  "/" 
                                +  tlg.mc.getWeapon().getMaxDurability() + "."; 
                        break;
                    }
                    catch(BeyondRangeException BRE){
                        System.out.println("Max durability");
                        textOutput = "The weapon is at its maximum durability.";                        
                        break;
                    }
                case "partner":
                    try{
                        tlg.partner.getWeapon().repair();
                        textOutput = tlg.partner.getName() + " repaired their " + tlg.partner.getWeapon().getName() 
                                + ".\n The durability is now " + tlg.partner.getWeapon().getDurability() 
                                +  "/" +  tlg.partner.getWeapon().getMaxDurability() + "."; 
                        break;
                    }
                    catch(BeyondRangeException BRE){
                        System.out.println("Max durability");
                        textOutput = "The weapon is at its maximum durability.";
                        break;
                    }
                default:
                    try{
                        tlg.mc.getWeapon().repair();
                        break;
                    }
                    catch(BeyondRangeException BRE){
                        System.out.println("Max durability");
                        break;
                    }
                }
                performAction(textOutput);
                currentRoom.reduceAc();
        }
        
	// Party-wide Activities
	@FXML void rest(ActionEvent e) throws IOException{
		int mcInitialEnergy = tlg.mc.getEnergy();
		int partnerInitialEnergy = tlg.partner.getEnergy();
		
		currentRoom.rest();
		
		String textOutput = tlg.mc.getName() + " and " + tlg.partner.getName() + " rested!\n"
				+ "Their energies increased by " + (tlg.mc.getEnergy()-mcInitialEnergy) + " and " + (tlg.partner.getEnergy()-partnerInitialEnergy)
				+ " and are now " + tlg.mc.getEnergy() + " and " + tlg.partner.getEnergy() + "!";

		performAction(textOutput);
	}
	@FXML void train(ActionEvent e) throws IOException{
		String textOutput;
		int mcInitialEnergy = tlg.mc.getEnergy();
		int partnerInitialEnergy = tlg.partner.getEnergy();
		
		try {
			currentRoom.train();
			
			textOutput = tlg.mc.getName() + " and " + tlg.partner.getName() + " trained together!\n"
				+ "Their atk power increased by 2 at the cost of 25 energy!\nThey now have an attack power of "
				+ tlg.mc.getAttack() + " and " + tlg.partner.getAttack() + "!";
		}
		catch(NoEnergyException NEE) {
			currentRoom.rest();
			
			textOutput = "Your party was too tired to train. They decided to rest and increased their energies by "
					+ (tlg.mc.getEnergy()-mcInitialEnergy) + " and " + (tlg.partner.getEnergy()-partnerInitialEnergy)
					+ ".\nThey now have " + tlg.mc.getEnergy() + " and " + tlg.partner.getEnergy() + " energy.";
		}
		
		performAction(textOutput);
	}
}
