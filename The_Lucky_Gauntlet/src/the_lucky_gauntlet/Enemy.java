package the_lucky_gauntlet;

import java.util.ArrayList;

public class Enemy extends Character {
	public Enemy(String newName) {
		super(newName, "Enemy.png");
		attack = rand.nextInt(5);
	}

	public void targetSelect(ArrayList<Player> targets) {
		int targetIndex = rand.nextInt(targets.size());
		target = targets.get(targetIndex);
	}

	public void useSkill() {
		int damage = attack;
		System.out.printf("%s went on a rampage and dealt %d damage to all" +
				"enemies!\n", this.getName(), damage);
		tlg.mc.takeDamage(damage);
		tlg.partner.takeDamage(damage);
	}
}
