package the_lucky_gauntlet;

import the_lucky_gauntlet.Exceptions.*;

import java.util.ArrayList;

public class Player extends Character {
	private Player partner;
	protected String playerType;
	private ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
	private static ArrayList<Player> players = new ArrayList<Player>();

	public Player(String newName, String newPlayerType, String imageFilename) {
		super(newName, imageFilename);
		playerType = newPlayerType;
		players.add(this);
                Weapon fists = new Weapon("Fists", "Fists.png", "Generic",0, 1000);
                weaponList.add(fists);
        }//!

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
        public ArrayList getWeaponList(){
                return weaponList;
        }
        
        public Weapon getWeapon(){
                return weapon;
        }
        
        
        
        /*public ArrayList getWeaponList(Player e){
                
                return weaponList;
        }*/
        
	// Peaceful Room Actions
	public void train() throws NoEnergyException {
		this.useEnergy(25);
		this.attack += 2;
	}

	public void rest() {
		this.gainEnergy(150);
		partner.gainEnergy(150);
		this.heal(20);
		partner.heal(20);
		System.out.printf("%s and %s took a long rest."
				+ " Their energy went up by 150 and they now have energies of %d and %d\n",
				this.getName(), partner.getName(), energy, partner.energy);
	}

	// Battle Room Actions
	public void useSkill() {
		System.out.println("Generic Skill Text 1");
	} // Overrided by the different classes
	public void attack() {
		super.attack();
		
		if (target.isDead()){
			for(Enemy e : tlg.currentRoom.getAllEnemies()) {
				if(!e.isDead()) {
					target = e;
					partner.targetSelect(target);
					System.out.println("Target has changed");
					break;
                                }
                        }
                }
	}
	
	public void stall() {
		System.out.printf("%s and %s rested and both regained 50 energy!\n", this.getName(),
				partner.getName());
		this.gainEnergy(50);
		partner.gainEnergy(50);
	}

	// Weapon Regulation
	public void gainWeapon(Weapon w) {
		weaponList.add(w);
	}
        
        public void changeWeapon(int index){
                weapon = weaponList.get(index);
        }
        
	public Weapon getWeaponFromList(int index){
                return weaponList.get(index);
        }
        
	// Maybe should be limited to treasure rooms
	public void newWeapon() {
		String[] orders = {"Archer", "Bard", "Cleric", "Knight", "Mage", "Rogue"};
		
		if (rand.nextInt(5) > 3) {
			Weapon findings = new Weapon("Hello", orders[rand.nextInt(6)], rand.nextInt(7) + 3, rand.nextInt(30) + 20);
			System.out.print("Would you like to keep the weapon \"" + findings.getName() + "\"? (y/n)");
			String choice = sc.nextLine();
			if (choice.equals("y"))
				gainWeapon(findings);
		}
	}
}
