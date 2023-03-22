package admin.gui;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import admin.insert.FrameInsertMember;
import dao.impl.UserDAOImpl;
import entity.Feedback;
import helper.FrameUtils;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.awt.Panel;
import java.awt.TextArea;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FrameDetailFeedback extends JFrame {

	private JPanel contentPane;
	private JPanel panelContent;
	private Feedback fb;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JLabel lblNewLabel_1;
	private JScrollBar scrollBar;
	private JTextArea textContent;
	private JLabel lblContent;

	private static FrameDetailFeedback myInstance;
	public static FrameDetailFeedback getMyInstance(Feedback fb) {
		if (myInstance == null) {
			myInstance = new FrameDetailFeedback(fb);
		} else {
			myInstance.dispose();
			myInstance = new FrameDetailFeedback(fb);
		}
		return myInstance;
	}

	public FrameDetailFeedback(Feedback fb) {
		setBounds(100, 100, 768, 481);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("Email");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 60, 84, 35);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(104, 60, 638, 35);
		textField.setEditable(false);
		textField.setText(new UserDAOImpl().select(fb.getUserId()).getEmail());
		contentPane.add(textField);
		textField.setColumns(10);

		panelContent = new JPanel();
		panelContent.setBackground(Color.BLACK);
		panelContent.setBounds(104, 139, 638, 292);
		getContentPane().add(panelContent);
		panelContent.setLayout(new BorderLayout(0, 0));

		textContent = new JTextArea();
		textContent.setLineWrap(true);
		textContent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textContent.setBackground(Color.WHITE);
		textContent.setEditable(false);
		textContent.setText(fb.getContent());
		textContent.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		panelContent.add(textContent);
		
		lblContent = new JLabel("Content");
		lblContent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContent.setBounds(10, 139, 69, 35);
		contentPane.add(lblContent);
		
		FrameUtils.alignFrameScreenCenter(this);
	}
}
