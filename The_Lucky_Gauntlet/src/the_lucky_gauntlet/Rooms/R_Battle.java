package the_lucky_gauntlet.Rooms;

import java.util.ArrayList;

import the_lucky_gauntlet.*;
/**
 * R_Battle is extended from Room. 
 * It also contains an ArrayList of enemies.
 * @author Athena Kimwell
 * @version 5-26-23
 */
public class R_Battle extends Room{
	private ArrayList<Enemy> enemies;
        
        /**
         * Creates a Battle Room with a name and status.
         * @param n Name of the room
         * @param u Status of the room
         */
	public R_Battle(String n, boolean u) {
		super(n, u);
		enemies = new ArrayList<Enemy>();
		this.fillRoom(3);
	}

	// Getter Methods
        /**
         * @return Returns the ArrayList of enemies in the room.
         */
	public ArrayList<Enemy> getAllEnemies(){
		return this.enemies;
	}
	
	// Enemy Set-up Methods
        /**
         * Adds enemy to the ArrayList.
         * @param c Enemy
         */
	public void addEnemy(Enemy c){
		enemies.add(c);
	}
        /**
         * Adds enemies to the room until the enemyCount is fulfilled.
         * @param enemyCount Integer number of enemies in each room
         */
	public void fillRoom(int enemyCount){
		for(int i=0; i<enemyCount; i++) {
			Enemy enemy = new Enemy("Enemy " + (i+1));
			this.addEnemy(enemy);
		}
	}
}
