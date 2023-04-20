package the_lucky_gauntlet.Rooms;

import java.util.logging.Level;
import java.util.logging.Logger;
import the_lucky_gauntlet.*;
import the_lucky_gauntlet.Exceptions.NoEnergyException;

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

		
		if(action == 1) {
			System.out.println(Room.getRoom("Main Hall").getName());
			mc.changeRoom(Room.getRoom("Main Hall"));
		}
		else if(actionCount > 0) {
			switch(action){
				case 2:
                            {
                                try {
                                    mc.train();
                                } catch (NoEnergyException ex) {
                                    System.out.println("No more energy");
                                }
                            }
					actionCount -= 1;
					break;

				case 3:
                            {
                                try {
                                    mc.getPartner().train();
                                } catch (NoEnergyException ex) {
                                    System.out.println("No more energy");
                                }
                            }
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
}
