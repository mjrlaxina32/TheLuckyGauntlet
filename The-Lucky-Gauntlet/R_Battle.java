import java.util.ArrayList;

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
	
	// Room Actions Methods
	public void performAction(Player mc) {
		if (!completed){
			System.out.println("Actions: ");
			System.out.printf(" 1 - Attack\n"
					+ " 2 - %s Attack\n"
					+ " 3 - Use Skill\n"
					+ " 4 - Use %s's Skill\n"
					+ " 5 - Stall\n"
					+ " 6 - Open Menu\n", mc.getPartnerName(),mc.getPartnerName());
			System.out.print("What will you do? ");
			int action = Integer.parseInt(sc.nextLine());

			try {
				switch(action){
					case 1:
						mc.targetSelect(enemies);
						mc.attack();
						if(mc.getTarget().isDead()) {
							enemies.remove(mc.getTarget());
						}
						break;
					case 2:
						mc.getPartner().targetSelect(enemies);
						mc.getPartner().attack();
						if(mc.getPartner().getTarget().isDead()) {
							enemies.remove(mc.getTarget());
						}
						break;
					case 3:
						mc.useSkill();
						break;
					case 4:
						mc.getPartner().useSkill();
						break;
					case 5:
						mc.stall();
						break;
					case 6:
						this.openMenu();
						break;
					default: 
						System.out.println("There are no actions with that ID");
						break;
						//Action doesn't exist exception
				}
			}
			catch(NoEnergyException e) {
				System.out.print(e.getMessage());
				System.out.println(" They were forced to stall and regain energy");
				mc.stall();
			}
			completed = true;
			
			for (Enemy e : enemies) {
				if(!(e.isDead())) {
					completed = false;
				}
				e.targetSelect(players);
				try {
					e.attack();
				}
				catch(NoEnergyException f) {
					System.out.print(f.getMessage());
					System.out.println(" They were forced to stall and regain energy");
					mc.stall();
				}
				if(e.getTarget().isDead()) {
					gameState = false;
				}
			}
		}
		else {
			System.out.println("Actions: ");
			System.out.printf(" 1 - Leave Room\n");
			System.out.print("What will you do? ");
			int action = Integer.parseInt(sc.nextLine());

			if(action == 1) {
				mc.changeRoom(Room.getRoom("Main Hall"));
			}
		}
	}
}