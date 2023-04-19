package the_lucky_gauntlet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import the_lucky_gauntlet.Exceptions.NoEnergyException;
import the_lucky_gauntlet.Exceptions.InvalidOrderException;
import the_lucky_gauntlet.Orders.*;
import the_lucky_gauntlet.Screens.*;

public class The_Lucky_Gauntlet extends Application {
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
		String filename = "Pause";
		
		Parent root = FXMLLoader.load(getClass().getResource("Screens/Home.fxml"));
		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setResizable(false);
        stage.setTitle("The Lucky Gauntlet");
		stage.show();
	}

	public static void main(String[] args) {
		Player mc = new P_Archer("Henry", "mc");
		Player partner = new P_Rouge("Joe", "partner");
		
		mc.setPartner(partner);
		partner.setPartner(mc);
		
		try {
			mc.useEnergy(mc.getEnergy()+1);
		}
		catch (NoEnergyException NEE){
			mc.stall();
		}
		
		launch(args);
	}
}
