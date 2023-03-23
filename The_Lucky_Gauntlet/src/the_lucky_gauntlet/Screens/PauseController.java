package the_lucky_gauntlet.Screens;

// Utility
import the_lucky_gauntlet.*;

// JavaFX Set-up
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;

// Components
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;

// Alert Components
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;
import java.util.Optional;

// Events
import javafx.event.ActionEvent;

// Exceptions
import java.io.IOException;

public class PauseController extends SuperController implements Initializable {
	
	@FXML ImageView mcPhoto, partnerPhoto, mcWeaponPhoto, partnerWeaponPhoto;
	@FXML Text mcName, mcClass, mcAttack;
	@FXML ProgressBar mcHealth, mcEnergy;
	@FXML Text partnerName, partnerClass, partnerAttack;
	@FXML ProgressBar partnerHealth, partnerEnergy;
	@FXML Text mcWeaponName, mcWeaponClass, mcWeaponAttack;
	@FXML ProgressBar mcWeaponDurability;
	@FXML Text partnerWeaponName, partnerWeaponClass, partnerWeaponAttack;
	@FXML ProgressBar partnerWeaponDurability;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		mc = Player.getMC();
		partner = mc.getPartner();
		
		mcName.setText(mc.getName());
		mcClass.setText(mc.getClass().getSimpleName().substring(2));
		mcAttack.setText("ATK: " + mc.getAttack());
		mcHealth.setProgress((float)mc.getHealth()/mc.getMaxHealth());
		mcEnergy.setProgress((float)mc.getEnergy()/mc.getMaxEnergy());
		
		partnerName.setText(partner.getName());
		partnerClass.setText(partner.getClass().getSimpleName().substring(2));
		partnerAttack.setText("ATK: " + partner.getAttack());
		partnerHealth.setProgress((float)partner.getHealth()/partner.getMaxHealth());
		partnerEnergy.setProgress((float)partner.getEnergy()/partner.getMaxEnergy());
		
		mcWeaponName.setText(mc.getWeapon().getName());
		mcWeaponClass.setText("Class"); // We have't set-up class specific weapons yet
		mcWeaponAttack.setText("ATK Bonus: " + mc.getWeapon().getAtkBonus());
		mcWeaponDurability.setProgress((float)mc.getWeapon().getDurability() /mc.getWeapon().getMaxDurability());
		
		partnerWeaponName.setText(partner.getWeapon().getName());
		partnerWeaponClass.setText("Class"); // We have't set-up class specific weapons yet
		partnerWeaponAttack.setText("ATK Bonus: " + partner.getWeapon().getAtkBonus());
		partnerWeaponDurability.setProgress((float)partner.getWeapon().getDurability()/partner.getWeapon().getMaxDurability());
	}
	
	@FXML private void openMap(ActionEvent event) throws IOException {
		FXMLLoader loader = openNewWindow("Map.fxml", event);
		MapController MController = loader.getController();
		
		//MController.setLastScreen("Pause.fxml");
	}
	@FXML private void openMenu(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("End Game");
		alert.setHeaderText("Are you sure that you want to close the game?");
		alert.setContentText("All your progress will be lost");
		
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
			openNewWindow("Home.fxml", event);
		}
	}
}
