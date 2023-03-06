package the_lucky_gauntlet.Screens;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import the_lucky_gauntlet.*;

public class PauseController implements Initializable {
	
	@FXML ImageView mcPhoto, partnerPhoto, mcWeaponPhoto, partnerWeaponPhoto;
	@FXML Text mcName, mcClass, mcAttack, mcHealth, mcEnergy;
	@FXML Text partnerName, partnerClass, partnerAttack, partnerHealth, partnerEnergy;
	@FXML Text mcWeaponName, mcWeaponClass, mcWeaponAttack, mcWeaponDurability;
	@FXML Text partnerWeaponName, partnerWeaponClass, partnerWeaponAttack, partnerWeaponDurability;
	
	Player mc, partner;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		mc = Player.getMC();
		partner = mc.getPartner();
		
		mcName.setText(mc.getName());
		mcClass.setText(mc.getClass().getSimpleName().substring(2));
		mcAttack.setText("ATK: " + mc.getAttack());
		mcHealth.setText(mc.getHealth() + "/" + mc.getMaxHealth());
		mcEnergy.setText(mc.getEnergy() + "/" + mc.getMaxEnergy());
		
		partnerName.setText(partner.getName());
		partnerClass.setText(partner.getClass().getSimpleName().substring(2));
		partnerAttack.setText("ATK: " + partner.getAttack());
		partnerHealth.setText(partner.getHealth() + "/" + partner.getMaxHealth());
		partnerEnergy.setText(partner.getEnergy() + "/" + partner.getMaxEnergy());
		
		mcWeaponName.setText(mc.getWeapon().getName());
		mcWeaponClass.setText("Class"); // We have't set-up class specific weapons yet
		mcWeaponAttack.setText("ATK Bonus: " + mc.getWeapon().getAtkBonus());
		mcWeaponDurability.setText(mc.getWeapon().getDurability() + "/" + mc.getWeapon().getMaxDurability());
		
		partnerWeaponName.setText(partner.getWeapon().getName());
		partnerWeaponClass.setText("Class"); // We have't set-up class specific weapons yet
		partnerWeaponAttack.setText("ATK Bonus: " + partner.getWeapon().getAtkBonus());
		partnerWeaponDurability.setText(partner.getWeapon().getDurability() + "/" + partner.getWeapon().getMaxDurability());
	}
}