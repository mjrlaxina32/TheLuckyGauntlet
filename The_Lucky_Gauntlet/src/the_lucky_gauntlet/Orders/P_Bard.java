package the_lucky_gauntlet.Orders;

import the_lucky_gauntlet.*;
import the_lucky_gauntlet.Exceptions.NoEnergyException;
/**
 * P_Bard is extended from Player.
 * @author Athena Kimwell
 */
public class P_Bard extends Player{
	/**
         * Creates a bard with a name and type, and a kazoo as a weapon.
         * @param n Name of the order
         * @param t Player type of the order
         */
        public P_Bard(String n, String t){
		super(n, t, "Bard.png");
		System.out.println("Order: Bard");
		this.resetAtkValue(5);
		Weapon kazoo = new Weapon("Kazoo", "Kazoo.png", "Bard", 5, 50);
		this.gainWeapon(kazoo);
                weapon = kazoo;
	}
        /**
         * Runs the useSkill method of the Bard.
         * The skill costs 100 energy and adds 150 energy to both player and partner.
         * If energy is insufficient, it will default to use the stall method.
         */
	public void useSkill() {
		int cost = 50;
		try {
			this.useEnergy(cost);
			System.out.printf("%s played a little tune! %s regained 150 energy!\n",
											 this.getName(), this.getPartnerName());
			this.gainEnergy(25);
			this.getPartner().gainEnergy(100);
		}
		catch (NoEnergyException NEE) {
			this.stall();
		}
	}
}
