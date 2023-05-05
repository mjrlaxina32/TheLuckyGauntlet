package the_lucky_gauntlet.Rooms;

import java.util.logging.Level;
import java.util.logging.Logger;
import the_lucky_gauntlet.*;
import the_lucky_gauntlet.Exceptions.BeyondRangeException;
import the_lucky_gauntlet.Exceptions.NoEnergyException;

public class R_Peaceful extends Room{
	private int actions;
	
	public R_Peaceful(String n, boolean u, int aC) {
		super(n, u);
		actions = aC;
	}
	
	// Getter Methods
	public int getActions() {
		return actions;
	}
	
	// Group Actions
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
	public void rest() {
		tlg.mc.rest();
		actions--;
	}
	
	// Individual Actions
	public void repairWeapon(Player p) throws BeyondRangeException {
		p.getWeapon().repair();
		actions -= 1;
	}
	public void enhanceWeapon(Player p) {
		p.getWeapon().enhance();
		actions -= 1;
	}
	public void findWeapon(Player p) {
		p.newWeapon();
		actions -= 1;
	}
}
