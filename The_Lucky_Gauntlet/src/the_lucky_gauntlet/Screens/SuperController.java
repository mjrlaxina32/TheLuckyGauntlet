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
import the_lucky_gauntlet.Exceptions.InvalidOrderException;

public class SuperController {
	public static ArrayList<Stage> stageHierarchy = new ArrayList<Stage>();
	public static String actionsource = "mc"; //whether action is from mc or prt //!
	
	protected FXMLLoader openNewWindow(String fileName, ActionEvent e) {
		try {
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
		catch(NullPointerException NPE) {
			System.out.println("NullPointerException: Opening new screen terminated. Please make sure that the event that triggered the new screen is passed.");
			return null;
		}
		catch(IOException IOE) {
			System.out.println("IOException: Opening new screen terminated");
			return null;
		}
	}
	@FXML public void openPreviousWindow() {
		int hierarchyLength = stageHierarchy.size();
		Stage currentStage = stageHierarchy.get(hierarchyLength-1);
		Stage previousStage = stageHierarchy.get(hierarchyLength-2);
		
		currentStage.hide();
		previousStage.show();
		
		stageHierarchy.remove(currentStage);
	}
}
