package the_lucky_gauntlet;

import the_lucky_gauntlet.Exceptions.*;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.image.Image;

public abstract class Character {
	private String name, filename;
	protected int health, maxHealth, attack, energy, maxEnergy;
	protected Character target;
	protected Weapon weapon;
	protected ArrayList<String> effects = new ArrayList<String>();
	protected ArrayList<Integer> effectDurations = new ArrayList<Integer>();
	
	public Random rand = new Random();
	public Scanner sc = new Scanner(System.in);

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
	public String getName() {
		return this.name;
	}
	public int getAttack() {
		return this.attack;
	}
	public int getHealth() {
		return this.health;
	}
	public int getMaxHealth() {
		return this.maxHealth;
	}
	public int getEnergy() {
		return this.energy;
	}
	public int getMaxEnergy() {
		return this.maxEnergy;
	}
	public Character getTarget() {
		return target;
	}
	public String getTargetName() {
		return target.name;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public String getImgFileName() {
		return filename;
	}
	public Image getImg() {
		Image classImg = new Image("/the_lucky_gauntlet/Sprites/" + this.getImgFileName());
		return classImg;
	}
	public boolean hasEffect(String searchEffect){
		for(String effect : effects){
			if(effect.equals(searchEffect)) {
				return true;
			}
		}
		return false;
	}

	// Effect Manipulation
	public void addEffect(String effect,int duration) {
		this.effects.add(effect);
		this.effectDurations.add(duration);
	}
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
	public void resetAtkValue(int randFactor) {
		int baseFactor = 20 - randFactor;
		attack = rand.nextInt(randFactor) + baseFactor;
	}
	public void resetHealthValue(int randFactor) {
		int baseFactor = 50 - randFactor;
		maxHealth = rand.nextInt(randFactor) + baseFactor;
		health = maxHealth;
	}
	public void resetEnergyValue(int randFactor) {
		int baseFactor = 200 - randFactor;
		maxEnergy = rand.nextInt(randFactor) + baseFactor;
		energy = maxEnergy;
	}
	
	
	// Utility methods
	public void takeDamage(int dmg) {
		health -= dmg;
		if (health < 0) {
			health = 0;
			System.out.println(name + " has died!");
		}
	}
	public void heal(int amount) {
		health += amount;
		if (health > maxHealth)
			health = maxHealth;
	}
	public void useEnergy(int amount) throws NoEnergyException{
		if (amount > energy) {
			throw new NoEnergyException(this);
		}
		energy -= amount;
	}
	public void gainEnergy(int amount) {
		energy += amount;
		if (energy > maxEnergy)
			energy = maxEnergy;
	}

	public void showStats() {
		System.out.printf(" Name: %s\n Health: %d\n Attack: %d\n Energy: %d\n", name, health, attack, energy);
	}

	public boolean isDead() {
		if (health <= 0) {
			return true;
		} else {
			return false;
		}
	}

	// Skills
	public void targetSelect(Character newTarget) {
		target = newTarget;
	}

	public void attack() {
		try {
			this.useEnergy(20);
			int totalDamage = attack + weapon.getAtkBonus();
			System.out.printf("%s used %d energy to attack %s and dealt %d damage!\n", name, 20, target.getName(),
					totalDamage);
			target.takeDamage(totalDamage);
		}
		catch(NoEnergyException NEE) {
			this.stall();
		}
	}

	public void stall() {
		System.out.printf("%s rested and regained 50 energy!\n", this.getName());
		this.gainEnergy(50);
	}

	public abstract void useSkill();
}
