public class P_Bard extends Player{
	public P_Bard(String n){
		super(n);
		System.out.println("Order: Bard");
		attack = rand.nextInt(15)+5;
		Weapon kazoo = new Weapon("kazoo", 5, 50);
		weapon = kazoo;
	}

	public void useSkill() throws NoEnergyException {
		int cost = 100;
		if((energy-cost) < 0) {
			//skill issue
			throw new NoEnergyException(this.getName() + " doesn't have anough energy to perform this action!");
		}
		System.out.printf("%s played a little tune! %s and %s regained 150 energy!",
										 this.getName(), this.getName(), this.getPartnerName());
		this.useEnergy(cost);
		this.gainEnergy(150);
		this.getPartner().gainEnergy(150);
	}
}
