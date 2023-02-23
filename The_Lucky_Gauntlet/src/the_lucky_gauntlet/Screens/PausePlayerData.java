package the_lucky_gauntlet.Screens;

import the_lucky_gauntlet.Player;

import javax.swing.*;
import java.awt.*;
import javax.swing.BorderFactory;

public class PausePlayerData extends JPanel{
	public PausePlayerData(Player p) {
		this.setLayout(new FlowLayout());
		
		JPanel playerData = new JPanel();
		JLabel playerSprite = new JLabel();
		
		ImageIcon sprite = new ImageIcon(PausePlayerData.class.getResource("archer.png"));
		playerSprite.setIcon(sprite);
		playerSprite.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));
		
		JLabel name = new JLabel(p.getName());
		JLabel className = new JLabel("class");
		
		JLabel blank = new JLabel("\n");
		
		JLabel attack = new JLabel("Atk: " + p.getAttack());
		JLabel health = new JLabel(p.getHealth() + "/" + p.getMaxHealth());
		JLabel energy = new JLabel(p.getEnergy() + "/" + p.getMaxEnergy());
		
		playerData.setLayout(new BoxLayout(playerData, BoxLayout.Y_AXIS));
		
		addWithFont(playerData, name);
		addWithFont(playerData, className);
		addWithFont(playerData, blank);
		addWithFont(playerData, attack);
		addWithFont(playerData, health);
		addWithFont(playerData, energy);
		
		health.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(playerSprite);
		this.add(playerData);
		
		this.setVisible(true);
	}
	public void addWithFont(JComponent parent, JComponent component) {
		component.setFont(new Font("Sans-Serif", Font.PLAIN, 25));
		parent.add(component);
	}
}