package home.panel.info;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import dao.FeedbackDAO;
import dao.VocabularyContributionDAO;
import dao.impl.FeedbackDAOImpl;
import dao.impl.UserDAOImpl;
import dao.impl.VocabularyContributionDAOImpl;
import entity.Feedback;
import entity.VocabularyContribution;
import helper.ErrorMessage;
import helper.StringUtils;
import helper.Validation;
import service.Authorization;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class PanelRespose extends JPanel{

	private JPanel panelContent;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblNewLabel_1;
	private JScrollBar scrollBar;
	private JTextArea txtContent;
	private JLabel lblContent;
	
	private JPanel panelContentFeedback;
	private JPanel panelContentVocabContri;

	private static PanelRespose myInstance;
	private JButton btnSendFeedback;
	private JPanel panelVocabContri;
	private JLabel lblEmail_1;
	private JTextField txtEmail1;
	private JLabel lblTVng;
	private JButton btnSendVocabContri;
	private JTextField txtWord;
	private JTextField txtMeaning1;
	private JLabel lblMeaning;
	private JTextField txtMeaning2;
	private JTextField txtMeaning3;
	private JPanel panel;
	private JLabel lblImage;
	private JLabel lblImage2;

	private JPanel panelParent;
	private JPanel panelChild;
	public static PanelRespose getMyInstance() {
		if (myInstance == null) {
			myInstance = new PanelRespose();
		}
		return myInstance;
	}
	
	public PanelRespose() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setBounds(0, 0, 1042, 691);
		panelParent = new JPanel();
		panelParent.setBounds(0, 0, 1042, 691);
		panelParent.setBackground(new Color(255, 255, 255));
		panelParent.setLayout(new BorderLayout(0, 0));
		add(panelParent);
		
		panelChild = new JPanel();
		panelParent.add(panelChild);
		
		initComponentFeedback();
		initComponentVocabContri();
		
		panelChild.setPreferredSize(new Dimension(691, 1400));
		JScrollPane jsp = new JScrollPane(panelChild, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBorder(null);
		panelParent.add(jsp);
		
		txtEmail.setText(Authorization.email);
	}

	private void initComponentVocabContri() {
		panelChild.setLayout(null);
		panelVocabContri = new JPanel();
		panelVocabContri.setLayout(new BorderLayout());
		panelVocabContri.setBorder(BorderFactory.createLineBorder(new Color(37, 57, 111), 2));
		panelVocabContri.setBackground(Color.WHITE);
		panelVocabContri.setBounds(50, 30, 946, 595);
		panelChild.add(panelVocabContri);
		
		
		lblImage2 = new JLabel();
		lblImage2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/image/backgroundVocabContri.png")).getImage()
				.getScaledInstance(942, 591, Image.SCALE_SMOOTH)));
		panelVocabContri.add(lblImage2, BorderLayout.CENTER);
		
		
		lblEmail_1 = new JLabel("Email");
		lblEmail_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail_1.setBounds(191, 166, 84, 41);
		lblImage2.add(lblEmail_1);
		
		txtEmail1 = new JTextField();
		txtEmail1.setText((String) null);
		txtEmail1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail1.setBorder(null);
		txtEmail1.setColumns(10);
		txtEmail1.setBackground(new Color(217, 217, 217));
		txtEmail1.setBounds(279, 168, 383, 35);
		
		lblImage2.add(txtEmail1);
		
		lblTVng = new JLabel("Từ vựng");
		lblTVng.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTVng.setBounds(191, 236, 84, 41);
		lblImage2.add(lblTVng);
		
		btnSendVocabContri = new JButton("Gửi");
		btnSendVocabContri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBtnSendFeedback_1ActionPerformed(e);
			}
		});
		btnSendVocabContri.setForeground(Color.WHITE);
		btnSendVocabContri.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSendVocabContri.setBackground(new Color(37, 57, 111));
		btnSendVocabContri.setBounds(421, 530, 135, 35);
		lblImage2.add(btnSendVocabContri);
		
		txtWord = new JTextField();
		txtWord.setText((String) null);
		txtWord.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtWord.setColumns(10);
		txtWord.setBounds(279, 239, 383, 35);
		txtWord.setBorder(null);
		lblImage2.add(txtWord);
		
		txtMeaning1 = new JTextField();
		txtMeaning1.setText((String) null);
		txtMeaning1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMeaning1.setColumns(10);
		txtMeaning1.setBounds(279, 309, 383, 35);
		txtMeaning1.setBorder(null);
		lblImage2.add(txtMeaning1);
		
		lblMeaning = new JLabel("Ý Nghĩa");
		lblMeaning.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMeaning.setBounds(191, 306, 84, 41);
		lblImage2.add(lblMeaning);
		
		txtMeaning2 = new JTextField();
		txtMeaning2.setText((String) null);
		txtMeaning2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMeaning2.setColumns(10);
		txtMeaning2.setBounds(279, 379, 383, 35);
		txtMeaning2.setBorder(null);
		lblImage2.add(txtMeaning2);
		
		txtMeaning3 = new JTextField();
		txtMeaning3.setText((String) null);
		txtMeaning3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMeaning3.setColumns(10);
		txtMeaning3.setBounds(279, 449, 383, 35);
		txtMeaning3.setBorder(null);
		lblImage2.add(txtMeaning3);
		
		txtEmail1.setText(Authorization.email);
		txtEmail1.setEditable(false);
		
		txtEmail1.setText(Authorization.email);
		txtEmail1.setEditable(false);
	}

	private void initComponentFeedback() {
		panelChild.setLayout(null);
		panelContentFeedback = new JPanel();
		panelContentFeedback.setBackground(new Color(255, 255, 255));
		panelContentFeedback.setBounds(50, 700, 946, 595);
		panelContentFeedback.setBorder(BorderFactory.createLineBorder(new Color(37, 57, 111), 2));
		panelContentFeedback.setLayout(new BorderLayout(0, 0));
		panelChild.add(panelContentFeedback);
		
		lblImage = new JLabel();
		lblImage.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/image/backgroundFeedback.png")).getImage()
				.getScaledInstance(942, 591, Image.SCALE_SMOOTH)));
		panelContentFeedback.add(lblImage, BorderLayout.CENTER);
		
		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(190, 164, 84, 42);
		lblImage.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setBounds(278, 168, 285, 34);
		txtEmail.setBorder(null);
		txtEmail.setEditable(false);
		txtEmail.setBackground(new Color(217, 217, 217));
		txtEmail.setColumns(10);
		lblImage.add(txtEmail);

		panelContent = new JPanel();
		panelContent.setBackground(Color.BLACK);
		panelContent.setBounds(277, 247, 387, 170);
		panelContent.setLayout(new BorderLayout(0, 0));
		lblImage.add(panelContent);

		txtContent = new JTextArea();
		txtContent.setLineWrap(true);
		txtContent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtContent.setBackground(Color.WHITE);
		panelContent.add(txtContent);
		
		lblContent = new JLabel("Nội dung");
		lblContent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContent.setBounds(190, 242, 84, 42);
		lblImage.add(lblContent);
		
		btnSendFeedback = new JButton("Gửi");
		btnSendFeedback.setForeground(new Color(255, 255, 255));
		btnSendFeedback.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSendFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBtnSendFeedbackActionPerformed(e);
			}
		});
		btnSendFeedback.setBackground(new Color(37, 57, 111));
		btnSendFeedback.setBounds(421, 500, 100 , 35);
		lblImage.add(btnSendFeedback);
	}
	
	
	
	private boolean validateDataVocabContri() {
		if(txtWord.getText().equals("")) {
			ErrorMessage.ERROR_MESSAGES = "Từ vựng không được để trống!";
			return false;
		} else if(!Validation.checkLength(txtWord.getText(), 1, 200)) {
			ErrorMessage.ERROR_MESSAGES = "Từ vựng tối đa 200 ký tự!";
			return false;
		}
		
		if(!Validation.checkLength(txtMeaning1.getText(), 1, 50) ||
			!Validation.checkLength(txtMeaning2.getText(), 1, 50) ||
			!Validation.checkLength(txtMeaning2.getText(), 1, 50)) {
			ErrorMessage.ERROR_MESSAGES = "Ý nghĩa tối đa 50 ký tự!";
			return false;
		}
		return true;
	}
	
	private boolean validateDataFeedback() {
		if(txtContent.getText().equals("")) {
			ErrorMessage.ERROR_MESSAGES = "Nội dung không được để trống!";
			return false;
		} else if(!Validation.checkLength(txtContent.getText(), 1, 200)) {
			ErrorMessage.ERROR_MESSAGES = "Nội dung tối đa 200 ký tự!";
			return false;
		}
		return true;
	}

	protected void doBtnSendFeedbackActionPerformed(ActionEvent e) {
		if(validateDataFeedback()) {
			FeedbackDAO fbDAO = new FeedbackDAOImpl();
			int id = UserDAOImpl.getIdFromDbByAccount(Authorization.email);
			fbDAO.insert(new Feedback(txtContent.getText(), id));
			JOptionPane.showMessageDialog(this, "Cảm ơn bạn đã đóng góp ý kiến :)!");
			reset();
		} else {
			JOptionPane.showMessageDialog(this, ErrorMessage.ERROR_MESSAGES);
		}
	}
	protected void doBtnSendFeedback_1ActionPerformed(ActionEvent e) {
		if(validateDataVocabContri()) {
			VocabularyContributionDAO vcDAO = new VocabularyContributionDAOImpl();
			int id = UserDAOImpl.getIdFromDbByAccount(Authorization.email);
			StringBuilder meanings = new StringBuilder();
			if(!txtMeaning1.getText().equals("")) {
				meanings.append(StringUtils.formatVocab(txtMeaning1.getText()) + ";");
			}
			if(!txtMeaning2.getText().equals("")) {
				meanings.append(StringUtils.formatVocab(txtMeaning2.getText()) + ";");
			}
			if(!txtMeaning3.getText().equals("")) {
				meanings.append(StringUtils.formatVocab(txtMeaning3.getText()));
			}
			

			vcDAO.insert(new VocabularyContribution(StringUtils.formatVocab(txtWord.getText()), meanings.toString(), id));
			JOptionPane.showMessageDialog(this, "Cảm ơn bạn đã đóng góp từ vựng :)!");
			reset();
		} else {
			JOptionPane.showMessageDialog(this, ErrorMessage.ERROR_MESSAGES);
		}
	}
	
	private void reset() {
		txtContent.setText("");
		txtWord.setText("");
		txtMeaning1.setText("");
		txtMeaning2.setText("");
		txtMeaning3.setText("");
	}
}
