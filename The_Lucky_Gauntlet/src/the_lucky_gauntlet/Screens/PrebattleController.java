package the_lucky_gauntlet.Screens;

// Utility
import the_lucky_gauntlet.*;

// JavaFX Set-up
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;

// Components
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.stage.*;

// Exception
import java.io.IOException;

public class PrebattleController extends SuperController implements Initializable {
	
	@FXML Text actionText, movesLeftText, mcName, partnerName;
        @FXML Button mcWeaponSelectButton, partnerWeaponSelectButton;
	@FXML ImageView mcImage, partnerImage;
	
	private int actions;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		mc = Player.getMC();
		partner = mc.getPartner();
		
		actions = 3;
		
		actionText.setText(" ");
		movesLeftText.setText("Moves Left: " + actions);
		mcName.setText(mc.getName());
		partnerName.setText(partner.getName());
	}	
	
	@FXML void openPause(ActionEvent e) throws IOException{
		FXMLLoader loader = openNewWindow("Pause.fxml", e);
		PauseController PController = loader.getController();
	}
        
        @FXML void openWeaponSelect(ActionEvent e) throws IOException{
                FXMLLoader loader = openNewWindow("WeaponSelect.fxml", e);
		WeaponSelectController WSController = loader.getController();
        }
}
