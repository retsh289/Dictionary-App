package home.item;

import javax.swing.JPanel;
import dao.impl.WordTypeDAOImpl;
import entity.Vocabulary;
import helper.ImageUtils;
import helper.StringUtils;
import home.gui.FrameCategory;
import home.gui.FrameHome;
import home.panel.PanelVocab;
import service.CategoryService;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;


import java.awt.Cursor;


public class ItemCategoryVocab extends JPanel {

	private JLabel lblWord;
	private WordTypeDAOImpl typeDao;
	private CategoryService cateService;
	private Vocabulary vocab;
	public FrameCategory frameCate;
	public PanelVocab panelVocab;
	
	public ItemCategoryVocab(Vocabulary vocab) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doThisMouseClicked(e);
			}
		});
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		initComponent(vocab);
	}

	private void initComponent(Vocabulary vocab) {	
		this.vocab = vocab;
		setBorder(BorderFactory.createLineBorder(new Color(37, 57, 111), 2));
		setBackground(new Color(242, 247, 255));
		setLayout(null);
		setBounds(0, 0, 200, 200);
		typeDao = new WordTypeDAOImpl();

		JLabel lblImage = new JLabel("");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBackground(new Color(0, 0, 0));
		lblImage.setBounds(2, 20, 196, 100);
		add(lblImage);
		lblImage.setIcon(ImageUtils.getImageByURL("vocabulary", vocab.getImage(), 100));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(37, 57, 111));
		panel.setBounds(2, 140, 196, 58);
		add(panel);
		panel.setLayout(null);
		lblWord = new JLabel("");
		lblWord.setHorizontalAlignment(SwingConstants.CENTER);
		lblWord.setBounds(0, 0, 200, 58);
		panel.add(lblWord);
		lblWord.setForeground(new Color(255, 255, 255));
		lblWord.setFont(new Font("Arial", Font.PLAIN, 18));
		lblWord.setText(StringUtils.toCapitalize(vocab.getWord()) + " (" + typeDao.get(vocab.getWordTypeId()).toLowerCase() + ") ");
	}

	protected void doThisMouseClicked(MouseEvent e) {
		if(frameCate != null) {
			frameCate.dispose();
		}
		FrameHome.getMyInstance().navbarMenuChanged();
		panelVocab.getMyInstance().getPanelDetailVocab().setData(vocab);
	}
}
