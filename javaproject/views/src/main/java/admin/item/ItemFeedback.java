package admin.item;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import admin.gui.FrameDetailFeedback;
import admin.insert.FrameInsertVocab;
import admin.panel.PanelFeedback;
import dao.impl.UserDAOImpl;
import entity.Feedback;
import service.FeedbackService;

import javax.swing.SwingConstants;

public class ItemFeedback extends JPanel {

	private JLabel lblEmail;

	private Feedback feedback;
	private FeedbackService fbService;
	private JButton btnDelete_1;

	public PanelFeedback panelParent;
	public ItemFeedback(Feedback feedback, int y) {
		this.feedback = feedback;
		initComponent(y);
	}

	private void initComponent(int y) {
		setLayout(null);
		setBounds(0, y, 995, 53);

		JPanel panel = new JPanel();
		panel.setBounds(0, 52, 995, 1);
		add(panel);
		panel.setBackground(new Color(238, 238, 238));

		JPanel panelHeader = new JPanel();
		panelHeader.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		panelHeader.setBounds(0, 0, 995, 50);
		add(panelHeader);
		panelHeader.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 60, 50);
		panel_1.setBackground(new Color(255, 255, 255));
		panelHeader.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblID = new JLabel("ID");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setForeground(new Color(37, 57, 111));
		lblID.setFont(new Font("Arial", Font.PLAIN, 14));
		lblID.setText(feedback.getId().toString());
		panel_1.add(lblID);

		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBounds(60, 0, 250, 50);
		panel_1_2.setBackground(new Color(255, 255, 255));
		panelHeader.add(panel_1_2);
		panel_1_2.setLayout(new BorderLayout(0, 0));

		lblEmail = new JLabel();
		lblEmail.setForeground(new Color(37, 57, 111));
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1_2.add(lblEmail);
		
		String email = new UserDAOImpl().select(feedback.getUserId()).getEmail();
		lblEmail.setText(email);

		JPanel panel_1_2_1 = new JPanel();
		panel_1_2_1.setBounds(310, 0, 460, 50);
		panel_1_2_1.setBackground(Color.WHITE);
		panelHeader.add(panel_1_2_1);
		panel_1_2_1.setLayout(new BorderLayout(0, 0));

		JLabel lblWord = new JLabel();
		lblWord.setText((String) null);
		lblWord.setForeground(new Color(37, 57, 111));
		lblWord.setFont(new Font("Arial", Font.PLAIN, 14));
		lblWord.setText(feedback.getContent());
		panel_1_2_1.add(lblWord);

		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBounds(770, 0, 120, 50);
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBackground(new Color(255, 255, 255));
		panelHeader.add(panel_1_1_1);

		JButton btnEdit = new JButton("Chi tiết");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnEdit_actionPerformed(e);
			}
		});
		btnEdit.setBorder(null);
		btnEdit.setForeground(new Color(255, 255, 255));
		btnEdit.setBackground(new Color(67, 98, 190));
		btnEdit.setFont(new Font("Arial", Font.BOLD, 14));
		btnEdit.setBounds(13, 11, 100, 30);
		panel_1_1_1.add(btnEdit);
		
		JPanel panel_1_1_1_1 = new JPanel();
		panel_1_1_1_1.setBounds(890, 0, 102, 50);
		panel_1_1_1_1.setLayout(null);
		panel_1_1_1_1.setBackground(Color.WHITE);
		panelHeader.add(panel_1_1_1_1);
		
		btnDelete_1 = new JButton("Xóa");
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBtnDelete_1ActionPerformed(e);
			}
		});
		btnDelete_1.setForeground(Color.WHITE);
		btnDelete_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnDelete_1.setBorder(null);
		btnDelete_1.setBackground(new Color(205, 16, 64));
		btnDelete_1.setBounds(25, 11, 60, 30);
		panel_1_1_1_1.add(btnDelete_1);
	}

	protected void do_btnEdit_actionPerformed(ActionEvent e) {
		FrameDetailFeedback fr = FrameDetailFeedback.getMyInstance(feedback);
		fr.setVisible(true);
	}
	protected void doBtnDelete_1ActionPerformed(ActionEvent e) {
		int option = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa phản hồi này?", "Xóa phản hồi",
				JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			fbService = new FeedbackService();
			if (fbService.delete(feedback)) {
				JOptionPane.showMessageDialog(this, "Xoá phản hồi thành công!");
				
				JPanel panel = this.panelParent.getPanel();
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				this.panelParent.loadData();
			}
		}
	}

}
