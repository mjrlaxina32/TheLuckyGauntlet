package the_lucky_gauntlet;

import the_lucky_gauntlet.Exceptions.*;
import the_lucky_gauntlet.Screens.*;

public class The_Lucky_Gauntlet {
	public static void main(String[] args) {
		Player mc = new Player("Henry", "mc");
		Player partner = new Player("Joe", "partner");
		
		mc.setPartner(partner);
		partner.setPartner(mc);
		
		try {
			mc.useEnergy(mc.getEnergy()+1);
		}
		catch (NoEnergyException NEE){
			mc.stall();
		}
		
		new Pause(mc, partner);
	}
}
