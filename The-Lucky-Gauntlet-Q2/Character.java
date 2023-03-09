import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Character {
	private String name;
	protected int health, maxHealth, attack, energy, maxEnergy;
	protected Character target;
	protected Weapon weapon;
	protected Room currentRoom;
	protected ArrayList<String> effects = new ArrayList<String>();

	Random rand = new Random();
	Scanner sc = new Scanner(System.in);

	public Character(String n) {
		name = n;
		maxHealth = rand.nextInt(25) + 25;
		health = maxHealth;
		attack = rand.nextInt(10) + 10;
		maxEnergy = rand.nextInt(100) + 100;
		energy = maxEnergy;

		weapon = new Weapon("Fists", 0, 1000);

		// this.showStats();
	}

	// Getter Methods
	public String getName() {
		return this.name;
	}

	public int getHealth() {
		return this.health;
	}

	public int getEnergy() {
		return this.energy;
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

	public void useEnergy(int amount) {
		energy -= amount;
		if (energy < 0)
			energy = 0;
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

	public void attack() throws NoEnergyException {
		int cost = 20;
		if ((energy - cost) < 0) {
			throw new NoEnergyException(name);
		}
		int totalDamage = attack + weapon.getAtkBonus();
		System.out.printf("%s used %d energy to attack %s and dealt %d damage!\n", name, cost, target.getName(),
				totalDamage);
		this.useEnergy(20);
		target.takeDamage(totalDamage);
	}

	public void stall() {
		System.out.printf("%s rested and regained 50 energy!\n", this.getName());
		this.gainEnergy(50);
	}

	public abstract void useSkill() throws NoEnergyException;

	// Current Room Handling
	public void updateRoom(Room r) {
		currentRoom = r;
	}
}
