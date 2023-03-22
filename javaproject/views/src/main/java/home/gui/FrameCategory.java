package home.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import dao.impl.CategoryDAOImpl;
import entity.Category;
import entity.Vocabulary;
import helper.FrameUtils;
import home.item.ItemCategory;
import home.item.ItemCategoryVocab;
import home.panel.PanelVocab;

import java.awt.GridLayout;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class FrameCategory extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panelParent;
	
	private Category category;
	public ItemCategory itemCate;

	private static FrameCategory myInstance;
	
	public static FrameCategory getMyInstance(Category category) {
		if (myInstance == null) {
			myInstance = new FrameCategory(category);
		} else {
			myInstance.dispose();
			myInstance = new FrameCategory(category);
		}
		return myInstance;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public FrameCategory(Category category) {
		setTitle("Thể loại: " + category.getName());
		setResizable(false);
		this.category = category;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 690, 680);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		panelParent = new JPanel();
		panelParent.setBounds(0, 0, 674, 641);
		panelParent.setLayout(new BorderLayout(0, 0));
		getContentPane().add(panelParent);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);
		panelParent.add(panel);
		
		JScrollPane jspEx1 = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelParent.add(jspEx1, BorderLayout.CENTER);
		
		
		loadData();
		
		FrameUtils.alignFrameScreenCenter(this);
	}

	public void loadData() {
		final int NUMBER_OF_ROW = 3;
		final int NUMBER_OF_COL = 3;
		
		
		List<Vocabulary> vocabs = new CategoryDAOImpl().selectAllVocabByCategoryId(category.getId());
		panel.setPreferredSize(new Dimension(660, (int) Math.ceil((double) vocabs.size() / NUMBER_OF_ROW) * 220));
		int currentX = 10; 
		int currentY = 10;
		int k = 0;
		Vocabulary vocab;
		for(int i = 0; i < (int) Math.ceil((double) vocabs.size() / NUMBER_OF_ROW); i++ ) {
			for(int j = 0; j < NUMBER_OF_COL; j ++) {
				vocab = vocabs.get(k++);
				
				ItemCategoryVocab item = new ItemCategoryVocab(vocab);
				item.setLocation(currentX, currentY);
				item.frameCate = this;
				panel.add(item);
				
				currentX += 220;
				if(k == vocabs.size()) break;
			}
			currentX = 10;
			currentY += 220;
		}
	}

}
