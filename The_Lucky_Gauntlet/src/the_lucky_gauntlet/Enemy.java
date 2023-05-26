package the_lucky_gauntlet;

import java.util.ArrayList;
/**
 * Enemy is extended from Character.
 * @author Athena Kimwell
 * @version 5-26-23
 */
public class Enemy extends Character {
	/**
         * Creates an enemy with its name. Its attack value is then randomly generated.
         * @param newName Name of the enemy
         */
        public Enemy(String newName) {
		super(newName, "Enemy.png");
		attack = 1+rand.nextInt(5);
	}
        /**
         * Randomly selects the enemy's target between partner and player.
         */
	public void targetSelect() {
		int targetIndex = rand.nextInt(2);
		if(targetIndex == 0) {
			target = tlg.mc;
		}
		else {
			target = tlg.partner;
		}
	}
        /**
         * Runs the enemy's skill.
         */
	public void useSkill() {
		int damage = attack;
		System.out.printf("%s went on a rampage and dealt %d damage to all" +
				"enemies!\n", this.getName(), damage);
		tlg.mc.takeDamage(damage);
		tlg.partner.takeDamage(damage);
	}
	/**
         * Runs attack from Character. The enemy then gains 20 energy.
         */
	public void attack() {
		super.attack();
		this.gainEnergy(20);
	}
}