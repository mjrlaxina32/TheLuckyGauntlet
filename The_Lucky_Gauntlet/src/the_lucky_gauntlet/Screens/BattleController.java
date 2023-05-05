/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package the_lucky_gauntlet.Screens;	

// Java-FX Set-up
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;

// Components
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

// Lucky Gauntlet Files
import the_lucky_gauntlet.Rooms.R_Battle;
import the_lucky_gauntlet.*;

// Events
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

// Exceptions
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author Athena Kimwell
 */
public class BattleController extends SuperController implements Initializable {
	@FXML Text enemy1Name, enemy2Name, enemy3Name, mcName, partnerName, actionText;
	@FXML Button mcAttackButton, mcStallButton, mcSkillButton, partnerAttackButton, partnerStallButton, partnerSkillButton;
	@FXML ProgressBar enemy1Health, enemy2Health, enemy3Health, mcHealth, partnerHealth;
	@FXML ProgressBar enemy1Energy, enemy2Energy, enemy3Energy, mcEnergy, partnerEnergy;
	@FXML ImageView enemy1Image, enemy2Image, enemy3Image, mcImage, partnerImage;
	
	private R_Battle currentRoom;
	
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
	public void delayedInitialize(){
		// Lets the current room be updated first before initialization
		actionText.setText("What's the plan?");
		completeRoom();
		updateStats();
	}
    
	// General Methods
	@FXML void openPause(ActionEvent e) throws IOException{
		openNewWindow("Pause.fxml", e);
	}
	@FXML public void selectEnemy(MouseEvent e) {
		String fullEnemyData = e.getSource().toString();
		Integer enemyIndex = Integer.parseInt(fullEnemyData.substring(18,19))-1;
		
		tlg.mc.targetSelect(currentRoom.getAllEnemies().get(enemyIndex));
		tlg.partner.targetSelect(currentRoom.getAllEnemies().get(enemyIndex));
	}
	
	// Utility Methods (Room Handling)
	public void enterRoom(R_Battle newRoom) {
		currentRoom = newRoom;
	}
	public R_Battle getCurrentRoom() {
		return currentRoom;
	}
	public void completeRoom() {
		for(Enemy e : currentRoom.getAllEnemies()){
			if(!e.isDead()) {
				return;
			}
		}
		
		currentRoom.completeRoom();
		mcAttackButton.setDisable(true);
		mcSkillButton.setDisable(true);
		mcStallButton.setDisable(true);
		partnerAttackButton.setDisable(true);
		partnerSkillButton.setDisable(true);
		partnerStallButton.setDisable(true);
		
		actionText.setText("This dungeon has been cleared!");
	}
	
	// Display Updating Methods
	public void updateEnemyStats() {
		float[] health = new float[3];
		float[] energy = new float[3];
		String[] name = new String[3];
		Image[] images = new Image[3];
		
		for(int i=0;i<3;i++) {
			Enemy e = currentRoom.getAllEnemies().get(i);
			health[i] = (float)e.getHealth() / (float)e.getMaxHealth();
			energy[i] = (float)e.getEnergy() / (float)e.getMaxEnergy();
			name[i] = e.getName();
			images[i] = e.getImg();
		}
		
		enemy1Name.setText(name[0]);
		enemy1Health.setProgress(health[0]);
		enemy1Energy.setProgress(energy[0]);
		enemy1Image.setImage(images[0]);
		
		enemy2Name.setText(name[1]);
		enemy2Health.setProgress(health[1]);
		enemy2Energy.setProgress(energy[1]);
		enemy2Image.setImage(images[1]);
		
		enemy3Name.setText(name[2]);
		enemy3Health.setProgress(health[2]);
		enemy3Energy.setProgress(energy[2]);
		enemy3Image.setImage(images[2]);
	}
	public void updatePlayerStats() {
		float[] health = new float[2];
		float[] energy = new float[2];
		String[] name = new String[2];
		Image[] images = new Image[2];
		Player[] players = {tlg.mc , tlg.partner};
		
		for(int i=0;i<2;i++) {
			Player p = players[i];
			health[i] = (float)p.getHealth() / (float)p.getMaxHealth();
			energy[i] = (float)p.getEnergy() / (float)p.getMaxEnergy();
			name[i] = p.getName();
			images[i] = p.getImg();
		}
		
		mcName.setText(name[0]);
		mcHealth.setProgress(health[0]);
		mcEnergy.setProgress(energy[0]);
		
		partnerName.setText(name[1]);
		partnerHealth.setProgress(health[1]);
		partnerEnergy.setProgress(energy[1]);
	}
	public void updateStats() {
		updateEnemyStats();
		updatePlayerStats();
	}
	
	// Combat Methods
	public void attackEnemy(ActionEvent e, Player player) {				
		int initialHealth = player.getTarget().getHealth();
		Enemy initialTarget = (Enemy) player.getTarget();
		
		String textOutput = "";
		
		player.attack();
		updateStats();
		
		int damage = initialHealth - initialTarget.getHealth();
		System.out.println(initialTarget.getName() + "'s Initial Health: " + initialHealth);
		System.out.println(initialTarget.getName() + "'s Current Health: " + initialTarget.getHealth());
		if(damage > 0) {
			textOutput = player.getName() + " used 20 energy to \nattack "
					+ initialTarget.getName() + " and dealt " + damage + " \ndamage!";
			if(player.hasEffect("Hawkeye")) {
				if(damage > (player.getAttack()*2)) {
					textOutput += " Critical Hit!";
				}
			}
			if(initialTarget.isDead()) {
				textOutput += " " + initialTarget.getName() + " has died!";
			}
		}
		else {
			textOutput = player.getName() + " and " + player.getPartnerName()
					+ " were too \ntired to fight! They rested and \nboth regained 50 energy!";
		}
		
		actionText.setText(textOutput);
		completeRoom();
	}
	@FXML public void mcAttack(ActionEvent e) {
		attackEnemy(e,tlg.mc);
	}
	@FXML public void partnerAttack(ActionEvent e) {
		attackEnemy(e,tlg.partner);
	}
	
	@FXML public void stall(ActionEvent e) {
		String textOutput = "";
		
		tlg.mc.stall();
		updateStats();
		
		textOutput = tlg.mc.getName() + " and " + tlg.partner.getName()
					+ " stalled\n and both regained 50 energy!";
	}
	
	public void skill(ActionEvent e, Player player) {
		String textOutput;
		
		String order = player.getClass().getSimpleName().substring(2);
		player.useSkill();
		
		switch(order) {
			case ("Archer"):
				textOutput = player.getName() + " concentrated on his foes!\n";
				break;
			case ("Bard"):
				textOutput = player.getName() + " played a little tune! your party regained 150 energy!";
				break;
			case ("Cleric"):
				textOutput = player.getName() + " cast a revival spell! Your party healed by 40 hp!";
				break;
			case ("Knight"):
				textOutput = player.getName() + " gathered his resolve!\n";
				break;
			case ("Mage"):
				textOutput = player.getName() + " cast a powerful explosion dealing " + player.getAttack() + " damage to all enemies!\n";
				break;
			case ("Rogue"):
				textOutput = player.getName() + " vanished into a puff of smoke!\n";
				break;
			default:
				textOutput = "Generic skill text here";
				break;
		}
		
		actionText.setText(textOutput);
		updateStats();
		completeRoom();
	}
	@FXML public void mcSkill(ActionEvent e) {
		skill(e, tlg.mc);
	}
	@FXML public void partnerSkill(ActionEvent e) {
		skill(e, tlg.partner);
	}
}

