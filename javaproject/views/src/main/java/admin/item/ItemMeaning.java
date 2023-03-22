package admin.item;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ItemMeaning extends JPanel {
	private JTextField textMeaning;
	private Panel panelMeaning2;
	private Panel panelExample2;
	private JTextArea textExample;
	
	public ItemMeaning(int y) {
		initComponent(y);
	}

	private void initComponent(int y) {
		setBackground(Color.white);
		setBounds(31, y, 1054, 127);
		setLayout(null);
		
		panelMeaning2 = new Panel();
		panelMeaning2.setBackground(Color.BLACK);
		panelMeaning2.setBounds(106, 0, 362, 37);
		add(panelMeaning2);
		panelMeaning2.setLayout(new BorderLayout(0, 0));
		
		textMeaning = new JTextField();
		textMeaning.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textMeaning.setColumns(10);
		panelMeaning2.add(textMeaning);
		
		panelExample2 = new Panel();
		panelExample2.setBackground(new Color(255, 255, 255));
		panelExample2.setBounds(592, 0, 462, 102);
		add(panelExample2);
		panelExample2.setLayout(new BorderLayout(0, 0));
		
		textExample = new JTextArea();
		textExample.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textExample.setBackground(Color.WHITE);
		panelExample2.add(textExample);
		
		JScrollPane jspEx1 = new JScrollPane(textExample, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelExample2.add(jspEx1);
		
		JLabel lblMeaning = new JLabel("Ý nghĩa");
		lblMeaning.setFont(new Font("Arial", Font.PLAIN, 14));
		lblMeaning.setBounds(0, 0, 92, 14);
		add(lblMeaning);
		
		JLabel lblVD = new JLabel("Ví dụ");
		lblVD.setFont(new Font("Arial", Font.PLAIN, 14));
		lblVD.setBounds(507, 0, 54, 14);
		add(lblVD);
	}
	
	public String getMeaningText() {
		return textMeaning.getText();
	}
	
	public String getExampleText() {
		return textExample.getText();
	}
	
	public void setMeaningText(String str) {
		textMeaning.setText(str);
	}
	
	public void setExampleText(String str) {
		textExample.setText(str);
	}
	
}
