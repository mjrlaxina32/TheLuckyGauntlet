import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Main {
	public static Player PlayerCreation(int order, String n) throws InvalidOrderException {
		switch (order) {
			case 1:
				return (new P_Archer(n));
			case 2:
				return (new P_Bard(n));
			case 3:
				return (new P_Cleric(n));
			case 4:
				return (new P_Knight(n));
			case 5:
				return (new P_Mage(n));
			case 6:
				return (new P_Rouge(n));
			default:
				throw new InvalidOrderException("That Order does not exist.");
			// System.out.println("That Order does not exist.");
		}
	}

	public static void main(String[] args) {
		// test statements
		/*
		 * System.out.println("Hello mate!");
		 * Player Adam = new Player("Adam");
		 * Player MJ = new Player("Mj");
		 * Player Athena = new Player("Athena");
		 */

		// Utility Variable Initialization
		Scanner sc = new Scanner(System.in);

		// Room Initialization
		R_Neutral mainHall = new R_Neutral("Main Hall", true);// (VERY IMPORTANT. DO NOT DELETE)
		R_Peaceful restroom = new R_Peaceful("Restroom", true, 3);
		R_Peaceful library = new R_Peaceful("Library", true, 4);
		R_Battle room302 = new R_Battle("Room 302", true);

		ArrayList<Room> sideRooms = Room.getAllRooms();
		mainHall.attachRooms(sideRooms);
		mainHall.adjacentRooms.remove(mainHall);

		room302.fillRoom(3);

		// Player Initialization
		Player mc = null;
		Player partner = null;
		boolean cond = false;
		do {
			System.out.print("Orders:\n" +
					" 1 - Archer\n" +
					" 2 - Bard\n" +
					" 3 - Cleric\n" +
					" 4 - Knight\n" +
					" 5 - Mage\n" +
					" 6 - Rouge\n" +
					"What will be your order: ");
			int mcOrder = Integer.parseInt(sc.nextLine());
			System.out.print("What is your name: ");
			String mcName = sc.nextLine();
			try {
				mc = PlayerCreation(mcOrder, mcName);
				cond = true;
			} catch (InvalidOrderException e) {
				System.out.println(e.getMessage());
			}
		} while (cond == false);
		System.out.print("What is your partner's name: ");
		String paName = sc.nextLine();
		try {
			partner = PlayerCreation((new Random()).nextInt(6) + 1, paName);
		} catch (InvalidOrderException e) {
			System.out.println(e.getMessage());
		}

		mc.setPartner(partner);
		partner.setPartner(mc);

		mc.changeRoom(mainHall);

		boolean continueGame = true;
		
		while (continueGame) {
			mc.action();
			continueGame = mc.getCurrentRoom().getGameState();
		}
	}
}
