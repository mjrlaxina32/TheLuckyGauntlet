package the_lucky_gauntlet.Orders;

import the_lucky_gauntlet.*;
import the_lucky_gauntlet.Exceptions.NoEnergyException;

public class P_Cleric extends Player{
	public P_Cleric(String n, String t){
		super(n, t, "Cleric.png");
		System.out.println("Order: Cleric");
		this.resetHealthValue(15);
		Weapon staff = new Weapon("Staff", "Door.png", "Cleric", 6, 50);
		this.gainWeapon(staff);
                weapon = staff;
	}

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
