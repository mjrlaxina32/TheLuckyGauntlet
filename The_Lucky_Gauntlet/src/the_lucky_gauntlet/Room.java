package the_lucky_gauntlet;

import the_lucky_gauntlet.Exceptions.InaccessibleRoomException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import the_lucky_gauntlet.Rooms.R_Battle;
import the_lucky_gauntlet.Rooms.R_Peaceful;

/**
 * A Room class contains a name, a boolean value stating whether it's completed, unlocked, or active, 
 * and an ArrayList of Rooms that makes up the map.
 * @author Athena Kimwell
 * @version 5-26-23
 */
public abstract class Room {
	private String name;

    /**
     * Indicates the completion status of the room
     */
    protected boolean completed,

    /**
     * Indicates the unlocked status of the room
     */
    unlocked,

    /**
     * Indicates the active status of the room
     */
    active;
	private static ArrayList<Room> map = new ArrayList<>();

    /**
     * Scanner for user inputs
     */
	public Scanner sc = new Scanner(System.in);
	/**
         * Creates a room and adds it to the map.
         * @param name Name of room
         * @param u Status of room
         */
	public Room(String name, boolean u){
		this.name = name;
		completed = false;
		unlocked = u;
		active = false;
		map.add(this);
	}

	// Getter Methods
        /**
         * @return Returns name of room
         */
	public String getName(){
		return name;
	}
        /**
         * Gets a room by name.
         * @param roomName Name of the room
         * @return Returns either the designated room or null
         */
	public static Room getRoom(String roomName) {
		for(int i=0; i<map.size(); i++){
			if(map.get(i).getName().equals(roomName)) {
				return map.get(i);
			}
		}
		return null;
		// Can throw room doesn't exist method
	}
        /**
         * Gets a room by index in the map.
         * @param roomIndex Index of the room in the ArrayList
         * @return Returns room by index
         */
	public static Room getRoom(int roomIndex) {
		return map.get(roomIndex);
	}
        /**
         * Gets all rooms in the ArrayList.
         * @return Returns map
         */
	public static ArrayList<Room> getAllRooms() {
		return map;
	}	
	
	// Room Management Methods
        /**
         * Sets boolean unlocked as true.
         */
	public void unlockRoom() {
		unlocked = true;
	}
        /**
         * Sets boolean completed as true.
         */
	public void completeRoom() {
		completed = true;
	}
        public boolean isCompleted() {
		return completed;
	}
        /**
         * Clears map and creates a new randomized one.
         */
	public static void resetRooms() {
		map.clear();
		
		for(int i = 0; i<19; i++) {
			Random rand = new Random();
			if(rand.nextInt(3) == 2) {
				new R_Peaceful("Room " + i, true, 3);
			}
			else {
				new R_Battle("Room " + i, true);
			}
		}
		
		R_Battle bossRoom = new R_Battle("Room 18", false);
	}
	
	// Battle Room Methods (Overriden in R_Battle and not used in other methods)
	/**
         * Gets all enemies from the Enemy ArrayList.
         * @return Returns null
         */
	public ArrayList<Enemy> getAllEnemies() {
		return null;
	} //can we just remove this then
	// Won't be able to acces it in player. It's just a long story
}
