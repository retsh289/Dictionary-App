package home.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelDictionary extends JPanel {
	
	private Image starImg = new ImageIcon(getClass().getResource("/image/star.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private Image starAltImg = new ImageIcon(getClass().getResource("/image/star-alt.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private JLabel lblStar;
	
	public PanelDictionary(String word, String wordType) {
		setBorder(null);
		setBackground(new Color(242, 247, 255));
		setBounds(0, 0, 1085, 702);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(32, 0, 1025, 671);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblWordType = new JLabel(wordType);
		lblWordType.setBounds(25, 52, 93, 22);
		panel.add(lblWordType);
		lblWordType.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel lblWord = new JLabel(word);
		lblWord.setBounds(25, 11, 175, 39);
		panel.add(lblWord);
		lblWord.setForeground(new Color(37, 57, 111));
		lblWord.setFont(new Font("Arial", Font.BOLD, 20));
		
		lblStar = new JLabel("");
		lblStar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_lblStar_mouseClicked(e);
			}
		});
		lblStar.setHorizontalAlignment(SwingConstants.CENTER);
		lblStar.setBounds(955, 11, 60, 39);
		lblStar.setIcon(new ImageIcon(starAltImg));
		panel.add(lblStar);
	}
	protected void do_lblStar_mouseClicked(MouseEvent e) {
		lblStar.setIcon(new ImageIcon(starImg));
	}
}
