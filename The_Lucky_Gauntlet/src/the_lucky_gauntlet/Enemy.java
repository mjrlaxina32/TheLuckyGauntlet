package the_lucky_gauntlet;

import java.util.ArrayList;

public class Enemy extends Character {
	public Enemy(String newName) {
		super(newName, "Enemy.png");
		attack = 1+rand.nextInt(5);
	}

	public void targetSelect() {
		int targetIndex = rand.nextInt(2);
		if(targetIndex == 0) {
			target = tlg.mc;
		}
		else {
			target = tlg.partner;
		}
	}

	public void useSkill() {
		int damage = attack;
		System.out.printf("%s went on a rampage and dealt %d damage to all" +
				"enemies!\n", this.getName(), damage);
		tlg.mc.takeDamage(damage);
		tlg.partner.takeDamage(damage);
	}
	
	public void attack() {
		super.attack();
		this.gainEnergy(20);
	}
}
