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
}
