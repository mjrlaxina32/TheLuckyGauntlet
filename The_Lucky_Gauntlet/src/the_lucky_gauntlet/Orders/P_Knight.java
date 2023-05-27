package the_lucky_gauntlet.Orders;

import the_lucky_gauntlet.*;
import the_lucky_gauntlet.Exceptions.NoEnergyException;
/**
 * P_Knight is extended from Player.
 * @author Athena Kimwell
 * @version 5-26-23
 */
public class P_Knight extends Player{
	/**
         * Creates a Knight with a name and type, and a sword as a weapon.
         * @param n Name of the order
         * @param t Player type of the order
         */
        public P_Knight(String n, String t){
		super(n,t,"Knight.png");
		System.out.println("Order: Knight");
		this.resetHealthValue(10);
		Weapon sword = new Weapon("Sword","Sword.png", "Knight", 6, 50);
		this.gainWeapon(sword);
                weapon = sword;
	}
        /**
         * Runs the useSkill method of the Knight.
         * The skill costs 40 energy and adds the effect "Unyielding Will" on the Knight.
         * If energy is insufficient, it will default to use the stall method.
         */
	public void useSkill() {
		int cost = 40;
		try {
			this.useEnergy(cost);
			System.out.println(this.getName() + " gathered his resolve!");
			this.addEffect("Unyielding Will", 7);
		}
		catch (NoEnergyException NEE) {
			this.stall();
		}
	}
        /**
         * Halves the damage taken by the Knight if it has the effect "Unyielding Will".
         * @param dmg Original damage dealt on player
         */
	public void takeDamage(int dmg){
		if(this.effects.contains("Unyielding Will")){
			dmg /= 2;
			System.out.printf("%s tanked the hit and only recieved %d damage!\n",
												this.getName(), dmg);
		}
		
		super.takeDamage(dmg);
  }
}
