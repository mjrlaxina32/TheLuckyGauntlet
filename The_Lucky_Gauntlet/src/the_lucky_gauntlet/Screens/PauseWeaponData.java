package the_lucky_gauntlet.Screens;

import the_lucky_gauntlet.*;
//import the_lucky_gauntlet.Weapon;

import javax.swing.*;
import java.awt.*;
import javax.swing.BorderFactory;

public class PauseWeaponData extends JPanel{
	public PauseWeaponData(Player p) {
		Weapon w = p.getWeapon();
		
		this.setLayout(new FlowLayout());
		
		JLabel weaponSprite = new JLabel();
		JPanel weaponData = new JPanel();
		
		ImageIcon sprite = new ImageIcon(PauseWeaponData.class.getResource("Fists.png"));
		weaponSprite.setIcon(sprite);
		weaponSprite.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));
		
		JLabel name = new JLabel(w.getName());
		
		JLabel blank = new JLabel("\n");
		
		JLabel attack = new JLabel("Atk Bonus: " + w.getAtkBonus());
		JLabel durability = new JLabel(w.getDurability() + "/" + w.getMaxDurability());
		
		weaponData.setLayout(new BoxLayout(weaponData, BoxLayout.Y_AXIS));
		
		int fontSize = 20;
		
		addWithFont(weaponData, name, fontSize);
		addWithFont(weaponData, blank, fontSize);
		addWithFont(weaponData, attack, fontSize);
		addWithFont(weaponData, durability, fontSize);
		
		durability.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(weaponSprite);
		this.add(weaponData);
		
		this.setVisible(true);
	}
	public void addWithFont(JComponent parent, JComponent component, int fontSize) {
		component.setFont(new Font("Sans-Serif", Font.PLAIN, fontSize));
		parent.add(component);
	}
}