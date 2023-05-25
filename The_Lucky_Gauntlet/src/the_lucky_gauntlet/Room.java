package the_lucky_gauntlet;

import the_lucky_gauntlet.Exceptions.InaccessibleRoomException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import the_lucky_gauntlet.Rooms.R_Battle;
import the_lucky_gauntlet.Rooms.R_Peaceful;

public abstract class Room {
	private String name;
	protected boolean completed, unlocked, active;
	private static ArrayList<Room> map = new ArrayList<>();

	public Scanner sc = new Scanner(System.in);
	
	public Room(String name, boolean u){
		this.name = name;
		completed = false;
		unlocked = u;
		active = false;
		map.add(this);
	}

	// Getter Methods
	public String getName(){
		return name;
	}
	public static Room getRoom(String roomName) {
		for(int i=0; i<map.size(); i++){
			if(map.get(i).getName().equals(roomName)) {
				return map.get(i);
			}
		}
		return null;
		// Can throw room doesn't exist method
	}
	public static Room getRoom(int roomIndex) {
		return map.get(roomIndex);
	}
	public static ArrayList<Room> getAllRooms() {
		return map;
	}	
	
	// Room Management Methods
	public void unlockRoom() {
		unlocked = true;
	}
	public void completeRoom() {
		completed = true;
	}

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
	}
	
	// Battle Room Methods (Overriden in R_Battle and not used in other methods)
	public ArrayList<Enemy> getAllEnemies() {
		return null;
	} //can we just remove this then
	// Won't be able to acces it in player. It's just a long story
}
