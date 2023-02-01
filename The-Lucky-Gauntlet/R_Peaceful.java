import java.util.ArrayList;

public class R_Peaceful extends Room{
	private int actionCount;
	
	public R_Peaceful(String n, boolean u, int aC) {
		super(n, u);
		actionCount = aC;
	}
	
	public void performAction(Player mc) {
		System.out.println("Actions: ");
		System.out.printf(" 1 - Leave Room\n");
		if(actionCount > 0) {
		System.out.printf(" 2 - Train\n"
						+ " 3 - Train %s\n"
						+ " 4 - Rest\n"
						+ " 5 - Open Menu\n", mc.getPartnerName());
		}
		System.out.print("What will you do? ");
		int action = Integer.parseInt(sc.nextLine());

		try {
			if(action == 1) {
				System.out.println(Room.getRoom("Main Hall").getName());
				mc.changeRoom(Room.getRoom("Main Hall"));
			}
			else if(actionCount > 0) {
				switch(action){
					case 2:
						mc.train();
						actionCount -= 1;
						break;
					case 3:
						mc.getPartner().train();
						actionCount -= 1;
						break;
					case 4:
						mc.rest();
						actionCount -= 1;
						break;
					case 5:
						this.openMenu();
						break;
					default: 
						System.out.println("There are no actions with that ID");
						break;
						//Action doesn't exist exception
				}
			}
			else {
				System.out.println("Enough hiding. It is time to re-enter the dungeon.");
			}
		}
		catch(NoEnergyException e) {
				System.out.print(e.getMessage());
				System.out.println(" They were forced to rest and regain energy");
				mc.rest();
			}
	}
}