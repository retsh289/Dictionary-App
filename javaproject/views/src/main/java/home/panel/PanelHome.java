package home.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.SwingConstants;

import dao.impl.CategoryDAOImpl;
import dao.impl.LessonDAOImpl;
import entity.Category;
import entity.Lesson;
import home.gui.FrameHome;
import home.item.ItemCategory;
import home.item.ItemLesson;
import javax.swing.JButton;

public class PanelHome extends JPanel {

	private ItemLesson itemLesson;
	private JPanel panelLesson;
	private ItemCategory itemCategory;
	private Category cate;
	private LessonDAOImpl lsDAO;
	private CategoryDAOImpl cateDAO;
	private JPanel panelCategory;
	public FrameHome frameParent;

	
	private static PanelHome myInstance;

	public static PanelHome getMyInstance() {
		if (myInstance == null) {
			myInstance = new PanelHome();
		}
		return myInstance;
	}
	
	public PanelHome() {
		initComponent();
		lsDAO = new LessonDAOImpl();
		cateDAO = new CategoryDAOImpl();
		int currentX = 0;
		int currentY = 25;
		for (Lesson ls : lsDAO.selectTop3()) {
			ItemLesson item = new ItemLesson(ls);
			item.setLocation(currentX, currentY);
			panelLesson.add(item);
			currentX += item.getWidth() + 20;
		}

		currentX = 0;
		currentY = 25;
		for (Category cate : cateDAO.selectAll()) {
			ItemCategory item = new ItemCategory(cate);
			item.setLocation(currentX, currentY);
			panelCategory.add(item);
			currentX += item.getWidth() + 20;
		}

	}

	private void initComponent() {
		setBackground(new Color(37, 57, 111));
		setBounds(0, 0, 1302, 702);

		JLabel background = new JLabel();
		background.setBounds(0, 0, 1302, 702);
		background.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/image/background.png")).getImage()
				.getScaledInstance(1302, 702, Image.SCALE_SMOOTH)));
		add(background);
		setLayout(null);

		panelLesson = new JPanel();
		panelLesson.setBackground(new Color(255, 255, 255));
		panelLesson.setBounds(219, 11, 862, 319);
		panelLesson.setLayout(null);
		background.add(panelLesson);

		JLabel lblLesson = new JLabel("Bài học mới nhất");
		lblLesson.setBounds(0, 0, 200, 25);
		panelLesson.add(lblLesson);
		lblLesson.setHorizontalAlignment(SwingConstants.LEFT);
		lblLesson.setFont(new Font("Arial", Font.BOLD, 18));

//		
		JPanel panelParent = new JPanel();
		panelParent.setBounds(220, 330, 862, 245);
		panelParent.setBackground(new Color(255, 255, 255));
		panelParent.setLayout(new BorderLayout(0, 0));
		background.add(panelParent);
//		
		panelCategory = new JPanel();
		panelCategory.setBackground(new Color(255, 255, 255));
		panelCategory.setBounds(220, 330, 862, 225);
		panelCategory.setLayout(null);
		panelParent.add(panelCategory, BorderLayout.CENTER);

////			
		JScrollPane jsp = new JScrollPane(panelCategory, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBorder(null);
		panelParent.add(jsp);
		
		panelCategory.setPreferredSize(new Dimension(220 * 5, 225));
//		
		JLabel lblCategory = new JLabel("Chủ đề mới nhất");
		lblCategory.setBounds(0, 0, 200, 25);
		lblCategory.setHorizontalAlignment(SwingConstants.LEFT);
		lblCategory.setFont(new Font("Arial", Font.BOLD, 18));
		panelCategory.add(lblCategory);
//		
	}
}
