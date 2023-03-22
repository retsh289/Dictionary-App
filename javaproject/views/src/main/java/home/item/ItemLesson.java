package home.item;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;

import javax.swing.SwingConstants;

import dao.impl.TheoryDAOImpl;
import dao.impl.UserDAOImpl;
import dao.impl.UserLessonResultDAOImpl;
import dao.impl.VocabularyDAOImpl;
import entity.Lesson;
import entity.Theory;
import entity.UserLessonResult;
import entity.Vocabulary;
import helper.ImageUtils;
import helper.StringUtils;
import home.gui.FrameLesson;
import home.panel.PanelLesson;
import service.Authorization;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.BorderLayout;
import java.awt.Cursor;

public class ItemLesson extends JPanel {
	private JPanel panelTitle;
	private JPanel panel;
	private JLabel lblImage;
	private ItemCard cardContent;

	private Lesson ls;
	public PanelLesson panelLesson;

	public ItemLesson(Lesson ls) {
		this.ls = ls;

		initComponent();

		TheoryDAOImpl thDAO = new TheoryDAOImpl();
		VocabularyDAOImpl vocabDAO = new VocabularyDAOImpl();
		List<Theory> ths = thDAO.selAllTheoriesByLessonId(ls.getId());
		List<Vocabulary> vocabs = new ArrayList<>();
		ths.forEach(th -> vocabs.add(vocabDAO.select(th.getVocabId())));

		String strs = vocabs.stream().map(v -> StringUtils.toCapitalize(v.getWord())).collect(Collectors.joining(" ,"));

		UserDAOImpl userDAO = new UserDAOImpl();
		UserLessonResult ulr = new UserLessonResultDAOImpl().find(userDAO.selectIdByUserEmail(Authorization.email),
				ls.getId());

		int point = 0;
		if (ulr != null) {
			point = ulr.getPoint();
		}
		cardContent.setData(ls.getTitle(), strs, String.valueOf(point));
		lblImage.setIcon(ImageUtils.getImageByURL("lesson", ls.getImage(), 100));

	}

	private void initComponent() {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setLayout(null);
		setBackground(new Color(242, 247, 255));
		setBounds(20, 20, 264, 292);
		setBorder(BorderFactory.createLineBorder(new Color(37, 57, 111), 2));

		setOpaque(true);
		panelTitle = new JPanel();
		panelTitle.setBounds(2, 156, 260, 134);
		panelTitle.setBackground(new Color(242, 247, 255));
		add(panelTitle);
		panelTitle.setLayout(new BorderLayout(0, 0));
		cardContent = new ItemCard();
		cardContent.setColor1(new Color(37, 57, 111));
		cardContent.setColor2(new Color(116, 215, 252));

		panelTitle.add(cardContent);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(2, 2, 260, 288);

		add(panel);
		panel.setLayout(null);

		lblImage = new JLabel();
		lblImage.setBounds(2, 29, 260, 100);
		lblImage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblImage.setBackground(new Color(124, 141, 181));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblImage);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_this_mouseClicked(e);
			}
		});

	}

	protected void do_this_mouseClicked(MouseEvent e) {
		FrameLesson frameLs = FrameLesson.getMyInstance(ls);
		frameLs.itemLesson = this;
		frameLs.setVisible(true);
	}

}
