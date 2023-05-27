package the_lucky_gauntlet.Orders;

import the_lucky_gauntlet.*;
import the_lucky_gauntlet.Exceptions.NoEnergyException;
/**
 * P_Rouge is extended from Player.
 * @author Athena Kimwell
 * @version 5-26-23
 */
public class P_Rogue extends Player{
	/**
         * Creates a Rogue with a name and type, and a pistol as a weapon.
         * @param n Name of the order
         * @param t Player type of the order
         */
        public P_Rogue(String n, String t){
		super(n, t, "Rogue.png");
		System.out.println("Order: Rouge");
   		Weapon pistol = new Weapon("Pistol","Pistol.png", "Rogue",7, 60);
		this.gainWeapon(pistol);		
                weapon = pistol;
	}
        /**
         * Runs the useSkill method of the Rouge.
         * The skill costs 40 energy and adds the effect "Ghost in the Fog" on the Rogue.
         * If energy is insufficient, it will default to use the stall method.
         */
	public void useSkill() {
		int cost = 40;
		try {
			this.useEnergy(cost);
			System.out.println(this.getName() + " vanished into a puff of smoke!");
			this.effects.add("Ghost in the Fog");
		}
		catch (NoEnergyException NEE) {
			this.stall();
		}
	}
        /**
         * Gives the Rogue a random chance to avoid taking damage when it has the effect "Ghost in the Fog".
         * @param dmg Original damage dealt on player
         */
	public void takeDamage(int dmg){
		if(effects.contains("Ghost in the Fog")) {
			if(rand.nextInt(2) == 1){
				System.out.println(this.getName() + " dodged the attack!");
			}
			else{
				super.takeDamage(dmg);
			}
		}
		else{
			super.takeDamage(dmg);
		}
	}
}
