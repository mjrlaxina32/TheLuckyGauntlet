package the_lucky_gauntlet;

import the_lucky_gauntlet.Exceptions.*;

import java.util.ArrayList;
/**
 * Player is extended from Character. 
 * It also contains a partner, type/order of player, an ArrayList of weapons and an ArrayList of players.
 * @author Athena Kimwell
 * @version 5-26-23
 */
public class Player extends Character {
	private Player partner;

    /**
     * Indicates whether Player is a player or a partner; no longer in use.
     */
    protected String playerType;
	private ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
	private static ArrayList<Player> players = new ArrayList<Player>();
        
        /**
         * Creates a player with a name, image filename, order, weapon, and adds it to the player list.
         * @param newName Name of player
         * @param newPlayerType Order of player
         * @param imageFilename Image filename of player
         */
	public Player(String newName, String newPlayerType, String imageFilename) {
		super(newName, imageFilename);
		playerType = newPlayerType;
		players.add(this);
                Weapon fists = new Weapon("Fists", "Fists.png", "Generic",0, 1000);
                weaponList.add(fists);
        }//!

	// Partner Methods
        /**
         * 
         * @param newPartner 
         */
	public void setPartner(Player newPartner) {
		this.partner = newPartner;
	}

	// Getter Methods
        /**
         * @return Returns player's partner
         */
	public Player getPartner() {
		return partner;
	}
        /**
         * @return Returns the name of player's partner
         */
	public String getPartnerName() {
		return partner.getName();
	}
        /**
         * @return Returns ArrayList of weapons
         */
        public ArrayList getWeaponList(){
                return weaponList;
        }
        /**
         * @return Returns player's weapon
         */
        public Weapon getWeapon(){
                return weapon;
        }
        
        
        
        /*public ArrayList getWeaponList(Player e){
                
                return weaponList;
        }*/
        
	// Peaceful Room Actions
        /**
         * Decreases player's energy by 25 and increases attack by 2.
         * @throws NoEnergyException 
         */
	public void train() throws NoEnergyException {
		this.useEnergy(25);
		this.attack += 2;
	}
        /**
         * Recharges player and partner's energies by 150 and heals them by 20.
         */
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
        /**
         * Runs player's specific skill, overridden by the different orders.
         */
	public void useSkill() {
		System.out.println("Generic Skill Text 1");
	} 
        /**
         * Runs attack method from Character. If target is dead, then the partner's target is changed to one that's not.
         */
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
	/**
         * Increases energy of player and partner by 50.
         */
	public void stall() {
		System.out.printf("%s and %s rested and both regained 50 energy!\n", this.getName(),
				partner.getName());
		this.gainEnergy(50);
		partner.gainEnergy(50);
	}

	// Weapon Regulation
        /**
         * Adds a weapon to the ArrayList of weapons.
         * @param w Weapon
         */
	public void gainWeapon(Weapon w) {
		weaponList.add(w);
	}
        /**
         * Changes the player/partner's weapon to one from the weapon list.
         * @param index Index of the weapon in the weapon list
         */
        public void changeWeapon(int index){
                weapon = weaponList.get(index);
        }
        /**
         * Gets a weapon from the weapon list by index.
         * @param index Index of the desired weapon
         * @return Returns the weapon of the specified index in the weapon list
         */
	public Weapon getWeaponFromList(int index){
                return weaponList.get(index);
        }
        
	// Maybe should be limited to treasure rooms
	/**
         * Asks the user if the player if they would like to keep a weapon, then adds it to the weapon list.
         */
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