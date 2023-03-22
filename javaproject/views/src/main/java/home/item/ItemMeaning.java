package home.item;

import java.awt.Color;

import javax.swing.JPanel;
import java.awt.Panel;
import javax.swing.JTextField;
import java.awt.Font;

public class ItemMeaning extends JPanel {
	private JTextField textMeaning;

	public ItemMeaning(int y) {
		setBackground(new Color(255, 255, 255));
		setBounds(0, y, 631, 65);
		setLayout(null);
		
		Panel panelMeaning = new Panel();
		panelMeaning.setLayout(null);
		panelMeaning.setBackground(Color.BLACK);
		panelMeaning.setBounds(147, 10, 362, 42);
		add(panelMeaning);
		
		textMeaning = new JTextField();
		textMeaning.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textMeaning.setColumns(10);
		textMeaning.setBounds(0, 0, 362, 42);
		panelMeaning.add(textMeaning);
	}
}
