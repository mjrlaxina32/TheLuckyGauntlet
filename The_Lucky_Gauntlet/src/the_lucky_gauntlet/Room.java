package the_lucky_gauntlet;

import the_lucky_gauntlet.Exceptions.InaccessibleRoomException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Room {
	private String name;
	protected boolean completed, unlocked, active;
	protected ArrayList<Player> players = new ArrayList<>();
	private static ArrayList<Room> map = new ArrayList<>();
	protected ArrayList<Room> adjacentRooms = new ArrayList<Room>();

	protected boolean gameState = true;

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
	public static ArrayList<Room> getAllRooms() {
		return map;
	}
	public ArrayList<Player> getAllPlayers() {
		return players;
	}
	public boolean getGameState() {
		return this.gameState;
	}
	
	// Room Movement Methods
	public void enterRoom(Player player) throws InaccessibleRoomException{
		if(unlocked == true){
			players.add(player);
			active = true;
		}
		else{
			throw new InaccessibleRoomException("The room is locked");
		}
	}
	public void leaveRoom(Player player){
  	players.remove(player);
		if(players.isEmpty()) {
			active = false;
		}
  }
	
	// Room Management Methods
	public void unlockRoom() {
		unlocked = true;
	}
	public void completeRoom() {
		completed = true;
	}

	// Room set-up methods
	public void attachRoom(Room room) {
		adjacentRooms.add(room);
	}
	public void attachRooms(ArrayList<Room> rooms) {
		adjacentRooms.addAll(rooms);
	}

	// Battle Room Methods (Overriden in R_Battle and not used in other methods)
	public ArrayList<Enemy> getAllEnemies() {
		return null;
	} //can we just remove this then
	// Won't be able to acces it in player. It's just a long story
	public void openMenu() {
		System.out.println("Actions: ");
			System.out.print(" 1 - Continue\n"
					+ " 2 - Show Stats\n"
					+ " 3 - End Game\n");
			System.out.print("What will you do? ");
			int action = Integer.parseInt(sc.nextLine());
		if(action == 1) {
			this.performAction(players.get(0));
		}
		else if(action == 2) {
			for(Player p : players) {
				p.showStats();
			}
			this.openMenu();
		}
		else if(action == 3) {
			gameState = false;
		}
	}	
	public abstract void performAction(Player mc);
}