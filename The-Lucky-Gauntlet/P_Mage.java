import java.util.ArrayList;

public class P_Mage extends Player {
	public P_Mage(String n) {
		super(n);
		maxEnergy = rand.nextInt(40) + 160;
		System.out.println("Order: Mage");
		Weapon staff = new Weapon("staff", 5, 50);
		weapon = staff;
	}

	public void useSkill() throws NoEnergyException {
		int damage = attack;
		int cost = 60;
		if((energy-cost) < 0) {
			throw new NoEnergyException(this.getName() + " doesn't have anough energy to perform this action!");
		}
		System.out.printf("%s cast a powerful explosion dealing %d damage to all" +
											"enemies!\n", this.getName(), damage);
		this.useEnergy(cost);
		ArrayList<Enemy> enemies = this.getCurrentRoom().getAllEnemies();
		for(Enemy e : enemies) {
			this.targetSelect(e);
			e.takeDamage(damage);
		}
	}
	
}