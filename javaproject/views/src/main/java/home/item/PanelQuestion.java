package home.item;

import javax.swing.JPanel;

import entity.Answer;
import entity.Question;
import entity.Vocabulary;
import helper.ImageUtils;
import home.gui.FrameLesson;
import jaco.mp3.player.MP3Player;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import dao.impl.AnswerDAOImpl;
import dao.impl.QuestionDAOImpl;

public class PanelQuestion extends JPanel {
	private JLabel lblNumberOfQuestion;
	private JLabel lblQuestion;
	private JPanel panelAnswer;
	private JPanel panelD;
	private JPanel panelA;
	private JPanel panelB;
	private JPanel panelC;
	
	private Question qs;
	private boolean isChosen;
	private int questionNumber;
	private int totalQuestions;
	public FrameLesson frameLesson;
	private JLabel answerA;
	private JLabel answerB;
	private JLabel answerD;
	private JLabel answerC;
	private String pronunciation;
	private Vocabulary vocab;
	private String rightAnswer;
	private JLabel lblPronunciation;

	public PanelQuestion(Question qs, int questionNumber, int totalQuestions) {
		this.qs = qs;
		this.totalQuestions = totalQuestions;
		this.questionNumber = questionNumber;
		initComponent();
		setData(qs);
	}

	private void initComponent() {
		setBackground(new Color(37, 55, 111));
		setBounds(0, 0, 700, 454);
		setLayout(null);
		
		lblNumberOfQuestion = new JLabel();
		lblNumberOfQuestion.setForeground(new Color(255, 255, 255));
		lblNumberOfQuestion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumberOfQuestion.setBounds(10, 11, 680, 33);
		add(lblNumberOfQuestion);
		
		lblQuestion = new JLabel();
		lblQuestion.setForeground(new Color(255, 255, 255));
		lblQuestion.setAutoscrolls(true);
		lblQuestion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQuestion.setBounds(10, 55, 680, 80);
		add(lblQuestion);
		
		panelAnswer = new JPanel();
		panelAnswer.setBounds(0, 170, 700, 261);
		add(panelAnswer);
		panelAnswer.setLayout(new GridLayout(4, 1, 0, 0));
		
		panelA = new JPanel();
		panelAnswer.add(panelA);
		panelA.setLayout(new BorderLayout(0, 0));
		
		answerA = new JLabel();
		answerA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doAnswerAMouseClicked(e);
			}
		});
		answerA.setFont(new Font("Tahoma", Font.PLAIN, 16));
		answerA.setHorizontalAlignment(SwingConstants.CENTER);
		panelA.add(answerA);
		
		panelB = new JPanel();
		panelAnswer.add(panelB);
		panelB.setLayout(new BorderLayout(0, 0));
		
		answerB = new JLabel();
		answerB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doAnswerBMouseClicked(e);
			}
		});
		answerB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		answerB.setHorizontalAlignment(SwingConstants.CENTER);
		panelB.add(answerB, BorderLayout.CENTER);
		
		panelC = new JPanel();
		panelAnswer.add(panelC);
		panelC.setLayout(new BorderLayout(0, 0));
		
		answerC = new JLabel();
		answerC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doAnswerCMouseClicked(e);
			}
		});
		answerC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		answerC.setHorizontalAlignment(SwingConstants.CENTER);
		panelC.add(answerC, BorderLayout.CENTER);
		
		panelD = new JPanel();
		panelAnswer.add(panelD);
		panelD.setLayout(new BorderLayout(0, 0));
		
		answerD = new JLabel();
		answerD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doAnswerDMouseClicked(e);
			}
		});
		answerD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		answerD.setHorizontalAlignment(SwingConstants.CENTER);
		panelD.add(answerD, BorderLayout.CENTER);
		
		lblPronunciation = new JLabel("");
		lblPronunciation.setBounds(23, 78, 40, 40);
		lblPronunciation = new JLabel("");
		lblPronunciation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				soundMouseClick(e);
			}
		});

		lblPronunciation.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/image/sound.png")).getImage()
				.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
		lblPronunciation.setBounds(22, 77, 40, 40);
		lblPronunciation.setVisible(false);
		add(lblPronunciation);
		
		for(Component cpm : panelAnswer.getComponents()) {
			((JPanel) cpm).setBorder(BorderFactory.createLineBorder(new Color(37, 57, 111), 1));
		}
		
	}	
	
	private void setData(Question qs) {
		if(qs.getContent().substring(Math.max(0, qs.getContent().length() - 3)).equals("mp3")) {
			pronunciation = qs.getContent();
			lblPronunciation.setVisible(true);
			lblQuestion.setVisible(false);
		} else {
			lblQuestion.setText(qs.getContent());
			lblQuestion.setVisible(true);
		}
		lblNumberOfQuestion.setText("Question " + questionNumber + " of " + totalQuestions);
		List<Answer> answers = new AnswerDAOImpl().selAllAnswerByQuestionId(qs.getId());
		
		for(int i = 0; i < answers.size(); i++) {
			answerA.setText(answers.get(0).getContent());
			answerB.setText(answers.get(1).getContent());
			answerC.setText(answers.get(2).getContent());
			answerD.setText(answers.get(3).getContent());
		}
		rightAnswer = answers.get(0).getContent();

	}
	
	protected void doAnswerAMouseClicked(MouseEvent e) {
		if(!isChosen) {
			if(answerA.getText().equals(rightAnswer)) {
				panelA.setBackground(Color.green);
				frameLesson.totalPoint += 1;
			} else {
				panelA.setBackground(Color.red);
			}
			isChosen = true;
		}
	}
	protected void doAnswerBMouseClicked(MouseEvent e) {
		if(!isChosen) {
			if(answerB.getText().equals(rightAnswer)) {
				panelB.setBackground(Color.green);
				frameLesson.totalPoint += 1;
			} else {
				panelB.setBackground(Color.red);
			}
			isChosen = true;
		}
	}
	protected void doAnswerCMouseClicked(MouseEvent e) {
		if(!isChosen) {
			if(answerC.getText().equals(rightAnswer)) {
				panelC.setBackground(Color.green);
				frameLesson.totalPoint += 1;
			} else {
				panelC.setBackground(Color.red);
			}
			isChosen = true;
		}
	}
	protected void doAnswerDMouseClicked(MouseEvent e) {
		if(!isChosen) {
			if(answerD.getText().equals(rightAnswer)) {
				panelD.setBackground(Color.green);
				frameLesson.totalPoint += 1;
			} else {
				panelD.setBackground(Color.red);
			}
			isChosen = true;
		}
	}
	
	protected void soundMouseClick(MouseEvent e) {
		if (pronunciation != null) {
			try {
				String url = ImageUtils.pathToResource + "/pronunciation/" + pronunciation;
				MP3Player mp3 = new MP3Player(new File(url));
				mp3.play();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy file phát âm");
			}
		}
	}
}
