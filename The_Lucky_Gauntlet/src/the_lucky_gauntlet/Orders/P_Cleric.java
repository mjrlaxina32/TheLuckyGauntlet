package the_lucky_gauntlet.Orders;

import the_lucky_gauntlet.*;
import the_lucky_gauntlet.Exceptions.NoEnergyException;

public class P_Cleric extends Player{
	public P_Cleric(String n, String t){
		super(n, t);
		System.out.println("Order: Cleric");
		this.resetHealthValue(5);
		Weapon staff = new Weapon("staff", 6, 50);
		weapon = staff;
	}

	public void useSkill() {
		int cost = 75;
		try {
			this.useEnergy(cost);
			System.out.printf("%s cast a revival spell! %s and %s both healed by " +
												"40 health!", this.getName(), this.getName(), this.getPartnerName());
			this.heal(40);
			this.getPartner().heal(40);
		}
		catch (NoEnergyException NEE) {
			this.stall();
		}
	}
}