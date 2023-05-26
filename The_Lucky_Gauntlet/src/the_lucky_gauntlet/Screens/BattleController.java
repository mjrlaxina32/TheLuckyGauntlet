/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package the_lucky_gauntlet.Screens;	

// Utility
import java.util.ArrayList;

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

// Timer
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

// Lucky Gauntlet Files
import the_lucky_gauntlet.Rooms.R_Battle;
import the_lucky_gauntlet.*;

// Events
import javafx.event.ActionEvent;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


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
	@FXML Button leaveRoom;
	
	private R_Battle currentRoom;
	private String textOutput;
	
	private Timer timer;
	private TimerTask task;
	private final int timerDelay = 2000;
	
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	    actionText.setWrappingWidth(160);
    }
	public void delayedInitialize(){
		// Lets the current room be updated first before initialization
		textOutput = "What's the plan?";
		completeRoom();
		updateStats();
		textOutput = "";
	}
    
	// General Methods
	@FXML void openPause(ActionEvent e) {
		openNewWindow("Pause.fxml", e);
	}
	@FXML public void selectEnemy(MouseEvent e) {
		String fullEnemyData = e.getSource().toString();
		Integer enemyIndex = Integer.parseInt(fullEnemyData.substring(18,19))-1;
		
		tlg.mc.targetSelect(currentRoom.getAllEnemies().get(enemyIndex));
		tlg.partner.targetSelect(currentRoom.getAllEnemies().get(enemyIndex));
	}
        
        @FXML private void highlight(MouseEvent event){
            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.GREEN);
            dropShadow.setHeight(15); 
            dropShadow.setWidth(15); 
            dropShadow.setRadius(15); 

            ((ImageView)event.getSource()).setEffect(dropShadow);
            ((ImageView)event.getSource()).setFitHeight(230);
        }
    
        @FXML private void dehighlight(MouseEvent event){      
            ((ImageView)event.getSource()).setEffect(null);
            ((ImageView)event.getSource()).setFitHeight(115);
        }
	
	// Button Disabling
	public void disableButtons() {
		mcAttackButton.setDisable(true);
		mcSkillButton.setDisable(true);
		mcStallButton.setDisable(true);
		partnerAttackButton.setDisable(true);
		partnerSkillButton.setDisable(true);
		partnerStallButton.setDisable(true);
	}
	public void enableButtons() {
		mcAttackButton.setDisable(false);
		mcSkillButton.setDisable(false);
		mcStallButton.setDisable(false);
		partnerAttackButton.setDisable(false);
		partnerSkillButton.setDisable(false);
		partnerStallButton.setDisable(false);
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
		this.disableButtons();
		
		leaveRoom.setVisible(true);
		leaveRoom.setDisable(false);
		
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
		actionText.setText(textOutput);
	}
	
	// Combat Methods
	class enemyAttackDelay extends TimerTask {
		private ActionEvent e;
		
		enemyAttackDelay(ActionEvent e) {
			this.e = e;
		}
		
		@Override
		public void run() {
			Platform.runLater(() ->{
				System.out.println("Action was delayed");				
				
				textOutput = "";
				for(Enemy enemy : currentRoom.getAllEnemies()) {
					if(!enemy.isDead()) {
						enemy.targetSelect();
						int initialHP = enemy.getTarget().getHealth();
						enemy.attack();
						textOutput += enemy.getName() + " attacked " + enemy.getTargetName()
								+ " and dealt " + Integer.toString(enemy.getAttack() + enemy.getWeapon().getAtkBonus())
								+ " damage!\n";
						int damage = initialHP - enemy.getTarget().getHealth();
						// Checks if the target is a Rouge and if it took no damage (dodged)
						if(enemy.getTarget().getClass().getSimpleName().equals("P_Rogue") && damage == 0){
							textOutput += enemy.getTargetName() + " dodged the attack!\n"; 
						}
						// Checks if the target is a Knight and if it took less damage (resisted)
						if(enemy.getTarget().getClass().getSimpleName().equals("P_Knight") && damage < enemy.getAttack() + enemy.getWeapon().getAtkBonus()){
							textOutput += enemy.getTargetName() + " stood firm and resisted part of the damage!\n"; 
						}
						if(enemy.getTarget().isDead()) {
							openNewWindow("Lose.fxml", e);
						}
					}
				}
				updateStats();
			});
		}
	}
	class skillDecreaseDelay extends TimerTask {	
		@Override
		public void run() {
			Platform.runLater(() ->{
				System.out.println("Action was delayed");				
				
				textOutput = "";
				
				ArrayList<String> removedEffects = tlg.mc.decreaseAllEffectDurations();
				for(String effect : removedEffects) {
					textOutput += tlg.mc.getName() + "'s \"" + effect + "\" ended!\n";
				}
				removedEffects = tlg.partner.decreaseAllEffectDurations();
				for(String effect : removedEffects) {
					textOutput += tlg.partner.getName() + "'s \"" + effect + "\" ended!\n";
				}

				textOutput += "What's the Plan?";
				
				enableButtons();
				updateStats();
	
				timer.cancel();
			});
		}

	}
	public void endTurn(ActionEvent e) {		
		disableButtons();
		updateStats();
		
		completeRoom();
		
		timer = new Timer();
		System.out.println("Enemy attack was delayed");
		timer.schedule(new enemyAttackDelay(e), timerDelay);
		
		timer.schedule(new skillDecreaseDelay(), 2*timerDelay);
	}
	public void attackEnemy(ActionEvent e, Player player) {				
		int initialHealth = player.getTarget().getHealth();
		Enemy initialTarget = (Enemy) player.getTarget();
		
		textOutput = "";
		
		player.attack();
		
		int damage = initialHealth - initialTarget.getHealth();
		System.out.println(initialTarget.getName() + "'s Initial Health: " + initialHealth);
		System.out.println(initialTarget.getName() + "'s Current Health: " + initialTarget.getHealth());
		if(damage > 0) {
			textOutput = player.getName() + " used 20 energy to attack "
					+ initialTarget.getName() + " and dealt " + damage + " damage!";
			if(player.hasEffect("Hawkeye")) {
				if(damage > (player.getAttack()+player.getWeapon().getAtkBonus())) {
					textOutput += " Critical Hit!";
				}
			}
			if(initialTarget.isDead()) {
				textOutput += " " + initialTarget.getName() + " has died!";
			}
		}
		else {
			textOutput = player.getName() + " and " + player.getPartnerName()
					+ " were too tired to fight! They rested and both regained 50 energy!";
		}
		
		endTurn(e);
	}
	@FXML public void mcAttack(ActionEvent e) {
		attackEnemy(e,tlg.mc);
	}
	@FXML public void partnerAttack(ActionEvent e) {
		attackEnemy(e,tlg.partner);
	}
	
	@FXML public void stall(ActionEvent e) {
		textOutput = "";
		
		tlg.mc.stall();
		
		textOutput = tlg.mc.getName() + " and " + tlg.partner.getName()
					+ " stalled\n and both regained 50 energy!";
		
		endTurn(e);
	}
	
	public void skill(ActionEvent e, Player player) {
		int initialEnergy = player.getEnergy();
		
		String order = player.getClass().getSimpleName().substring(2);
		player.useSkill();
		
		int finalEnergy = player.getEnergy();
		
		if(initialEnergy < finalEnergy){
			textOutput = player.getName() + " didn't have enough energy to use their skill! "
					+ tlg.mc.getName() + " and " + tlg.partner.getName()
					+ " stalled\n and both regained 50 energy!";
		}
		else {
			switch(order) {
				case ("Archer"):
					textOutput = player.getName() + " concentrated on their foes!\n";
					break;
				case ("Bard"):
					textOutput = player.getName() + " played a little tune! Your party regained 150 energy!";
					break;
				case ("Cleric"):
					textOutput = player.getName() + " cast a revival spell! Your party healed by 20 hp!";
					break;
				case ("Knight"):
					textOutput = player.getName() + " gathered their resolve!\n";
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
		endTurn(e);
		}
	}
	@FXML public void mcSkill(ActionEvent e) {
		skill(e, tlg.mc);
	}
	@FXML public void partnerSkill(ActionEvent e) {
		skill(e, tlg.partner);
	}
}
