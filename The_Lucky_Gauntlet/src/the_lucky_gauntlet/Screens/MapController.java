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

public class MapController extends SuperController implements Initializable{
	private String lastFileName;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {}
	
	@FXML void openBattle(ActionEvent e) throws IOException{
		openNewWindow("Battle.fxml", e);
	}
	@FXML void openPrebattle(ActionEvent e) throws IOException{
		openNewWindow("Prebattle.fxml", e);
	}
}
