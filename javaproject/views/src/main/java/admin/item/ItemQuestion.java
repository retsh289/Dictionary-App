package admin.item;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import admin.insert.FrameInsertVocab;
import admin.update.FrameUpdateVocab;
import dao.impl.VocabularyDAOImpl;
import entity.Vocabulary;
import helper.ErrorMessage;
import helper.ImageUtils;
import helper.StringUtils;
import helper.Validation;
import jaco.mp3.player.MP3Player;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemQuestion extends JPanel {
	private JTextField textQuestion;
	private Panel panelQuestion;
	private JTextField textAnswer;
	private JTextField textQuestion2;
	private JTextField textAnswer2;
	private JTextField textAnswer3;
	private JComboBox<String> comboVocab;
	private JButton btnUpdateVocab;
	private JButton btnAddVocab;
	private Panel panelExample;
	private Panel panelMeaning2;
	private Panel panelExample_1;
	private Vocabulary selectingVocab;
	private MP3Player mp3 = null;
	private VocabularyDAOImpl vocabDAO;
	private JButton btnStopSound;
	private Integer currentIndexCbb;

	public ItemQuestion(int y) {
		initComponent(y);
		vocabDAO = new VocabularyDAOImpl();
		loadComboboxData();
	}

	private void loadComboboxData() {
		comboVocab.removeAllItems();
		comboVocab.addItem("");
		vocabDAO.selectIdAndWordAll().forEach(v -> comboVocab.addItem(joinIdAndWord(v.getId(), v.getWord())));
		comboVocab.setSelectedIndex(currentIndexCbb);
	}

	private void initComponent(int y) {
		setBackground(Color.white);
		setBounds(30, y, 930, 270);
		setLayout(null);
		Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
		setBorder(border);

		textQuestion = new JTextField();
		textQuestion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textQuestion.setBounds(95, 92, 350, 37);
		textQuestion.setColumns(10);
		add(textQuestion);

		JLabel lblQuestion = new JLabel("<html>\r\n    Câu hỏi<br/>\r\n(chọn nghĩa) \r\n</html>");
		lblQuestion.setFont(new Font("Arial", Font.PLAIN, 14));
		lblQuestion.setBounds(10, 92, 85, 37);
		add(lblQuestion);

		JLabel lblAnswer = new JLabel("Câu trả lời");
		lblAnswer.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAnswer.setBounds(465, 103, 85, 14);
		add(lblAnswer);

		textAnswer = new JTextField();
		textAnswer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textAnswer.setBounds(570, 92, 350, 37);
		textAnswer.setColumns(10);
		add(textAnswer);

		textQuestion2 = new JTextField();
		textQuestion2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textQuestion2.setBounds(95, 152, 350, 37);
		textQuestion2.setColumns(10);
		add(textQuestion2);

		JLabel lblAnswer_1 = new JLabel("Câu trả lời");
		lblAnswer_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAnswer_1.setBounds(465, 164, 85, 14);
		add(lblAnswer_1);

		textAnswer2 = new JTextField();
		textAnswer2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textAnswer2.setBounds(570, 152, 350, 37);

		textAnswer2.setColumns(10);
		add(textAnswer2, BorderLayout.CENTER);

		JLabel lblAnswer_1_1 = new JLabel("Câu trả lời");
		lblAnswer_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAnswer_1_1.setBounds(465, 224, 85, 14);
		add(lblAnswer_1_1);


		textAnswer3 = new JTextField();
		textAnswer3.setHorizontalAlignment(SwingConstants.LEFT);
		textAnswer3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textAnswer3.setBounds(570, 212, 350, 37);
		textAnswer3.setColumns(10);
		add(textAnswer3, BorderLayout.CENTER);

		JLabel lblVocab = new JLabel("Từ vựng");
		lblVocab.setFont(new Font("Arial", Font.PLAIN, 14));
		lblVocab.setBounds(10, 33, 65, 14);
		add(lblVocab);

		comboVocab = new JComboBox<String>();
		comboVocab.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				doComboVocabMousePressed(e);
			}
		});
		comboVocab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doComboVocabActionPerformed(e);
			}
		});
		comboVocab.setFont(new Font("Arial", Font.PLAIN, 16));
		comboVocab.setBackground(Color.WHITE);
		comboVocab.setBounds(95, 20, 350, 38);
		add(comboVocab);

		btnUpdateVocab = new JButton("Sửa");
		btnUpdateVocab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBtnSaActionPerformed(e);
			}
		});
		btnUpdateVocab.setBounds(570, 20, 150, 37);
		add(btnUpdateVocab);

		btnAddVocab = new JButton("Thêm");
		btnAddVocab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBtnThmActionPerformed(e);
			}
		});
		btnAddVocab.setBounds(770, 20, 150, 37);
		add(btnAddVocab);

		JLabel lblCuHiin = new JLabel("<html>\r\n    Câu hỏi<br/>\r\n(điền ô trống) \r\n</html>");
		lblCuHiin.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCuHiin.setBounds(10, 152, 95, 37);
		add(lblCuHiin);

		JLabel lblCuHipht = new JLabel("<html>\r\n    Câu hỏi<br/>\r\n(phát âm) \r\n</html>");
		lblCuHipht.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCuHipht.setBounds(10, 212, 85, 37);
		add(lblCuHipht);

		JButton btnPlaySound = new JButton("Play Sound");
		btnPlaySound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBtnPlaySoundActionPerformed(e);
			}
		});
		btnPlaySound.setBounds(95, 212, 175, 39);
		add(btnPlaySound);

		btnStopSound = new JButton("Stop Sound");
		btnStopSound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBtnStopSoundActionPerformed(e);
			}
		});
		btnStopSound.setBounds(270, 212, 175, 39);
		add(btnStopSound);
	}

	protected void doBtnSaActionPerformed(ActionEvent e) {
		if (selectingVocab != null) {
			FrameUpdateVocab fr = new FrameUpdateVocab(selectingVocab);
			fr.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn từ vựng nào!");
		}
	}

	protected void doBtnThmActionPerformed(ActionEvent e) {
		FrameInsertVocab fr = new FrameInsertVocab();
		fr.setVisible(true);
	}

	protected void doBtnPlaySoundActionPerformed(ActionEvent e) {
		if (selectingVocab != null) {
			String pronunciationURL = ImageUtils.pathToResource + "/pronunciation/" + selectingVocab.getPronunciation();
			mp3 = new MP3Player(new File(pronunciationURL));
			mp3.play();
		} else {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn từ vựng nào!");
		}
	}

	protected void doBtnStopSoundActionPerformed(ActionEvent e) {
		if (selectingVocab != null) {
			mp3.stop();
		} else {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn từ vựng nào!");
		}
	}

	public Map<String, String> getQuestionsAndAnswersText() {
		Map<String, String> qsAndAns = new HashMap<>();
		List<String> qsAndAnsTexts = componentTextFieldsText();
		if (comboVocab.getSelectedIndex() == 0)
			return null;
		qsAndAns.put("vocabId", getIdFromJoinedString((String) comboVocab.getSelectedItem()).toString());
		qsAndAns.put("question1", qsAndAnsTexts.get(0));
		qsAndAns.put("answer1", qsAndAnsTexts.get(1));
		qsAndAns.put("question2", qsAndAnsTexts.get(2));
		qsAndAns.put("answer2", qsAndAnsTexts.get(3));

		qsAndAns.put("question3", selectingVocab.getPronunciation() == null ? null : selectingVocab.getPronunciation());
		qsAndAns.put("answer3", qsAndAnsTexts.get(4));
		return qsAndAns;
	}

	private List<String> componentTextFieldsText() {
		List<String> qsAndAnsTexts = new ArrayList<>();
		Component[] components = this.getComponents();
		for (Component item : components) {
			if (item instanceof JTextField) {
				qsAndAnsTexts.add(((JTextField) item).getText());
			}
		}
		return qsAndAnsTexts;

	}

	private String joinIdAndWord(Integer id, String word) {
		StringBuilder s = new StringBuilder();
		s.append(id.toString());
		s.append(" - ");
		s.append(StringUtils.toCapitalize(word));
		return s.toString();
	}

	private Integer getIdFromJoinedString(String s) {
		if (s != null) {
			List<String> strs = Arrays.asList(s.split(" - "));
			return Integer.parseInt(strs.get(0));
		}
		return -1;

	}

	protected void doComboVocabActionPerformed(ActionEvent e) {
		currentIndexCbb = comboVocab.getSelectedIndex(); 
		if (comboVocab.getSelectedIndex() != 0) {
			selectingVocab = vocabDAO.select(getIdFromJoinedString((String) comboVocab.getSelectedItem()));
		}
	}
	
	protected void doComboVocabMousePressed(MouseEvent e) {
		loadComboboxData();
		
	}

	public String getError() {
		ErrorMessage.ERROR_MESSAGES = null;
//		 vocab is selected
		if (comboVocab.getSelectedIndex() != 0) {
			String error = validateQuestionAndAnswer(textQuestion.getText(), textAnswer.getText());
			if (error != null) {
				return error;
			}

			error = validateQuestionAndAnswer(textQuestion2.getText(), textAnswer2.getText());
			if (error != null) {
				return error;
			}

			if (textAnswer3.getText().equals("")) {
				return "Câu trả lời không được để trống";
			} else {
				List<String> answers = Arrays.asList(textAnswer3.getText().split(";"));
				if (answers.size() != 4) {
					return "Phải có đủ và chỉ có 4 câu trả lời";
				}
			}
		}
		return null;
	}

	private String validateQuestionAndAnswer(String question, String answer) {
		if (question.equals("")) {
			return "Câu hỏi không được để trống";
		} else {
			if (!Validation.checkLength(question, 1, 200))
				return "Độ dài câu hỏi không được quá 200 kí tự!";

			if (answer.equals("")) {
				return "Câu trả lời không được để trống";
			} else {
				List<String> answers = Arrays.asList(answer.split(";"));
				if (answers.size() != 4) {
					System.out.println(answers.size() + " : " + answers);
					return "Phải có đủ và chỉ có 4 câu trả lời";
				}
			}
		}
		return null;
	}

	public void setRedBorder() {
		Border border = BorderFactory.createLineBorder(Color.RED, 2);
		this.setBorder(border);
	}
	
	public void setNormalBorder() {
		Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
		this.setBorder(border);
	}
	
	public void setComboVocabByVocabId(Integer vocabId) {
		Vocabulary vocab = vocabDAO.select(vocabId);
		if(vocab != null) {
			comboVocab.setSelectedItem(joinIdAndWord(vocabId, vocab.getWord()));
		}
		
	}
	
	public void setTextQuestion1(String text) {
		textQuestion.setText(text);
	}
	
	public void setTextQuestion2(String text) {
		textQuestion2.setText(text);
	}
	
	public void setTextAnswer1(String text) {
		textAnswer.setText(text);
	}
	
	public void setTextAnswer2(String text) {
		textAnswer2.setText(text);
	}
	
	public void setTextAnswer3(String text) {
		textAnswer3.setText(text);
	}
}
