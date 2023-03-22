package home.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dao.impl.LessonDAOImpl;
import entity.Lesson;
import home.gui.FrameHome;
import home.item.ItemLesson;

public class PanelLesson extends JPanel {

	private JPanel panel;
	private ItemLesson item;
	private JScrollPane scrollPane;
	private JPanel panelMain;

	private LessonDAOImpl lsDAO;
	public FrameHome frameParent;
	
	private static PanelLesson myInstance;

	public static PanelLesson getMyInstance() {
		if (myInstance == null) {
			myInstance = new PanelLesson();
		} else {
			myInstance.loadData();
		}
		return myInstance;
	}

	
	public PanelLesson() {
		initComponent();
		panelMain.setLayout(null);
		lsDAO = new LessonDAOImpl();
		
		loadData();
	}

	public void loadData() {
		int currentX = 0;
		int currentY = 0;
		for(Lesson ls : lsDAO.selectAll()) {
			ItemLesson item = new  ItemLesson(ls);
			item.setLocation(currentX, currentY);
			item.panelLesson = this;
			panelMain.add(item);
			currentX += item.getWidth() + 20;
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
	}

}
