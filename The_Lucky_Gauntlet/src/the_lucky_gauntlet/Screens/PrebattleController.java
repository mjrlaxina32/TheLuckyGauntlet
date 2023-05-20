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

// Exception
import java.io.IOException;
import the_lucky_gauntlet.Exceptions.NoEnergyException;

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
	@FXML void openWeaponSelect(ActionEvent e) throws IOException{
                actionsource = (((Button)e.getSource()).getId()).replace("ChangeWeapon","");
                System.out.println(actionsource);
                openNewWindow("WeaponSelect.fxml", e);
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
				+ "Their atk power increased by 5 at the cost of 25 energy!\nThey now have an attack power of "
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
