package home.panel.info;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import dao.impl.BookmarkDAOImpl;
import dao.impl.HistoryDAOImpl;
import dao.impl.UserDAOImpl;
import entity.Vocabulary;
import home.item.ItemCategoryVocab;
import home.item.ItemVocab;
import service.Authorization;

public class PanelBookmark extends JPanel {
	public JPanel panel;
	public JLabel lblNewLabel;
	
	private JPanel panelParent;
	private JPanel panelContent;
	
	public PanelBookmark() {
		initComponent();
		loadData();
	}
	
	
	public void loadData() {
		
		int id = UserDAOImpl.getIdFromDbByAccount(Authorization.email);
		
		List<Vocabulary> vocabs = new BookmarkDAOImpl().selectAllVocabByUserId(id);
		if(vocabs.size() != 0) {
			final int NUMBER_OF_COL = 4;
			final int NUMBER_OF_ROW = (int) Math.ceil((double) vocabs.size() / NUMBER_OF_COL);
			System.out.println(NUMBER_OF_ROW);
			panelContent.setPreferredSize(new Dimension(660, NUMBER_OF_ROW * 220));
			Vocabulary vocab;
			int currentX = 10; 
			int currentY = 10;
			int k = 0;
			for(int i = 0; i < NUMBER_OF_ROW; i++ ) {
				for(int j = 0; j < NUMBER_OF_COL; j++) {
					if(k == vocabs.size()) break;
					vocab = vocabs.get(k++);
					
					ItemCategoryVocab item = new ItemCategoryVocab(vocab);
					item.setLocation(currentX, currentY);
					panelContent.add(item);
					
					currentX += 220;
				}
				currentX = 10;
				currentY += 220;
			}
		}
	}
	
	private void initComponent() {
		setBorder(null);
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 1004, 647);
		setLayout(new BorderLayout());
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);
		add(panel);
		
		JLabel lblHeader = new JLabel("TỪ VỰNG ĐÃ LƯU");
		lblHeader.setForeground(new Color(37, 57, 111));
		lblHeader.setFont(new Font("Arial", Font.BOLD, 24));
		lblHeader.setBounds(50, 30, 339, 50);
		panel.add(lblHeader);
		
		
		panelParent = new JPanel();
		panelParent.setLayout(new BorderLayout(0, 0));
		panelParent.setBounds(50, 91, 900, 523);
		panel.add(panelParent);
		
		panelContent = new JPanel();
		panelContent.setBackground(new Color(255, 255, 255));
		panelContent.setLayout(null);
		panelParent.add(panelContent);
		
		JScrollPane jspEx1 = new JScrollPane(panelContent, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelParent.add(jspEx1, BorderLayout.CENTER);
	}

}
