package the_lucky_gauntlet;

import java.util.Random;
import javafx.scene.image.Image;
import the_lucky_gauntlet.Exceptions.*;

public class Weapon {
	private String name, order, filename;
	protected int atkBonus, durability, maxDurability;
	private Random rand = new Random();

        public Weapon(String n, int aB, int mD) {
		name = n;
		atkBonus = aB;
		maxDurability = mD;
		durability = maxDurability;
	} //!
         
	public Weapon(String n, String f, String o, int aB, int mD) {
		name = n;
                filename = f;
		order = o;
		atkBonus = aB;
		maxDurability = mD;
		durability = maxDurability;
	}
	
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
	
        public String getImgFileName() {
                return filename;
        } //to do: initialize file name
	
        public Image getImg() {
		Image classImg = new Image("/the_lucky_gauntlet/Sprites/" + this.getImgFileName());
		return classImg;
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
