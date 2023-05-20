package the_lucky_gauntlet.Orders;

import the_lucky_gauntlet.*;
import the_lucky_gauntlet.Exceptions.NoEnergyException;

public class P_Knight extends Player{
	public P_Knight(String n, String t){
		super(n,t,"Knight.png");
		System.out.println("Order: Knight");
		this.resetHealthValue(20);
		this.resetHealthValue(8);
		Weapon sword = new Weapon("Sword","Door.png", "Knight", 6, 50);
		this.gainWeapon(sword);
                weapon = sword;
	}
	public void useSkill() {
		int cost = 40;
		try {
			this.useEnergy(cost);
			System.out.println(this.getName() + " gathered his resolve!");
			this.effects.add("Unyielding Will");
		}
		catch (NoEnergyException NEE) {
			this.stall();
		}
	}
	public void takeDamage(int dmg){
		if(this.effects.contains("Unyielding Will")){
			dmg /= 2;
			System.out.printf("%s tanked the hit and only recieved %d damage!",
												this.getName(), dmg);
		}
		
		super.takeDamage(dmg);
  }
}
