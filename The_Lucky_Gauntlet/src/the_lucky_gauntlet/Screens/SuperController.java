package the_lucky_gauntlet.Screens;

// Utility Methods
import the_lucky_gauntlet.*;
import java.util.ArrayList;

// JavaFX Set-up
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;

// Components
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Events
import javafx.event.ActionEvent;

// Exceptions
import java.io.IOException;

public class SuperController {
	public Player mc = Player.getMC();
	public Player partner = mc.getPartner();
	
	public static ArrayList<Stage> stageHierarchy = new ArrayList<Stage>();
	
	protected FXMLLoader openNewWindow(String fileName, ActionEvent e) throws IOException{
		// Getting the current Window
		
		Stage currentStage = (Stage)((Node) e.getSource()).getScene().getWindow();

		// Getting the next Screen
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
		Parent root = loader.load();
		Scene newScene = new Scene(root);
		Stage secondStage = new Stage();
		secondStage.setScene(newScene);

		// Adding the next screen to the screen hierarchy
		SuperController.stageHierarchy.add(secondStage);
		
		
		// Updating the screen
		currentStage.hide();
		secondStage.show();

		return loader;
	}
	@FXML public void openPreviousWindow(ActionEvent e) throws IOException {
		int hierarchyLength = stageHierarchy.size();
		Stage currentStage = stageHierarchy.get(hierarchyLength-1);
		Stage previousStage = stageHierarchy.get(hierarchyLength-2);
		
		currentStage.hide();
		previousStage.show();
		
		stageHierarchy.remove(currentStage);
	}
}
