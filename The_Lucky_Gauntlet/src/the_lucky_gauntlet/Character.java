package the_lucky_gauntlet;

import the_lucky_gauntlet.Exceptions.NoEnergyException;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.image.Image;

public abstract class Character {
	private String name, filename;
	protected int health, maxHealth, attack, energy, maxEnergy;
	protected Character target;
	protected Weapon weapon;
	protected Room currentRoom;
	protected ArrayList<String> effects = new ArrayList<String>();

	public Random rand = new Random();
	public Scanner sc = new Scanner(System.in);

	public Character(String n, String f) {
		name = n;
		maxHealth = rand.nextInt(25) + 25;
		health = maxHealth;
		attack = rand.nextInt(10) + 10;
		maxEnergy = rand.nextInt(100) + 100;
		energy = maxEnergy;

		weapon = new Weapon("Fists", "Generic",0, 1000);

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
	public Room getCurrentRoom() {
		return this.currentRoom;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public String getImgFileName() {
		return filename;
	}
	public Image getImg() {
		Image classImg = new Image(getClass().getResourceAsStream("@../../Sprites/" + this.getImgFileName()));
		return classImg;
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

	// Current Room Handling
	public void updateRoom(Room r) {
		currentRoom = r;
	}
}
