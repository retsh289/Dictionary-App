package admin.update;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import admin.insert.FrameInsertLesson;
import admin.item.ItemLesson;
import admin.item.ItemQuestion;
import dao.AnswerDAO;
import dao.LessonDAO;
import dao.QuestionDAO;
import dao.TheoryDAO;
import dao.impl.AnswerDAOImpl;
import dao.impl.LessonDAOImpl;
import dao.impl.QuestionDAOImpl;
import dao.impl.TheoryDAOImpl;
import entity.Answer;
import entity.Lesson;
import entity.Question;
import entity.Theory;
import helper.ErrorMessage;
import helper.FrameUtils;
import helper.ImageUtils;
import helper.StringUtils;
import service.LessonService;

public class FrameUpdateLesson extends JFrame{
	private JPanel contentPane;
	private JTextField textTitle;
	private JLabel lblNewLabel;
	private JLabel lblWord;
	private JButton btnAdd;
	private JPanel panel;
	private JLabel lblImage;
	private JButton btnImage;
	private JPanel panelShowImage;
	private JLabel lblShowImage;
	private LessonService lsSerivce;
	private JButton btnPlus;
	private JPanel panelParent;
	private JPanel panelChild;

	private List<ItemQuestion> qsItems;
	private List<HashMap<String, String>> questionAndAnswers;
	private Map<String, Object> data;

	private final int PANEL_ITEM_QUESTION_HEIGHT = 300;
	private int CURRENT_PANELS_MN_EX_HEIGHT = 210;
	private int HEIGHT_ADDED = 0;

	private Lesson ls;
	private LessonDAO lsDAO;
	private QuestionDAO qsDAO;
	private TheoryDAO thDAO;
	private AnswerDAO anDAO;
	
	public ItemLesson itemLesson;
	private static FrameUpdateLesson myInstance;

	public static FrameUpdateLesson getMyInstance(Lesson ls) {
		if (myInstance == null) {
			myInstance = new FrameUpdateLesson(ls);
		} else {
			myInstance.dispose();
			myInstance = new FrameUpdateLesson(ls);
		}
		return myInstance;
	}
	
	public FrameUpdateLesson(Lesson ls) {
		this.ls = ls;
		initComponent();
		questionAndAnswers = new ArrayList<>();
		data = new HashMap<>();
		loadData();
	}
	
	private void loadData() {
		lsDAO = new LessonDAOImpl();
		qsDAO = new QuestionDAOImpl();
		thDAO = new TheoryDAOImpl();
		anDAO = new AnswerDAOImpl();
		textTitle.setText(ls.getTitle());
		if (ls.getImage() != null) {
			final int ROW_HEIGHT = 170;
			lblShowImage.setIcon(ImageUtils.getImageByURL("lesson", ls.getImage(), ROW_HEIGHT));
		}
		
		List<Question> questions = qsDAO.selAllQuestionByLessonId(ls.getId());
		List<Theory> theories = thDAO.selAllTheoriesByLessonId(ls.getId());
		System.out.println(theories.size());
		System.out.println(questions.size());
		int k = 0;
		for(int i = 0; i < theories.size(); i++) {
			System.out.println(i);
			addItemQs();
			Theory th = theories.get(i);
			ItemQuestion qsItem = qsItems.get(i);
			qsItem.setComboVocabByVocabId(th.getVocabId());
			
			for(int j = 0; j < 3; j++, k ++) {
				Question qs = questions.get(k);
				List<Answer> answers;
				if(j % 3 == 0) {
					qsItem.setTextQuestion1(qs.getContent());
					answers = anDAO.selAllAnswerByQuestionId(qs.getId());
					qsItem.setTextAnswer1(joinAnswers(answers));
				} else if(j % 3 == 1) {
					qsItem.setTextQuestion2(qs.getContent());
					answers = anDAO.selAllAnswerByQuestionId(qs.getId());
					qsItem.setTextAnswer2(joinAnswers(answers));
				} else if(j % 3 == 2) {
					answers = anDAO.selAllAnswerByQuestionId(qs.getId());
					qsItem.setTextAnswer3(joinAnswers(answers));
				}
			}
		}
		
		if(theories.size() < 5) {
			for(int j = 0; j < 5 - theories.size(); j++) {
				addItemQs();
			}
		}
	}
	
	private String joinAnswers(List<Answer> answers) {
		String str = answers.stream().map(an -> StringUtils.toCapitalize(an.getContent()))
				.collect(Collectors.joining("; "));
		return str;
	}

	private void initComponent() {
		setResizable(false);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1005, 827);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		
		panelParent = new JPanel();
		panelParent.setBounds(0, 56, 989, 637);
		panelParent.setLayout(new BorderLayout(0, 0));
		getContentPane().add(panelParent);
		
		panelChild = new JPanel();
		panelChild.setBackground(new Color(255, 255, 255));
		panelChild.setBounds(0, 56, 989, 637);
		panelChild.setLayout(null);
		panelParent.add(panelChild);
		
