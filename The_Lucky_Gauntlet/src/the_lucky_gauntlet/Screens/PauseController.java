package the_lucky_gauntlet.Screens;

// Utility
import the_lucky_gauntlet.*;
import java.io.File;

// JavaFX Set-up
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;

// Components
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

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
		mcName.setText(tlg.mc.getName());
		mcClass.setText(tlg.mc.getClass().getSimpleName().substring(2));
		mcAttack.setText("ATK: " + tlg.mc.getAttack());
		mcHealth.setProgress((float)tlg.mc.getHealth()/tlg.mc.getMaxHealth());
		mcEnergy.setProgress((float)tlg.mc.getEnergy()/tlg.mc.getMaxEnergy());
		
		partnerName.setText(tlg.partner.getName());
		partnerClass.setText(tlg.partner.getClass().getSimpleName().substring(2));
		partnerAttack.setText("ATK: " + tlg.partner.getAttack());
		partnerHealth.setProgress((float)tlg.partner.getHealth()/tlg.partner.getMaxHealth());
		partnerEnergy.setProgress((float)tlg.partner.getEnergy()/tlg.partner.getMaxEnergy());
		
		mcWeaponName.setText(tlg.mc.getWeapon().getName());
		mcWeaponClass.setText(tlg.mc.getWeapon().getOrder()); // We have't set-up class specific weapons yet
		mcWeaponAttack.setText("ATK Bonus: " + tlg.mc.getWeapon().getAtkBonus());
		mcWeaponDurability.setProgress((float)tlg.mc.getWeapon().getDurability() /tlg.mc.getWeapon().getMaxDurability());
		
		partnerWeaponName.setText(tlg.partner.getWeapon().getName());
		partnerWeaponClass.setText(tlg.partner.getWeapon().getOrder());
		partnerWeaponAttack.setText("ATK Bonus: " + tlg.partner.getWeapon().getAtkBonus());
		partnerWeaponDurability.setProgress((float)tlg.partner.getWeapon().getDurability()/tlg.partner.getWeapon().getMaxDurability());
		
		mcPhoto.setImage(tlg.mc.getImg());
		partnerPhoto.setImage(tlg.partner.getImg());
                mcWeaponPhoto.setImage(tlg.mc.getWeapon().getImg());
                partnerWeaponPhoto.setImage(tlg.partner.getWeapon().getImg());
	}
	
	@FXML private void openMap(ActionEvent event) throws IOException {
		FXMLLoader loader = openNewWindow("Map.fxml", event);
		MapController MController = loader.getController();
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
