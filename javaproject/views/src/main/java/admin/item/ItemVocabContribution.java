package admin.item;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import admin.insert.FrameInsertVocab;
import admin.panel.PanelVocabContribution;
import admin.update.FrameUpdateMember;
import dao.impl.UserDAOImpl;
import entity.User;
import entity.VocabularyContribution;
import helper.ErrorMessage;
import helper.StringUtils;
import service.UserService;
import service.VocabularyContributionService;
import service.VocabularyService;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class ItemVocabContribution extends JPanel {

	private JLabel lblEmail;

	private VocabularyContribution vocabContribution;
	private VocabularyContributionService vocabService;
	private JButton btnDelete_1;

	public PanelVocabContribution panelParent;
	
	public ItemVocabContribution(VocabularyContribution vocabContri, int y) {
		this.vocabContribution = vocabContri;
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
		panelHeader.setLayout(new GridLayout(0, 6, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panelHeader.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblID = new JLabel("ID");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setForeground(new Color(37, 57, 111));
		lblID.setFont(new Font("Arial", Font.PLAIN, 14));
		lblID.setText(vocabContribution.getId().toString());
		panel_1.add(lblID);

		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBackground(new Color(255, 255, 255));
		panelHeader.add(panel_1_2);
		panel_1_2.setLayout(new BorderLayout(0, 0));

		lblEmail = new JLabel();
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(new Color(37, 57, 111));
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1_2.add(lblEmail);
		
		String email = new UserDAOImpl().select(vocabContribution.getUserId()).getEmail();
		lblEmail.setText(email);

		JPanel panel_1_2_1 = new JPanel();
		panel_1_2_1.setBackground(Color.WHITE);
		panelHeader.add(panel_1_2_1);
		panel_1_2_1.setLayout(new BorderLayout(0, 0));

		JLabel lblWord = new JLabel();
		lblWord.setHorizontalAlignment(SwingConstants.CENTER);
		lblWord.setText(StringUtils.toCapitalize(vocabContribution.getWord()));
		lblWord.setForeground(new Color(37, 57, 111));
		lblWord.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1_2_1.add(lblWord);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(255, 255, 255));
		panelHeader.add(panel_1_1);
		
		JLabel lblMeaning = new JLabel();
		List<String> mnStr = Arrays.asList(vocabContribution.getMeaning());
		mnStr.stream().map(mn -> StringUtils.toCapitalize(mn.trim()))
						.collect(Collectors.joining(" ,"));	
		lblMeaning.setText(vocabContribution.getMeaning());
		lblMeaning.setForeground(new Color(37, 57, 111));
		lblMeaning.setFont(new Font("Arial", Font.PLAIN, 14));
		lblMeaning.setBounds(0, 0, 165, 50);
		panel_1_1.add(lblMeaning);

		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBackground(new Color(255, 255, 255));
		panelHeader.add(panel_1_1_1);

		JButton btnEdit = new JButton("Thêm");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnEdit_actionPerformed(e);
			}
		});
		btnEdit.setBorder(null);
		btnEdit.setForeground(new Color(255, 255, 255));
		btnEdit.setBackground(new Color(67, 98, 190));
		btnEdit.setFont(new Font("Arial", Font.BOLD, 14));
		btnEdit.setBounds(55, 11, 60, 30);
		panel_1_1_1.add(btnEdit);
		
		JPanel panel_1_1_1_1 = new JPanel();
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
		btnDelete_1.setBounds(60, 11, 60, 30);
		panel_1_1_1_1.add(btnDelete_1);
	}

	protected void do_btnEdit_actionPerformed(ActionEvent e) {
		FrameInsertVocab fr = FrameInsertVocab.getMyInstance();
		fr.itemVocabContri = this;
		fr.setTextWordAndMeanings(vocabContribution);
		fr.setVisible(true);
	}
	protected void doBtnDelete_1ActionPerformed(ActionEvent e) {
		int option = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa từ vựng này?", "Xóa từ vựng",
				JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			vocabService = new VocabularyContributionService();
			if (vocabService.delete(vocabContribution)) {
				JOptionPane.showMessageDialog(this, "Xoá từ vựng thành công!");
				JPanel panel = this.panelParent.getPanel();
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				this.panelParent.loadData();
			}
		}
	}
}