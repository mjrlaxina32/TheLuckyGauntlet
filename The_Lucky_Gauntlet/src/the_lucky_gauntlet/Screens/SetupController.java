package the_lucky_gauntlet.Screens;

// Utility
import java.util.Random;

// Java-fx Set-up
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;

// Components
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

// Events
import javafx.event.ActionEvent;

// Exceptions
import the_lucky_gauntlet.Exceptions.InvalidOrderException;
import java.io.IOException;

// Lucky Gauntlet Imports
import the_lucky_gauntlet.tlg;
import the_lucky_gauntlet.Room;

public class SetupController extends SuperController implements Initializable {
	@FXML Text mcAtk, mcEnergy, mcHealth, partnerOrder, partnerAtk, partnerEnergy, partnerHealth;
	@FXML TextField mcName, partnerName;
	@FXML ChoiceBox mcOrder;
	@FXML Button startButton;
	
	private String[] orders = {"Archer", "Bard", "Cleric", "Knight", "Mage", "Rouge"};
	
	private Random rand = new Random();
	boolean stillRolling = true;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Room.resetRooms();
		for (String orderName : orders){
			mcOrder.getItems().add(orderName);
		}
	}
	
	public void updateStats() {
		mcAtk.setText("ATK: " + tlg.mc.getAttack());
		mcEnergy.setText("Max Energy: " + tlg.mc.getMaxEnergy());
		mcHealth.setText("Max Health: " + tlg.mc.getMaxHealth());
		
		partnerAtk.setText("ATK: " + tlg.partner.getAttack());
		partnerEnergy.setText("Max Energy: " + tlg.partner.getMaxEnergy());
		partnerHealth.setText("Max Health: " + tlg.partner.getMaxHealth());
		partnerOrder.setText(tlg.partner.getClass().getSimpleName().substring(2));
	}
	public void rollStats() {
		String orderChoice = (String) mcOrder.getValue();
		
		try {
			int orderIndex = 0;
			
			for (int i = 1; i <= orders.length; i++) {
				if(orderChoice.equals(orders[i-1])) {
					orderIndex = i;
					break;
				}
			}
			tlg.mc = tlg.PlayerCreation(orderIndex, mcName.getText(), "mc");
			
			orderIndex = rand.nextInt(6) + 1;
			tlg.partner = tlg.PlayerCreation(orderIndex, partnerName.getText(), "partner");
			
			tlg.mc.setPartner(tlg.partner);
			tlg.partner.setPartner(tlg.mc);
			
			startButton.setText("Start");
			
			updateStats();
			
			stillRolling = false;
			mcName.setEditable(false);
			mcOrder.setDisable(true);
			partnerName.setEditable(false);
		}
		catch (NullPointerException NPE) {
			System.out.println("Please select a class");
		}
		catch (InvalidOrderException IOE) {
			System.out.println(IOE.getMessage());
		}
	}
	
	@FXML public void buttonPress (ActionEvent e) throws IOException {
		if(stillRolling) {
			rollStats();
		}
		else {
			openNewWindow("Map.fxml", e);
		}
	}
}
