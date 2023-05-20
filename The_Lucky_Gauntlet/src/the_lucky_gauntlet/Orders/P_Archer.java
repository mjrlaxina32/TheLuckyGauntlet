package the_lucky_gauntlet.Orders;

import the_lucky_gauntlet.*;
import the_lucky_gauntlet.Exceptions.NoEnergyException;

public class P_Archer extends Player {
	public P_Archer(String n, String t) {
		super(n, t, "Archer.png");
		System.out.println("Order: Archer");
		Weapon bow = new Weapon("Bow","Door.png", "Archer", 3, 50); //placeholdoor
		this.gainWeapon(bow);
		weapon = bow;
	}

	public void useSkill() {
		int cost = 40;
		
		try {
			this.useEnergy(cost);
			System.out.println(this.getName() + " concentrated on his foes!\n");
			this.effects.add("Hawkeye");	
		}
		catch(NoEnergyException NEE) {
			this.stall();
		}
	}

	public void attack() {
		int cost = 20;
		try {
			this.useEnergy(cost);
			if (effects.contains("Hawkeye")) {
				if (rand.nextBoolean()) {
					System.out.printf("%s fired a perfect shot and landed a critical " +
							"hit dealing %d damage!", this.getName(),
							(attack + weapon.getAtkBonus()) * 2);
					this.useEnergy(cost);
					this.getTarget().takeDamage((attack + weapon.getAtkBonus()) * 2);
					
					if (target.isDead()){
						for(Enemy e : tlg.currentRoom.getAllEnemies()) {
							if(!e.isDead()) {
								target = e;
								tlg.partner.targetSelect(target);
								System.out.println("Target has changed");
								break;
							}
						}
					}
				}
				else {
					super.attack();
				}
			}
			else {
				super.attack();
			}
		}
		catch(NoEnergyException NEE) {
			this.stall();
		}
	}
}
