package the_lucky_gauntlet.Screens;

// Utility
import java.util.ArrayList;

// Utility
import javafx.scene.control.Button;

// JavaFX Set-up
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;

// Lucky Gauntlet Files
import the_lucky_gauntlet.*;
import the_lucky_gauntlet.Rooms.*;

// Events
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class MapController extends SuperController implements Initializable{
	private ArrayList<Room> map;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		map = Room.getAllRooms();	
	}
	
	@FXML private void highlight(MouseEvent event){
		String fullRoomData = event.getSource().toString();
		int roomIndex = Integer.parseInt(fullRoomData.substring(14,16));
		Room highlightedRoom = map.get(roomIndex);
		
		((Button)event.getSource()).setPrefSize(55, 93.5);
		
		if(highlightedRoom.isCompleted()) {
			((Button)event.getSource()).setStyle("-fx-border-color: green; -fx-border-width: 3;");		
		}
		else {
			((Button)event.getSource()).setStyle("-fx-border-color: red; -fx-border-width: 3;");	
		}
	}
	@FXML private void dehighlight(MouseEvent event){      
		((Button)event.getSource()).setStyle("");
		((Button)event.getSource()).setPrefSize(50, 85);
	}
	
	@FXML void openRoom(ActionEvent e) {
		String fullRoomData = e.getSource().toString();
		int roomIndex = Integer.parseInt(fullRoomData.substring(14,16));
		System.out.println(roomIndex);
		
		Room newRoom = Room.getRoom(roomIndex);
		String roomType = newRoom.getClass().toString().substring(33);
		
		
		if (roomType.equals("Peaceful")) {
			System.out.println("Open prebattle");
			openPrebattle(e, roomIndex);
		}
		else if(roomType.equals("Battle")) {
			openBattle(e, roomIndex);
		}
	}
	private void openBattle(ActionEvent e, int roomIndex) {
		FXMLLoader loader = openNewWindow("Battle.fxml", e);
		BattleController BController = loader.getController();
		R_Battle newRoom = (R_Battle) Room.getRoom(roomIndex);
		BController.enterRoom(newRoom);
		tlg.mc.targetSelect(BController.getCurrentRoom().getAllEnemies().get(0));
		tlg.partner.targetSelect(BController.getCurrentRoom().getAllEnemies().get(0));
		
		tlg.currentRoom = BController.getCurrentRoom();
		BController.delayedInitialize();
	}
	private void openPrebattle(ActionEvent e, int roomIndex) {
		FXMLLoader loader = openNewWindow("Prebattle.fxml", e);
		PrebattleController PController = loader.getController();
		R_Peaceful newRoom = (R_Peaceful) Room.getRoom(roomIndex);
		PController.enterRoom(newRoom);
		
		tlg.currentRoom = PController.getCurrentRoom();
		PController.delayedInitialize();
	}
}
