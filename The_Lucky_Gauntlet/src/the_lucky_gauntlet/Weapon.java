package the_lucky_gauntlet;

import java.util.Random;
import javafx.scene.image.Image;
import the_lucky_gauntlet.Exceptions.*;

/**
 *A weapon has a name, attack bonus, and max durability. It can also have a file name and belong to an order. 
 * The weapon's main purpose is to increase the attack value of the player.
 * @author Monique Jhoienyl
 * @version 5-23-23
 */
public class Weapon {
    private String name, order, filename;
    protected int atkBonus,durability, maxDurability;
    private Random rand = new Random();
    
    /**
     * @param n Name of weapon
     * @param aB Value of weapon Attack Bonus
     * @param mD Max Durability of weapon
     */
    public Weapon(String n, int aB, int mD) {
		name = n;
		atkBonus = aB;
		maxDurability = mD;
		durability = maxDurability;
	} //!
         
    /**
     * @param n Name of weapon
     * @param f Filename of weapon
     * @param o Order of weapon
     * @param aB Value of weapon Attack Bonus
     * @param mD Max Durability of weapon
     */
    public Weapon(String n, String f, String o, int aB, int mD) {
		name = n;
                filename = f;
		order = o;
		atkBonus = aB;
		maxDurability = mD;
		durability = maxDurability;
	}
	
    /**
     * @param n Name of weapon
     * @param o Order of weapon
     * @param aB Value of weapon Attack Bonus
     * @param mD Max Durability of weapon
     */
    public Weapon(String n, String o, int aB, int mD) {
		name = n;
		order = o;
		atkBonus = aB;
		maxDurability = mD;
		durability = maxDurability;
	}
        
    /** 
     * @return Returns weapon name
     */
    public String getName (){
		return name;
	}

    /** 
     * @return Returns weapon attack bonus
     */
    public int getAtkBonus (){
		return atkBonus;
	}

    /** 
     * @return Returns weapon current durability
     */
    public int getDurability (){
		return durability;
	}

    /** 
     * @return Returns weapon max durability
     */
    public int getMaxDurability (){
		return maxDurability;
	}

    /** 
     * @return Returns order of weapon
     */
    public String getOrder (){
		return order;
	}
	
    /** 
     * @return Returns weapon filename
     */
    public String getImgFileName() {
                return filename;
        } //to do: initialize file name
	
    /**
     * @return Returns weapon image using weapon filename 
     */
    public Image getImg() {
		Image classImg = new Image("/the_lucky_gauntlet/Sprites/" + this.getImgFileName());
		return classImg;
	}
        
    /**
     * Adds a random number between 0-5 to the attack bonus of the weapon
     */
    public void enhance() {
		atkBonus += rand.nextInt(5);
	}

    /**
     * Increases current durability of the weapon by 10
     * @throws BeyondRangeException if the durability is equal to the max durability 
     */
    public void repair() throws BeyondRangeException{
		if(durability == maxDurability){
			throw new BeyondRangeException("The weapon has reached its maximum durability.");
		}
		else{
		durability += 10;
		}
	}

    /**
     *Decreases current durability of the weapon by 5
     */
    public void weaken() {
		durability -= 5;
		if(durability <= 0)
			durability = 0;
	}
}
