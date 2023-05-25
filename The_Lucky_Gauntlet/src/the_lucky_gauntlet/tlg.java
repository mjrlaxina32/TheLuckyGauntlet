package the_lucky_gauntlet;

// Utility
import java.util.Random;

// JavaFX Components
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Events
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

// Lucky Gaunltet Imports
import the_lucky_gauntlet.Exceptions.NoEnergyException;
import the_lucky_gauntlet.Exceptions.InvalidOrderException;
import the_lucky_gauntlet.Orders.*;
import the_lucky_gauntlet.Rooms.*;
import the_lucky_gauntlet.Screens.*;

public class tlg extends Application {
	public static Player mc, partner;
	public static Room currentRoom;
	
	public static Player PlayerCreation(int order, String n, String type) throws InvalidOrderException {
		switch (order) {
			case 1:
				return (new P_Archer(n, type));
			case 2:
				return (new P_Bard(n, type));
			case 3:
				return (new P_Cleric(n, type));
			case 4:
				return (new P_Knight(n, type));
			case 5:
				return (new P_Mage(n, type));
			case 6:
				return (new P_Rogue(n, type));
			default:
				throw new InvalidOrderException("That Order does not exist.");
		}
	}
	
	@Override
	public void start(Stage stage) throws Exception{
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				Platform.exit();
				System.exit(0);
			}
		});
		Parent root = FXMLLoader.load(getClass().getResource("Screens/Home.fxml"));
		Scene scene = new Scene(root);
		
		SuperController.stageHierarchy.add(stage);
		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("The Lucky Gauntlet");
		stage.show();
	}

	public static void main(String[] args) {
		Room.resetRooms();
		mc = new P_Archer("Henry", "mc");
		partner = new P_Rogue("Joe", "partner");
		
		mc.setPartner(partner);
		partner.setPartner(mc);
		
		try {
			mc.useEnergy(mc.getEnergy());
			partner.useEnergy(partner.getEnergy());
		}
		catch (NoEnergyException NEE){
			mc.stall();
		}
		
		launch(args);
	}
}
