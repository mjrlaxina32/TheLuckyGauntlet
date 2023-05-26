package the_lucky_gauntlet.Rooms;

import java.util.logging.Level;
import java.util.logging.Logger;
import the_lucky_gauntlet.*;
import the_lucky_gauntlet.Exceptions.BeyondRangeException;
import the_lucky_gauntlet.Exceptions.NoEnergyException;
/**
 * R_Peaceful is extended from Room. 
 * It also contains an integer number of actions that can be done in the room.
 * @author Athena Kimwell
 * @version 5-26-23
 */
public class R_Peaceful extends Room{
	private int actions;
	/**
         * Creates a Peaceful Room with a name, status, and action count.
         * @param n Name of the room
         * @param u Status of the room
         * @param aC Action count of the room
         */
	public R_Peaceful(String n, boolean u, int aC) {
		super(n, u);
		actions = aC;
	}
	
	// Getter Methods
        /**
         * @return Returns action count
         */
	public int getActions() {
		return actions;
	}
	
	// Group Actions
        /**
         * Runs the train method from Player class on both player and partner, and decreases action count by 1.
         * @throws NoEnergyException Thrown when Player does not have enough energy.
         */
	public void train() throws NoEnergyException {		
		try {
			tlg.mc.train();
			tlg.partner.train();
			actions--;
		}
		catch (NoEnergyException NEE) {
			tlg.mc.rest();
			throw new NoEnergyException();
		}		
	}
        /**
         * Runs the rest method from Player class, and decreases action count by 1.
         */
	public void rest() {
		tlg.mc.rest();
		actions--;
	}
	
	// Individual Actions
        /**
         * Runs the repair method from the Weapon class on the weapon of the player 
         * and decreases action count by 1.
         * @param p Player whose weapon is being repaired
         * @throws BeyondRangeException
         */
	public void repairWeapon(Player p) throws BeyondRangeException {
		p.getWeapon().repair();
		actions -= 1;
	}
        /**
         * Runs the enhance method from the Weapon class on the weapon of the player 
         * and decreases the action count by 1.
         * @param p Player whose weapon is being enhanced
         */
	public void enhanceWeapon(Player p) {
		p.getWeapon().enhance();
		actions -= 1;
	}
        /**
         * Runs the newWeapon method from the Player class on the player 
         * and decreases the action count by 1.
         * @param p Player who is finding a new weapon
         */
	public void findWeapon(Player p) {
		p.newWeapon();
		actions -= 1;
	}
}
