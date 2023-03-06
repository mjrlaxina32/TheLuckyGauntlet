package the_lucky_gauntlet.Orders;

import the_lucky_gauntlet.*;
import the_lucky_gauntlet.Exceptions.NoEnergyException;

public class P_Rouge extends Player{
	public P_Rouge(String n, String t){
		super(n, t);
		System.out.println("Order: Rouge");
   		Weapon pistol = new Weapon("pistol", 7, 60);
		weapon = pistol;
	}

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