		JScrollPane jspEx1 = new JScrollPane(panelChild, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelParent.add(jspEx1, BorderLayout.CENTER);
		
		lblNewLabel = new JLabel("Cập nhật bài học");
		lblNewLabel.setBounds(20, 11, 219, 34);
		lblNewLabel.setForeground(new Color(37, 57, 111));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(lblNewLabel);

		lblWord = new JLabel("Tiêu đề");
		lblWord.setBounds(31, 30, 84, 21);
		lblWord.setForeground(Color.BLACK);
		lblWord.setFont(new Font("Arial", Font.PLAIN, 14));
		panelChild.add(lblWord);

		btnAdd = new JButton("Cập nhật");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddActionPerformed(e);
			}
		});
		btnAdd.setBounds(394, 724, 150, 44);
		btnAdd.setBackground(new Color(67, 98, 190));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setFont(new Font("Arial", Font.BOLD, 16));
		contentPane.add(btnAdd);
		
		textTitle = new JTextField();
		textTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textTitle.setBounds(137, 21, 362, 37);
		panelChild.add(textTitle);
		textTitle.setColumns(10);

		panel = new JPanel();
		panel.setBounds(0, 0, 1289, 62);
		panel.setBackground(new Color(242, 247, 255));
		contentPane.add(panel);

		lblImage = new JLabel("Hình ảnh");
		lblImage.setBounds(31, 99, 96, 21);
		lblImage.setForeground(Color.BLACK);
		lblImage.setFont(new Font("Arial", Font.PLAIN, 14));
		panelChild.add(lblImage);

		btnImage = new JButton("Tải ảnh lên");
		btnImage.setBounds(137, 91, 175, 37);
		btnImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnImage_actionPerformed(e);
			}
		});
		btnImage.setForeground(new Color(0, 0, 0));
		btnImage.setFont(new Font("Arial", Font.BOLD, 14));
		btnImage.setBackground(new Color(242, 247, 255));
		panelChild.add(btnImage);

		panelShowImage = new JPanel();
		panelShowImage.setBackground(new Color(255, 255, 255));
		panelShowImage.setBounds(551, 21, 412, 170);
		panelChild.add(panelShowImage);
		panelShowImage.setLayout(new BorderLayout(0, 0));
		
		lblShowImage = new JLabel("");
		lblShowImage.setHorizontalAlignment(SwingConstants.CENTER);
		panelShowImage.add(lblShowImage);
		Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
		lblShowImage.setBorder(border);
		
		btnPlus = new JButton("+");
		btnPlus.setBounds(37, 724, 44, 44);
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnPlus_actionPerformed(e);
			}
		});
		btnPlus.setForeground(Color.WHITE);
		btnPlus.setFont(new Font("Arial", Font.BOLD, 16));
		btnPlus.setBackground(new Color(67, 98, 190));
		contentPane.add(btnPlus);
		qsItems = new ArrayList<>();
		
		FrameUtils.alignFrameScreenCenter(this);
		
//		addItemQs();
//		addItemQs();
//		addItemQs();
//		addItemQs();
//		addItemQs();
	}

	
	
	protected void do_btnImage_actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser("desktop://");
		chooser.setDialogTitle("Hình ảnh");
		chooser.setFileFilter(new FileNameExtensionFilter("image(jpg, png, gif)", "jpg", "png", "gif"));
		chooser.setAcceptAllFileFilterUsed(false);

		int result = chooser.showOpenDialog(null);
		File f = null;
		if (result == chooser.APPROVE_OPTION) {
			f = chooser.getSelectedFile();
			final int ROW_HEIGHT = 170;
			lblShowImage.setIcon(ImageUtils.getImageByFile(f, ROW_HEIGHT));
			data.put("image", f.toPath().toString());
		}
	}

	protected void btnAddActionPerformed(ActionEvent e) {
		data.put("id", ls.getId().toString());
		data.put("title", textTitle.getText());
		
		HashMap<String, String> mnEx;
		for(ItemQuestion item : qsItems) {
			questionAndAnswers.add((HashMap<String, String>) item.getQuestionsAndAnswersText());
		}
		data.put("questionAndAnswers", questionAndAnswers);
		
		Boolean isAllItemsValid = true;
		for(ItemQuestion item : qsItems) {
			if(item.getError() != null) {
				item.setRedBorder();
				isAllItemsValid = false;
				JOptionPane.showMessageDialog(this, item.getError());
			} else {
				item.setNormalBorder();
			}
			
		}
		
		if(isAllItemsValid) {
			lsSerivce = new LessonService();
			if (lsSerivce.update(data)) {
				JOptionPane.showMessageDialog(this, "Cập nhật bài học thành công");
				dispose();
				
				JPanel panel = this.itemLesson.panelParent.getPanel();
				panel.repaint();
				panel.revalidate();
				this.itemLesson.panelParent.loadData();
			} else {
				JOptionPane.showMessageDialog(this, ErrorMessage.ERROR_MESSAGES);
			}
		}
	}

	protected void do_btnPlus_actionPerformed(ActionEvent e) {
		addItemQs();
	}
	
	private void addItemQs() {
		ItemQuestion item = new ItemQuestion(CURRENT_PANELS_MN_EX_HEIGHT);
		CURRENT_PANELS_MN_EX_HEIGHT += PANEL_ITEM_QUESTION_HEIGHT;
		panelChild.setPreferredSize(new Dimension(panelChild.getWidth(), 600 + HEIGHT_ADDED));
		HEIGHT_ADDED += PANEL_ITEM_QUESTION_HEIGHT;
		panelChild.add(item);
		qsItems.add(item);
	}
}
