package the_lucky_gauntlet.Rooms;

import java.util.ArrayList;

import the_lucky_gauntlet.*;

public class R_Battle extends Room{
	private ArrayList<Enemy> enemies;

	public R_Battle(String n, boolean u) {
		super(n, u);
		enemies = new ArrayList<Enemy>();
	}

	// Getter Methods
	public ArrayList<Enemy> getAllEnemies(){
		return this.enemies;
	}
	
	// Enemy Set-up Methods
	public void addEnemy(Enemy c){
		enemies.add(c);
		c.updateRoom(this);
	}
	public void fillRoom(int enemyCount){
		for(int i=0; i<enemyCount; i++) {
			Enemy enemy = new Enemy("Enemy " + (i+1));
			this.addEnemy(enemy);
		}
	}
}
