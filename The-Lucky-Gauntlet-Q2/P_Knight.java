public class P_Knight extends Player{
	public P_Knight(String n){
		super(n);
		System.out.println("Order: Knight");
		maxHealth = rand.nextInt(20)+30;
		attack = rand.nextInt(5)+10;
		Weapon sword = new Weapon("sword", 6, 50);
		weapon = sword;
	}

	public void useSkill() throws NoEnergyException {
		int cost = 40;
		if((energy-cost) < 0) {
			throw new NoEnergyException(this.getName() + " doesn't have anough energy to perform this action!");
		}
		System.out.println(this.getName() + " gathered his resolve!");
		this.useEnergy(cost);
		this.effects.add("Unyielding Will");
	}
	public void takeDamage(int dmg){
		if(this.effects.contains("Unyielding Will")){
			dmg /= 2;
			System.out.printf("%s tanked the hit and only recieved %d damage!",
												this.getName(), dmg);
		}
		
		super.takeDamage(dmg);
  }
}
