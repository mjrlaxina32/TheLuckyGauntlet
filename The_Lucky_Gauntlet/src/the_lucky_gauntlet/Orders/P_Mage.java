package the_lucky_gauntlet.Orders;

import java.util.ArrayList;
import the_lucky_gauntlet.*;
import the_lucky_gauntlet.tlg;
import the_lucky_gauntlet.Exceptions.*;

public class P_Mage extends Player {
	public P_Mage(String n, String t) {
		super(n, t,"Mage.png");
		this.resetEnergyValue(40);
		System.out.println("Order: Mage");
		Weapon staff = new Weapon("Staff","Door.png","Mage", 5, 50);
		this.gainWeapon(staff);
                weapon = staff;
	}

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
