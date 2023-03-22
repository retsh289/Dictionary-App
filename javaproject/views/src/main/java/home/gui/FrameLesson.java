package home.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.NumberOfDocuments;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import dao.LessonDAO;
import dao.QuestionDAO;
import dao.TheoryDAO;
import dao.VocabularyDAO;
import dao.impl.HistoryDAOImpl;
import dao.impl.LessonDAOImpl;
import dao.impl.QuestionDAOImpl;
import dao.impl.TheoryDAOImpl;
import dao.impl.UserDAOImpl;
import dao.impl.UserLessonResultDAOImpl;
import dao.impl.VocabularyDAOImpl;
import entity.Lesson;
import entity.Question;
import entity.Theory;
import entity.UserLessonResult;
import entity.Vocabulary;
import helper.FrameUtils;
import home.item.ItemCategoryVocab;
import home.item.ItemLesson;
import home.item.PanelQuestion;
import service.Authorization;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameLesson extends JFrame {

	private JPanel contentPane;
	private JPanel panelTheory;
	private JPanel panelContentTheory;
	
	private JPanel panelQuestions;
	private JPanel panelContentQuestion;
	
	public ItemLesson itemLesson;
	public int totalPoint;
	private Lesson ls;
	
	private TheoryDAO thDAO;
	private VocabularyDAO vocabDAO;
	private QuestionDAO qsDAO;
	private LessonDAO lsDAO;
	private static FrameLesson myInstance;
	private JButton btnNewButton;

	public static FrameLesson getMyInstance(Lesson ls) {
		if (myInstance == null) {
			myInstance = new FrameLesson(ls);
		} else {
			myInstance.dispose();
			myInstance = new FrameLesson(ls);
		}
		return myInstance;
	}
	
	public FrameLesson(Lesson ls) {
		this.ls = ls;
		thDAO = new TheoryDAOImpl();
		vocabDAO = new VocabularyDAOImpl();
		qsDAO = new QuestionDAOImpl();
		lsDAO = new LessonDAOImpl();
		initComponent();
		setData(new LessonDAOImpl().select(1));
	}

	public void setData(Lesson ls) {
		
//		set data theories
		List<Theory> thoeries= new TheoryDAOImpl().selAllTheoriesByLessonId(ls.getId());
		List<Vocabulary> vocabs = new ArrayList<>();
		for(Theory th : thoeries) {
			vocabs.add(vocabDAO.select(th.getVocabId()));
		}
		
		final int NUMBER_OF_COL_VOCABS = vocabs.size();
		panelContentTheory.setPreferredSize(new Dimension(NUMBER_OF_COL_VOCABS * 220, 230));
		Vocabulary vocab;
		int currentX = 10; 
		int currentY = 15;
		
		int k = 0;
		for(int j = 0; j < NUMBER_OF_COL_VOCABS; j++) {
			if(k == vocabs.size()) break;
			vocab = vocabs.get(k++);
			
			ItemCategoryVocab item = new ItemCategoryVocab(vocab);
			item.setLocation(currentX, currentY);
			panelContentTheory.add(item);
			
			currentX += 220;
		}
		
		
//		set data question

		List<Question> questions = new QuestionDAOImpl().selAllQuestionByLessonId(ls.getId());
		panelContentQuestion.setPreferredSize(new Dimension(700, 475 * questions.size() ));
		Question question;
		PanelQuestion panelQuestion;
		currentX = 0; 
		currentY = 0;
		
		for(int j = 0; j < questions.size(); j++) {
			question = questions.get(j);
			panelQuestion = new PanelQuestion(question, j+1, questions.size());
			panelQuestion.setLocation(currentX, currentY);
			panelQuestion.frameLesson = this;
			panelContentQuestion.add(panelQuestion);
			currentY += panelQuestion.getHeight() + 20;
		}
		
	}
	
	private void initComponent() {
		setBounds(0, 0, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initComponentTheroies();
		
		
		initComponentQuestions();
		
		FrameUtils.alignFrameScreenCenter(this);
	}

	private void initComponentQuestions() {
		panelQuestions = new JPanel();
		panelQuestions.setLayout(new BorderLayout(0, 0));
		panelQuestions.setBounds(40, 300, 700, 400);
		contentPane.add(panelQuestions);
		
		panelContentQuestion = new JPanel();
		panelContentQuestion.setBackground(new Color(255, 255, 255));
		panelContentQuestion.setLayout(null);
		panelQuestions.add(panelContentQuestion);
		
		JScrollPane jspEx1 = new JScrollPane(panelContentQuestion, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelQuestions.add(jspEx1, BorderLayout.CENTER);
		
		btnNewButton = new JButton("Nộp");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBtnNewButtonActionPerformed(e);
			}
		});
		btnNewButton.setBackground(new Color(37, 57, 111));
		btnNewButton.setBounds(349, 727, 89, 23);
		contentPane.add(btnNewButton);
	}

	private void initComponentTheroies() {
		panelTheory = new JPanel();
		panelTheory.setLayout(new BorderLayout(0, 0));
		panelTheory.setBounds(40, 30, 700, 250);
		contentPane.add(panelTheory);
		
		panelContentTheory = new JPanel();
		panelContentTheory.setBackground(new Color(255, 255, 255));
		panelContentTheory.setLayout(null);
		panelTheory.add(panelContentTheory);
		
		JScrollPane jspEx1 = new JScrollPane(panelContentTheory, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelTheory.add(jspEx1, BorderLayout.CENTER);
	}
	protected void doBtnNewButtonActionPerformed(ActionEvent e) {
		int id = UserDAOImpl.getIdFromDbByAccount(Authorization.email);
		UserLessonResultDAOImpl ulrDAO = new UserLessonResultDAOImpl();
		UserLessonResult ulr = ulrDAO.find(id, ls.getId());
		if(ulr == null) {
			ulrDAO.insert(new UserLessonResult(id, ls.getId(), totalPoint));
		} else {
			ulr.setPoint(totalPoint);
			ulrDAO.update(ulr);
		}
		JOptionPane.showMessageDialog(this, "Tổng điểm của bạn là: " + totalPoint);
		FrameHome.getMyInstance().navbarMenuChangedLesson();
		dispose();
	}
}
