package the_lucky_gauntlet.Screens;

// JavaFX Set-up
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;

// Lucky Gauntlet Files
import the_lucky_gauntlet.*;
import the_lucky_gauntlet.Rooms.*;

// Components
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Events
import javafx.event.ActionEvent;

// Exceptions
import java.io.IOException;

public class MapController extends SuperController implements Initializable{
	private String lastFileName;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {}
	
	@FXML void openRoom(ActionEvent e) throws IOException {
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
	private void openBattle(ActionEvent e) throws IOException{
		openNewWindow("Battle.fxml", e);
	}
	private void openPrebattle(ActionEvent e) throws IOException{
		FXMLLoader loader = openNewWindow("Prebattle.fxml", e);
		PrebattleController PController = loader.getController();
		R_Peaceful newRoom = (R_Peaceful) Room.getRoom(roomIndex);
		PController.enterRoom(newRoom);
		
		PController.delayedInitialize();
	}
}
