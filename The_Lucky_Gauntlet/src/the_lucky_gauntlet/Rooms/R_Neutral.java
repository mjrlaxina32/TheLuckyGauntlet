package the_lucky_gauntlet.Rooms;

import java.util.ArrayList;

import the_lucky_gauntlet.*;

public class R_Neutral extends Room {
	public ArrayList<Room> adjacentRooms = new ArrayList<Room>();
	
	public R_Neutral(String n, boolean u) {
		super(n, u);
	}
	
	public void attachRoom(Room room) {
		adjacentRooms.add(room);
	}
	public void attachRooms(ArrayList<Room> rooms) {
		adjacentRooms.addAll(rooms);
	}
	
	// Room actions
	public void performAction(Player mc) {
		System.out.println("Actions: ");
		int lastIndex = 0;
		for(int i=0;i<adjacentRooms.size();i++){
			System.out.printf(" %d - Enter %s\n", i+1, adjacentRooms.get(i).getName());
			lastIndex = i+2;
		}
		System.out.printf(" %d - Open Menu\n", lastIndex);
		int action = Integer.parseInt(sc.nextLine())-1;

		if((action+1) == lastIndex) {
			this.openMenu();
		}
		else {
			mc.changeRoom(adjacentRooms.get(action));
		}
	}
}