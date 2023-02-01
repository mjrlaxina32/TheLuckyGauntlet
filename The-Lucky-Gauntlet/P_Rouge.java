import java.util.Random;

public class P_Rouge extends Player{
	public P_Rouge(String n){
		super(n);
		System.out.println("Order: Rouge");
   		Weapon pistol = new Weapon("pistol", 7, 60);
		weapon = pistol;
	}

	public void useSkill() throws NoEnergyException {
		int cost = 40;
		if((energy-cost) < 0) {
			throw new NoEnergyException(this.getName() + " doesn't have anough energy to perform this action!");
		}
		this.useEnergy(cost);
		System.out.println(this.getName() + " vanished into a puff of smoke!");
		this.effects.add("Ghost in the Fog");
	}
	public void takeDamage(int dmg){
		if(effects.contains("Ghost in the Fog")) {
			if((new Random()).nextInt(2) == 1){
				System.out.println(this.getName() + " dodged the attack!");
			}
			else{
				super.takeDamage(dmg);
			}
		}
		else{
			super.takeDamage(dmg);
		}
	}
}