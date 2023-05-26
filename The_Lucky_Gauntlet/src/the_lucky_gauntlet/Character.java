package the_lucky_gauntlet;

import the_lucky_gauntlet.Exceptions.*;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.image.Image;

/**
 * A Character class contains a name, filename, health, maxHealth, attack, energy, maxEnergy, target, weapon, effects, and effectDurations.
 * It's an abstract class that's used as the template for the Player and Enemy classes.
 * @author Athena Kimwell
 * @version 5-26-23
 */
public abstract class Character {
	private String name, filename;

    /**
     * Character's health value
     */
    protected int health,

    /**
     * Character's maximum health value
     */
    maxHealth,

    /**
     * Character's attack value
     */
    attack,

    /**
     * Character's energy value
     */
    energy,

    /**
     * Character's maximum energy value
     */
    maxEnergy;

    /**
     * Character's target
     */
    protected Character target;

    /**
     * Character's weapon
     */
    protected Weapon weapon;

    /**
     * List of effects that can affect Character
     */
    protected ArrayList<String> effects = new ArrayList<String>();

    /**
     * List of durations of the effects that can affect Character
     */
    protected ArrayList<Integer> effectDurations = new ArrayList<Integer>();
	
    /**
     * Random value
     */
    public Random rand = new Random();

    /**
     * Scanner for user inputs
     */
    public Scanner sc = new Scanner(System.in);

        /**
         * Creates a character with randomized health, attack, energy, and a default weapon.
         * @param n name
         * @param f filename
         */
	public Character(String n, String f) {
		name = n;
		filename = f;
		maxHealth = rand.nextInt(25) + 25;
		health = maxHealth;
		attack = rand.nextInt(8) + 12;
		maxEnergy = rand.nextInt(100) + 100;
		energy = maxEnergy;

		weapon = new Weapon("Fists", "Fists.png", "Generic",0, 1000);

		// this.showStats();
	}

	// Getter Methods
        /**
         * @return Returns character's name
         */
	public String getName() {
		return this.name;
	}
        /**
         * @return Returns character's attack
         */
	public int getAttack() {
		return this.attack;
	}
        /**
         * @return Returns character's health
         */
	public int getHealth() {
		return this.health;
	}
        /**
         * @return Returns character's maximum health
         */
	public int getMaxHealth() {
		return this.maxHealth;
	}
        /**
         * @return Returns character's energy
         */
	public int getEnergy() {
		return this.energy;
	}
        /**
         * @return Returns character's maximum energy
         */
	public int getMaxEnergy() {
		return this.maxEnergy;
	}
        /**
         * @return Returns character's target
         */
	public Character getTarget() {
		return target;
	}
        /**
         * @return Returns the name of character's target
         */
	public String getTargetName() {
		return target.name;
	}
        /**
         * @return Returns the character's weapon
         */
	public Weapon getWeapon() {
		return weapon;
	}
        /**
         * @return Returns the character's image filename
         */
	public String getImgFileName() {
		return filename;
	}
        /**
         * @return Returns the image of the character's class
         */
	public Image getImg() {
		Image classImg = new Image("/the_lucky_gauntlet/Sprites/" + this.getImgFileName());
		return classImg;
	}
        /**
         * Determines whether or not the character has an effect on it.
         * @param searchEffect Effect currently on the character
         * @return Returns true/false depending on the presence of an effect
         */
	public boolean hasEffect(String searchEffect){
		for(String effect : effects){
			if(effect.equals(searchEffect)) {
				return true;
			}
		}
		return false;
	}

