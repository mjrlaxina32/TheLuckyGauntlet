package the_lucky_gauntlet.Rooms;

import java.util.ArrayList;

import the_lucky_gauntlet.*;
/**
 * R_Neutral is extended from Room. 
 * It also contains an ArrayList of adjacent rooms.
 * @author Athena Kimwell
 * @version 5-26-23
 */
public class R_Neutral extends Room {

    /**
     * List of adjacent rooms
     */
    public ArrayList<Room> adjacentRooms = new ArrayList<Room>();
	
        /**
         * Creates a Neutral Room with a name and status.
         * @param n Name of room
         * @param u Status of room
         */
	public R_Neutral(String n, boolean u) {
		super(n, u);
	}
	/**
         * Adds rooms to the ArrayList of adjacent rooms.
         * @param room Room adjacent to the current room
         */
	public void attachRoom(Room room) {
		adjacentRooms.add(room);
	}
        /**
         * Adds the ArrayList of rooms to the ArrayList of adjacent rooms. 
         * @param rooms ArrayList of rooms
         */
	public void attachRooms(ArrayList<Room> rooms) {
		adjacentRooms.addAll(rooms);
	}
}
