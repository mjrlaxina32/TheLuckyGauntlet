package the_lucky_gauntlet.Orders;

import java.util.ArrayList;
import the_lucky_gauntlet.*;
import the_lucky_gauntlet.tlg;
import the_lucky_gauntlet.Exceptions.*;
/**
 * P_Mage is extended from Player.
 * @author Athena Kimwell
 * @version 5-26-23
 */
public class P_Mage extends Player {
	/**
         * Creates a Mage with a name and type, and a staff as a weapon.
         * @param n Name of the order
         * @param t Player type of the order
         */
        public P_Mage(String n, String t) {
		super(n, t,"Mage.png");
		this.resetEnergyValue(40);
		System.out.println("Order: Mage");
		Weapon staff = new Weapon("Staff","Staff2.png","Mage", 5, 50);
		this.gainWeapon(staff);
                weapon = staff;
	}
        /**
         * Runs the useSkill method of the Mage.
         * The skill costs 60 energy and deals damage to all enemies in the room.
         * If energy is insufficient, it will default to use the stall method.
         */
	public void useSkill() {
		int cost = 60;
		try {
			this.useEnergy(cost);
			System.out.printf("%s cast a powerful explosion dealing %d damage to all" +
												"enemies!\n", this.getName(), this.getAttack());
			ArrayList<Enemy> enemies = tlg.currentRoom.getAllEnemies();
			for(Enemy e : enemies) {
				this.targetSelect(e);
				e.takeDamage(this.getAttack());
			}
		}
		catch (NoEnergyException NEE) {
			this.stall();
		}
	}
	
}
