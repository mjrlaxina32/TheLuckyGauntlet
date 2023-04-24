package the_lucky_gauntlet.Screens;

// Utility
import the_lucky_gauntlet.*;

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
	
	private int actions;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {		
		actions = 3;
		
		actionText.setText(" ");
		movesLeftText.setText("Moves Left: " + actions);
		mcName.setText(mc.getName());
		partnerName.setText(partner.getName());
                mcImage.setImage(mc.getImg());
		partnerImage.setImage(partner.getImg());
	}	
	
	private void performAction(String textOutput) {
		actions -= 1;
		movesLeftText.setText("Moves Left: " + actions);
		actionText.setText(textOutput);
		
		if(actions == 0) {
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
	
	@FXML void openPause(ActionEvent e) throws IOException{
		openNewWindow("Pause.fxml", e);
	}
	@FXML void openWeaponSelect(ActionEvent e) throws IOException{
		openNewWindow("WeaponSelect.fxml", e);
	}
	
	@FXML void rest(ActionEvent e) throws IOException{
		int mcInitialEnergy, mcFinalEnergy, partnerInitialEnergy, partnerFinalEnergy;
		mcInitialEnergy = mc.getEnergy();
		partnerInitialEnergy = partner.getEnergy();
		
		mc.rest();
		
		mcFinalEnergy = mc.getEnergy();
		partnerFinalEnergy = partner.getEnergy();
		String textOutput = mc.getName() + " and " + partner.getName() + " rested!\n"
				+ "Their energies increased by " + (mcFinalEnergy-mcInitialEnergy) + " and " + (partnerFinalEnergy-partnerInitialEnergy)
				+ " and are now " + mcFinalEnergy + " and " + partnerFinalEnergy + "!";

		performAction(textOutput);
	}
	@FXML void train(ActionEvent e) throws IOException{
		int mcInitialAtk, mcFinalAtk, partnerInitialAtk, partnerFinalAtk;
		int mcInitialEnergy, mcFinalEnergy, partnerInitialEnergy, partnerFinalEnergy;
		String textOutput;
		
		mcInitialAtk = mc.getAttack();
		partnerInitialAtk = partner.getAttack();
		mcInitialEnergy = mc.getEnergy();
		partnerInitialEnergy = partner.getEnergy();
		
		try {
			mc.train();
			partner.train();
			
			mcFinalAtk = mc.getAttack();
			partnerFinalAtk = partner.getAttack();
			mcFinalEnergy = mc.getEnergy();
			partnerFinalEnergy = partner.getEnergy();
		
			textOutput = mc.getName() + " and " + partner.getName() + " trained together!\n"
				+ "Their atk power increased by " + (mcFinalAtk-mcInitialAtk) + " and " + (partnerFinalAtk-partnerInitialAtk)
				+ " at the cost of 25 energy!\nThey now have an attack power of " + mcFinalAtk + " and " + partnerFinalAtk + "!";
		}
		catch(NoEnergyException NEE) {
			mc.rest();
			
			mcFinalEnergy = mc.getEnergy();
			partnerFinalEnergy = partner.getEnergy();
			
			textOutput = "Your party was too tired to train. They decided to rest and increased their energies by "
					+ (mcFinalEnergy-mcInitialEnergy) + " and " + (partnerFinalEnergy-partnerInitialEnergy)
					+ ".\nThey now have " + mcFinalEnergy + " and " + partnerFinalEnergy + " energy.";
		}
		
		performAction(textOutput);		
	}
	
}
