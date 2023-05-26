package the_lucky_gauntlet.Exceptions;

import the_lucky_gauntlet.Character;
/**
 * This exception occurs when the Character tries to do perform an action despite having insufficient energy.
 * @author Athena Kimwell
 */
public class NoEnergyException extends Exception{
	public NoEnergyException() {
	}
	public NoEnergyException(String msg) {
		super(msg);
	}
	public NoEnergyException(Character npc) {
		System.out.println(npc.getName() + " has no more energy! ");
	}
}