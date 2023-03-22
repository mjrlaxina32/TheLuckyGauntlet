package the_lucky_gauntlet.Screens;

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

public class MapController implements Initializable{
	private String lastFileName;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {}
	
	private FXMLLoader openNewWindow(String fileName, ActionEvent e) throws IOException{
		// Getting the current Window
		Stage currentStage = (Stage)((Node) e.getSource()).getScene().getWindow();

		// Getting the next Screen
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
		Parent root = loader.load();
		Scene newScene = new Scene(root);
		
		// Updating the screen
		currentStage.hide();
		currentStage.setScene(newScene);
		currentStage.show();
		
		return loader;
	}
	
	public void updateLastScreen (String fileName) {
		lastFileName = fileName;
	}
	
	@FXML public void openPreviousScreen(ActionEvent e) throws IOException {
		openNewWindow(lastFileName, e);
	}
}
