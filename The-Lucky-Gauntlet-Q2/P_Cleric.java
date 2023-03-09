public class P_Cleric extends Player{
	public P_Cleric(String n){
		super(n);
		System.out.println("Order: Cleric");
		maxHealth = rand.nextInt(5)+45;
		Weapon staff = new Weapon("staff", 6, 50);
		weapon = staff;
	}

	public void useSkill() throws NoEnergyException {
		int cost = 75;
		if((energy-cost) < 0) {
			throw new NoEnergyException(this.getName() + " doesn't have anough energy to perform this action!");
		}
		System.out.printf("%s cast a revival spell! %s and %s both healed by " +
											"40 health!", this.getName(), this.getName(), this.getPartnerName());
		this.useEnergy(cost);
		this.heal(40);
		this.getPartner().heal(40);
	}
}
