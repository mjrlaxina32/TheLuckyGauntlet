import java.util.Random;

public class Weapon {
	private String name;
	protected int atkBonus, durability, maxDurability;
	private Random rand = new Random();

	public String getName (){
		return name;
	}
	public int getAtkBonus (){
		return atkBonus;
	}
	public int getDurability (){
		return durability;
	}
	public int getMaxDurability (){
		return maxDurability;
	}
	
	public Weapon(String n, int aB, int mD) {
		name = n;
		atkBonus = aB;
		maxDurability = mD;
		durability = maxDurability;
	}
	
	public void enhance() {
		atkBonus += rand.nextInt(5);
	}
	public void repair() throws BeyondRangeException{
		if(durability == maxDurability){
			throw new BeyondRangeException("The weapon has reached its maximum durability.");
		}
		else{
		durability += 10;
		}
	}
	public void weaken() {
		if(durability != 0){
			durability -= 5;
		}
	}
	public void destroy() {
		//How do we destroy a weapon?
		//not too sure but
		//durability = 0;
	}
}
