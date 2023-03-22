package home.item;

import javax.swing.JPanel;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class itemContentVocab extends JPanel {

	/**
	 * Create the panel.
	 */
	public itemContentVocab() {
		setBounds(0, 0, 1294, 676);
		setLayout(null);
		
		JLabel loa = new JLabel("");
		loa.setBounds(535, 72, 58, 89);
		loa.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/image/loa.jpg")).getImage()
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		add(loa);
		
		JLabel bgVocab = new JLabel("");
		bgVocab.setBounds(0, 0, 1292, 676);
		bgVocab.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/image/contentVocab.png")).getImage()
				.getScaledInstance(1302, 702, Image.SCALE_DEFAULT)));
		add(bgVocab);

	}

}