	// Effect Manipulation
        /**
         * Adds an effect to the ArrayList of effects
         * @param effect Name of the effect
         * @param duration Duration of the effect
         */
	public void addEffect(String effect,int duration) {
		this.effects.add(effect);
		this.effectDurations.add(duration);
	}
        /**
         * Decreases effect duration on the character by 1
         * @param effect Name of the current effect
         * @return Returns removedEffect and a blank String
         */
	public String decreaseEffectDuration(String effect) {
		int effectIndex = effects.indexOf(effect);
		
		if(effectIndex != -1) {
			effectDurations.set(effectIndex, effectDurations.get(effectIndex)-1);
			System.out.println(effect + ": " + effectDurations.get(effectIndex));
			if(effectDurations.get(effectIndex) == 0) {
				String removedEffect = effects.get(effectIndex);
				effects.remove(effectIndex);
				effectDurations.remove(effectIndex);
				return removedEffect;
			}
			return "";
		}
		return "";
	}
        /**
         * Decreases durations of all effects on the character by 1
         * @return Returns endedEffects
         */
	public ArrayList<String> decreaseAllEffectDurations() {
		System.out.println(effects);
		ArrayList<String> endedEffects = new ArrayList<>();
		
		for(int i=0; i<effects.size(); i++) {
			System.out.println(effects.get(i));
			String clearedEffect = this.decreaseEffectDuration(effects.get(i));
			System.out.println(clearedEffect);
			if (!clearedEffect.equals("")) {
				endedEffects.add(clearedEffect);
			}
		}
		
		return endedEffects;
	}
	
	// Base Value Re-randomization
        /**
         * Resets character's attack value
         * @param randFactor Random factor determining amount of character's attack
         */
	public void resetAtkValue(int randFactor) {
		int baseFactor = 20 - randFactor;
		attack = rand.nextInt(randFactor) + baseFactor;
	}
        /**
         * Resets character's health value
         * @param randFactor Random factor determining amount of character's maximum health
         */
	public void resetHealthValue(int randFactor) {
		int baseFactor = 50 - randFactor;
		maxHealth = rand.nextInt(randFactor) + baseFactor;
		health = maxHealth;
	}
        /**
         * Resets character's energy value
         * @param randFactor Random factor determining amount of character's maximum energy
         */
	public void resetEnergyValue(int randFactor) {
		int baseFactor = 200 - randFactor;
		maxEnergy = rand.nextInt(randFactor) + baseFactor;
		energy = maxEnergy;
	}
	
	
	// Utility methods
        /**
         * Decreases the character's health
         * @param dmg Amount by which character's health decreases
         */
	public void takeDamage(int dmg) {
		health -= dmg;
		if (health < 0) {
			health = 0;
			System.out.println(name + " has died!");
		}
	}
        /**
         * Increases the character's health
         * @param amount Amount by which character's health increases
         */
	public void heal(int amount) {
		health += amount;
		if (health > maxHealth)
			health = maxHealth;
	}
        /**
         * Decreases the character's energy
         * @param amount Amount by which character's energy decreases
         * @throws NoEnergyException 
         */
	public void useEnergy(int amount) throws NoEnergyException{
		if (amount > energy) {
			throw new NoEnergyException(this);
		}
		energy -= amount;
	}
        /**
         * Increases the character's energy
         * @param amount Amount by which character's energy increases
         */
	public void gainEnergy(int amount) {
		energy += amount;
		if (energy > maxEnergy)
			energy = maxEnergy;
	}
        /**
         * Shows the name, health, attack and energy of the character
         */
	public void showStats() {
		System.out.printf(" Name: %s\n Health: %d\n Attack: %d\n Energy: %d\n", name, health, attack, energy);
	}
        /**
         * Determines whether or not the character is dead
         * @return Returns true/false depending on whether the character is dead
         */
	public boolean isDead() {
		if (health <= 0) {
			return true;
		} else {
			return false;
		}
	}

	// Skills
        /**
         * Changes the character's current target
         * @param newTarget The character targeted by the current character
         */
	public void targetSelect(Character newTarget) {
		target = newTarget;
	}
        /**
         * Uses current character to attack the target character
         */
	public void attack() {
		try {
			this.useEnergy(20);
			int totalDamage = attack + weapon.getAtkBonus();
			System.out.printf("%s used %d energy to attack %s and dealt %d damage!\n", name, 20, target.getName(),
					totalDamage);
			target.takeDamage(totalDamage);
                        this.getWeapon().weaken();
		}
		catch(NoEnergyException NEE) {
			this.stall();
		}
	}
        /**
         * Makes the character stall in order to gain energy
         */
	public void stall() {
		System.out.printf("%s rested and regained 50 energy!\n", this.getName());
		this.gainEnergy(50);
	}
        /**
         * Makes the character use the skill specific for their class
         */
	public abstract void useSkill();
}
