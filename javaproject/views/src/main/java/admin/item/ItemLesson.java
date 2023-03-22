package admin.item; 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import admin.panel.PanelLesson;
import admin.update.FrameUpdateLesson;
import admin.update.FrameUpdateVocab;
import dao.impl.CategoryDAOImpl;
import dao.impl.TheoryDAOImpl;
import dao.impl.VocabularyDAOImpl;
import dao.impl.WordTypeDAOImpl;
import entity.Category;
import entity.Lesson;
import entity.Meaning;
import entity.Theory;
import entity.Vocabulary;
import helper.FrameUtils;
import helper.ImageUtils;
import helper.StringUtils;
import service.LessonService;
import service.VocabularyService;

public class ItemLesson extends JPanel {
	private LessonService lsService;
	private Lesson ls;
	public PanelLesson panelParent;
	
	public ItemLesson(Lesson ls, int y) {
		this.ls = ls;
		initComponent(y);
		lblId.setText(ls.getId().toString());
		lblTitle.setText(StringUtils.toCapitalize(ls.getTitle()));
		lblImage.setIcon(ImageUtils.getImageByURL("lesson", ls.getImage(), 78));
		List<Theory> ths = new TheoryDAOImpl().selAllTheoriesByLessonId(ls.getId());
		VocabularyDAOImpl vocabDAO = new VocabularyDAOImpl();
		
		StringBuilder vocabsStr = new StringBuilder();
		Theory th = null;
		for(int i = 0; i < Math.min(5, ths.size()); i++) {
			th = ths.get(i);
			if(i != ths.size() - 1) {
				vocabsStr.append(StringUtils.toCapitalize(vocabDAO.select(th.getVocabId()).getWord()));
				vocabsStr.append(", ");
			} else {
				vocabsStr.append(StringUtils.toCapitalize(vocabDAO.select(th.getVocabId()).getWord()));
			}
		}
		lblVocabs.setText("<html>" + vocabsStr + "</html>");
		
	}

	private void initComponent(int y) {
		setLayout(null);
		setBounds(0, y, 980, 80);

		panel = new JPanel();
		panel.setBounds(0, 77, 995, 3);
		add(panel);
		panel.setBackground(new Color(238, 238, 238));
		panelHeader_1 = new JPanel();
		panelHeader_1.setBounds(0, 0, 980, 80);
		add(panelHeader_1);
		panelHeader_1.setLayout(new GridLayout(0, 6, 0, 0));

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panelHeader_1.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		lblId = new JLabel("");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setForeground(new Color(0, 0, 0));
		lblId.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1.add(lblId);

		panel_1_2 = new JPanel();
		panel_1_2.setBackground(new Color(255, 255, 255));
		panelHeader_1.add(panel_1_2);
		panel_1_2.setLayout(new BorderLayout(0, 0));

		lblTitle = new JLabel("");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(new Color(0, 0, 0));
		lblTitle.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1_2.add(lblTitle);
		

		panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(255, 255, 255));
		panelHeader_1.add(panel_2_1);
		panel_2_1.setLayout(new BorderLayout(0, 0));
		
		lblVocabs = new JLabel("");
		lblVocabs.setHorizontalAlignment(SwingConstants.LEFT);
		lblVocabs.setForeground(Color.BLACK);
		lblVocabs.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_2_1.add(lblVocabs, BorderLayout.CENTER);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panelHeader_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		lblImage = new JLabel();
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setForeground(new Color(0, 0, 0));
		lblImage.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_2.add(lblImage);
		

		panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(new Color(255, 255, 255));
		panelHeader_1.add(panel_1_1_1);
		panel_1_1_1.setLayout(null);

		btnEdit = new JButton("Sửa");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditActionPerfromed(e);
			}
		});
		btnEdit.setBounds(60, 25, 58, 30);
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Arial", Font.BOLD, 14));
		btnEdit.setBorder(null);
		btnEdit.setBackground(new Color(67, 98, 190));
		panel_1_1_1.add(btnEdit);

		panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(255, 255, 255));
		panelHeader_1.add(panel_1_1);

		btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteActionPerformed(e);
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
		btnDelete.setBorder(null);
		btnDelete.setBackground(new Color(205, 16, 64));
		btnDelete.setBounds(60, 25, 60, 30);
		panel_1_1.add(btnDelete);
	}
	
	protected void btnEditActionPerfromed(ActionEvent e) {
		FrameUpdateLesson fr = FrameUpdateLesson.getMyInstance(ls);
		fr.itemLesson = this;
		fr.setVisible(true);
	}

	protected void btnDeleteActionPerformed(ActionEvent e) {
		int option = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa bài học này?", "Xóa từ vựng",
				JOptionPane.YES_NO_OPTION);
		
		if (option == JOptionPane.YES_OPTION) {
			lsService = new LessonService();
			if (lsService.delete(ls)) {
				JOptionPane.showMessageDialog(this, "Xoá bài học thành công!");
				
				JPanel panel = this.panelParent.getPanel();
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				this.panelParent.loadData();
			}
		}
	}
	
	private JPanel panel;
	private JPanel panelHeader_1;
	private JPanel panel_1;
	private JLabel lblId;
	private JPanel panel_1_2;
	private JLabel lblTitle;
	private JPanel panel_2_1;
	private JPanel panel_2;
	private JLabel lblImage;
	private JPanel panel_1_1_1;
	private JButton btnEdit;
	private JPanel panel_1_1;
	private JButton btnDelete;
	private JLabel lblVocabs;
}
