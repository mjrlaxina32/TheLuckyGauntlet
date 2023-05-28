package the_lucky_gauntlet.Orders;

import the_lucky_gauntlet.*;
import the_lucky_gauntlet.Exceptions.NoEnergyException;

/**
 * P_Cleric is extended from Player.
 * @author Athena Kimwell
 * @version 5-26-23
 */
public class P_Cleric extends Player{
        /**
         * Creates a Cleric with a name and type, and a staff as a weapon.
         * @param n Name of the order
         * @param t Player type of the order
         */	
	public P_Cleric(String n, String t){
		super(n, t, "Cleric.png");
		System.out.println("Order: Cleric");
		this.resetHealthValue(15);
		Weapon staff = new Weapon("Staff", "Staff1.png", "Cleric", 6, 50);
		this.gainWeapon(staff);
		weapon = staff;
	}
        /**
         * Runs the useSkill method of the Cleric.
         * The skill costs 75 energy and heals the player and partner by 40.
         * If energy is insufficient, it will default to use the stall method.
         */
	public void useSkill() {
		int cost = 75;
		try {
			this.useEnergy(cost);
			System.out.printf("%s cast a revival spell! %s and %s both healed by " +
												"20 health!", this.getName(), this.getName(), this.getPartnerName());
			this.heal(20);
			this.getPartner().heal(20);
		}
		catch (NoEnergyException NEE) {
			this.stall();
		}
	}
}
