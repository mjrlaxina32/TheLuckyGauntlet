package the_lucky_gauntlet.Screens;

import the_lucky_gauntlet.Player;

import javax.swing.*;
import java.awt.*;
import javax.swing.BorderFactory;

public class Pause extends JFrame{
	GridBagConstraints c = new GridBagConstraints();
	
	public Pause(Player mc, Player partner) {
		super("Exercise 11: Muon-Lanuza, Adam");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		this.setLayout(new BorderLayout(0, 10));
		
		// Title Pane
		JLabel title = new JLabel("Paused", SwingConstants.CENTER);
		title.setFont(new Font("Sans-Serif", Font.PLAIN, 40));
		int borderRadius = 25;
		title.setBorder(BorderFactory.createEmptyBorder(borderRadius+25, borderRadius, borderRadius, borderRadius));
		
		this.add(title, BorderLayout.NORTH);
		
		
		// Details Pane
		JPanel details = new JPanel();
		details.setLayout(new GridBagLayout());
		
		JPanel mcDetails = new PausePlayerData(mc);
		JPanel partnerDetails = new PausePlayerData(partner);
		JPanel mcWeaponDetails = new PauseWeaponData(mc);
		JPanel partnerWeaponDetails = new PauseWeaponData(partner);
		c.weightx = 0.1;
		
		addDataGrid(details, mcDetails, 0, 0);
		addDataGrid(details, partnerDetails, 0, 2);
		addDataGrid(details, mcWeaponDetails, 1, 0);
		addDataGrid(details, partnerWeaponDetails, 1, 2);
		
		this.add(details, BorderLayout.CENTER);
		
		// Menu Pane
		JPanel menu = new JPanel();
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		
		
		JButton settingsButton = new JButton("Settings");
		JButton homeButton = new JButton("Home Screen");
		JButton mapButton = new JButton("See Map");
		JButton returnButton = new JButton("Return to Game");		
		
		addButton(menu, settingsButton);
		addButton(menu, homeButton);
		addButton(menu, mapButton);
		addButton(menu, returnButton);
		
		borderRadius = 25;
		menu.setBorder(BorderFactory.createEmptyBorder(0, borderRadius, 40, borderRadius));
		this.add(menu, BorderLayout.SOUTH);
		
		this.setVisible(true);
		
	}
	public void addDataGrid (JPanel parent, JPanel child, int row, int column) {
		c.gridy = row;
		c.gridx = column;
		
		parent.add(child, c);
	}
	public void addButton (JPanel parent, JButton child) {
		child.setFont(new Font("Sans-Serif", Font.PLAIN, 22));
		child.setAlignmentX(Component.CENTER_ALIGNMENT);
		child.setSize(new Dimension(100,100));
		
		parent.add(child);
	}
}