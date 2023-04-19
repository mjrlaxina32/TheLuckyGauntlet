package the_lucky_gauntlet;

import the_lucky_gauntlet.Exceptions.BeyondRangeException;

import java.util.Random;

public class Weapon {
	private String name, order;
	protected int atkBonus, durability, maxDurability;
	private Random rand = new Random();

	public Weapon(String n, String o, int aB, int mD) {
		name = n;
		order = o;
		atkBonus = aB;
		maxDurability = mD;
		durability = maxDurability;
	}
	
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
	public String getOrder (){
		return order;
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
		durability -= 5;
		if(durability <= 0)
			durability = 0;
	}
	public void destroy() {
		//How do we destroy a weapon?
		//not too sure but
		//durability = 0;
	}
}
