package the_lucky_gauntlet;

import the_lucky_gauntlet.Exceptions.NoEnergyException;

import java.util.ArrayList;

public class Enemy extends Character {
	public Enemy(String newName) {
		super(newName);
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
		ArrayList<Player> players = this.getCurrentRoom().getAllPlayers();
		for (Player p : players) {
			this.targetSelect(p);
			target.takeDamage(damage);
		}
	}
}