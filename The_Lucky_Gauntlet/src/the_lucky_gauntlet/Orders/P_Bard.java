package the_lucky_gauntlet.Orders;

import the_lucky_gauntlet.*;
import the_lucky_gauntlet.Exceptions.NoEnergyException;

public class P_Bard extends Player{
	public P_Bard(String n, String t){
		super(n, t);
		System.out.println("Order: Bard");
		this.resetAtkValue(5);
		Weapon kazoo = new Weapon("kazoo", 5, 50);
		weapon = kazoo;
	}

	public void useSkill() {
		int cost = 100;
		try {
			this.useEnergy(cost);
			System.out.printf("%s played a little tune! %s and %s regained 150 energy!",
											 this.getName(), this.getName(), this.getPartnerName());
			this.useEnergy(cost);
			this.gainEnergy(150);
			this.getPartner().gainEnergy(150);
		}
		catch (NoEnergyException NEE) {
			this.stall();
		}
	}
}
