package home.panel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import dao.impl.CategoryDAOImpl;
import entity.Category;
import home.gui.FrameHome;
import home.item.ItemCategory;
import java.awt.GridLayout;
import java.util.List;

public class PanelCategory extends JPanel {
	public JPanel panelMain;
	public JScrollPane scrollPane;
	public JPanel panel;
	public JLabel lblNewLabel;
	public FrameHome frameParent;
	
	private static PanelCategory myInstance;

	public static PanelCategory getMyInstance() {
		if (myInstance == null) {
			myInstance = new PanelCategory();
		}
		return myInstance;
	}
	public PanelCategory() {
		initComponent();
		int currentX = 10;
		int currentY = 0;
		int xGap = 10;
		int yGap = 10;
		
		Category cate;
		ItemCategory item = null;
		List<Category> cates = new CategoryDAOImpl().selectAll();
		int k = 0;
		for (int i = 0; i < 5 ; i++) {
			for(int j = 0; j < 6; j++) {
				if(k == cates.size() - 1) break;
				cate = cates.get(k++);
				
				item = new ItemCategory(cate);
				item.setLocation(currentX, currentY);
				item.panelParent = this;
				panelMain.add(item);
				
				currentX += item.getWidth() + xGap;
			}
			currentY += item.getHeight() + yGap; 
			currentX = 10;
		}
	}
	private void initComponent() {
		setBorder(null);
		setBackground(new Color(37, 57, 111));
		setBounds(0, 0, 1302, 702);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 0, 1282, 691);
		add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(10, 11, 1262, 669);
		panel.add(scrollPane);
		
		panelMain = new JPanel();
		panelMain.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(panelMain);
		panelMain.setLayout(null);
	}

}
