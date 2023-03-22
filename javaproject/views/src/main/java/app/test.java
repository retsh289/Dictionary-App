package app;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import dao.impl.BookmarkDAOImpl;
import dao.impl.UserDAOImpl;
import dao.impl.VocabularyDAOImpl;
import entity.User;
import helper.IconImage;
import home.panel.PanelDetailVocab;
import service.Authorization;

import java.awt.GridLayout;
import javax.swing.JLabel;

public class test extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel contentPane1;
	private JToggleButton tglbtnNewToggleButton;
	private JLabel lblNewLabel;
	private JToggleButton toggleButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		new UserDAOImpl().updateRole(new User(17, null, null, 2));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 200, 600, 600);
		contentPane1 = new JPanel();
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane1);
		contentPane1.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(20, 11, 521, 342);
		contentPane1.add(panel);
		panel.setLayout(null);
		
		tglbtnNewToggleButton = new JToggleButton("helo");
		tglbtnNewToggleButton.setBorderPainted(false);
//		tglbtnNewToggleButton.setIcon((new ImageIcon(icon.getStarImg())));
		tglbtnNewToggleButton.setContentAreaFilled(false);
		tglbtnNewToggleButton.setBounds(100, 57, 70, -50);
		 panel.add(toggleButton);
		 panel.add(tglbtnNewToggleButton);
		 IconImage icon = new IconImage();
		 
		 lblNewLabel = new JLabel("New label");
		 lblNewLabel.setBounds(10, 28, 141, 66);
		 panel.add(lblNewLabel);
		 lblNewLabel.setIcon((new ImageIcon(icon.getStarImg())));
		 
		 toggleButton = new JToggleButton("");
		 toggleButton.setBorderPainted(false);
		 toggleButton.setBounds(198, 28, 158, 35);
		 toggleButton.setIcon((new ImageIcon(icon.getStarImg())));
		 toggleButton.setContentAreaFilled(false);
		 panel.add(toggleButton);
	}

}
