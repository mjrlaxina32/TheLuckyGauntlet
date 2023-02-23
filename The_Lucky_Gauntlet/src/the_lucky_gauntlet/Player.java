package the_lucky_gauntlet;

import the_lucky_gauntlet.Exceptions.NoEnergyException;
import the_lucky_gauntlet.Exceptions.InaccessibleRoomException;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Player extends Character {
	private Player partner;

	Scanner sc = new Scanner(System.in);
	Random rand = new Random();

	public Player(String newName) {
		super(newName);
	}

	// Partner Methods
	public void setPartner(Player newPartner) {
		this.partner = newPartner;
	}

	// Getter Methods
	public Player getPartner() {
		return partner;
	}

	public String getPartnerName() {
		return partner.getName();
	}

	// Peaceful Room Actions
	public void train() throws NoEnergyException {
		try {
			this.useEnergy(25);
			this.attack += 5;
			System.out.printf("%s used 25 energy to train. %s's attack increased by 10 and is now %d\n", this.getName(), this.getName(), this.attack);
		}
		catch (NoEnergyException NEE) {
			this.rest();
		}
	}

	public void rest() {
		this.gainEnergy(150);
		partner.gainEnergy(150);
		System.out.printf("%s and %s took a long rest."
				+ " Their energy went up by 150 and they now have energies of %d and %d",
				this.getName(), partner.getName(), energy, partner.energy);
	}

	// Battle Room Actions
	public void useSkill() {
		System.out.println("Generic Skill Text 1");
	} // Overrided by the different classes

	public void stall() {
		System.out.printf("%s and %s rested and both regained 50 energy!\n", this.getName(),
				partner.getName());
		this.gainEnergy(50);
		partner.gainEnergy(50);
	}

	public void targetSelect(ArrayList<Enemy> targets) {
		System.out.println("Enemies: ");
		for (int i = 0; i < targets.size(); i++) {
			System.out.printf(" %d - %s (HP: %d)\n", i + 1, targets.get(i).getName(),
					targets.get(i).getHealth());
		}
		System.out.print("Who is your target? ");
		int targetIndex = Integer.parseInt(sc.nextLine()) - 1;
		try {
			target = targets.get(targetIndex);
		} catch (IndexOutOfBoundsException e) {
			System.out.printf("%d is not a valid input. Note: Inputs are the ones " +
					"on the left of the hyphen. This means that enemy 2 " +
					"can have an input of 1!\n", targetIndex);
		}
	}

	// Other Actions
	public void action() {
		currentRoom.performAction(this);
	}

	public void changeRoom(Room newRoom) {
		System.out.println("You entered the " + newRoom.getName());

		if (currentRoom != null) {
			currentRoom.leaveRoom(this);
			currentRoom.leaveRoom(partner);
		}

		this.updateRoom(newRoom);
		this.getPartner().updateRoom(newRoom);

		try {
			currentRoom.enterRoom(this);
			currentRoom.enterRoom(partner);
		} catch (InaccessibleRoomException e) {
			System.out.println(e.getMessage());
		}
	}

	// Maybe should be limited to treasure rooms
	public void newWeapon() {
		if (rand.nextInt(5) > 3) {
			Weapon findings = new Weapon("Hello", rand.nextInt(7) + 3, rand.nextInt(30) + 20);
			System.out.print("Would you like to keep the weapon \"" + findings.getName() + "\"? (y/n)");
			String choice = sc.nextLine();
			if (choice.equals("y"))
				this.weapon = findings;
		}
	}
}