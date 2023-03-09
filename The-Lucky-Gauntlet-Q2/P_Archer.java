import java.util.Random;

public class P_Archer extends Player {
	public P_Archer(String n) {
		super(n);
		System.out.println("Order: Archer");
		Weapon bow = new Weapon("bow", 3, 50);
	}

	public void useSkill() throws NoEnergyException {
		int cost = 40;
		if ((energy - cost) < 0) {
			throw new NoEnergyException(this.getName() + " doesn't have anough energy to perform this action!");
		}
		System.out.println(this.getName() + " concentrated on his foes!\n");
		this.effects.add("Hawkeye");
	}

	public void attack() throws NoEnergyException {
		int cost = 20;
		if ((energy - cost) < 0) {
			throw new NoEnergyException(this.getName() + " doesn't have anough energy to perform this action!");
		}
		if (effects.contains("Hawkeye")) {
			if ((new Random()).nextInt(2) == 1) {
				System.out.printf("%s fired a perfect arrow and landed a critical " +
						"hit dealing %d damage!", this.getName(),
						(attack + weapon.getAtkBonus()) * 2);
				this.useEnergy(cost);
				this.getTarget().takeDamage((attack + weapon.getAtkBonus()) * 2);
			} else {
				super.attack();
			}
		} else {
			super.attack();
		}
	}
}
