package admin.item;

import java.awt.Color;

import javax.swing.JPanel;

import entity.Category;
import entity.Meaning;
import entity.Vocabulary;
import helper.ImageUtils;
import helper.StringUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import admin.update.FrameUpdateVocab;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import dao.impl.CategoryDAOImpl;
import dao.impl.VocabularyDAOImpl;
import dao.impl.WordTypeDAOImpl;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemVocabDashboard extends JPanel {
	private JPanel panel;
	private JPanel panelHeader_1;
	private JPanel panel_1;
	private JLabel lblId;
	private JPanel panel_1_2;
	private JLabel lblWord;
	private JPanel panel_2_1;
	private JLabel lblMeaning;
	private JPanel panel_1_2_2;
	private JLabel lblWordType;
	private JPanel panel_2;
	private JLabel lblImage;
	private JPanel panel_1_2_1;
	private JLabel lblCategory;

	public ItemVocabDashboard(Vocabulary vocab, int y) {
		initComponent(vocab, y);
		lblId.setText(vocab.getId().toString());

		lblWord.setText(StringUtils.toCapitalize(vocab.getWord()));
		List<Meaning> means = new VocabularyDAOImpl().selectAllMeaningByVocabId(vocab.getId());
		String meansStr = means.stream().limit(4).map(mean -> StringUtils.toCapitalize(mean.getContent()))
				.collect(Collectors.joining("<br/>"));
		if (means.size() > 5) {
			meansStr += " ...";
		}

		lblMeaning.setText("<html>" + meansStr + "</html>");
		lblWordType.setText(new WordTypeDAOImpl().get(vocab.getWordTypeId()));
		Category cate = new CategoryDAOImpl().select(vocab.getCategoryId());
		lblCategory.setText((cate != null) ? cate.getName().toUpperCase() : "");
		lblImage.setIcon(ImageUtils.getImageByURL("vocabulary", vocab.getImage(), 63));
	}

	private void initComponent(Vocabulary vocab, int y) {
		setLayout(null);
		setBounds(0, y, 1005, 64);

		panel = new JPanel();
		panel.setBounds(0, 0, 1005, 1);
		add(panel);
		panel.setBackground(new Color(186, 123, 247));

		panelHeader_1 = new JPanel();
		panelHeader_1.setBounds(0, 0, 1005, 63);
		add(panelHeader_1);
		panelHeader_1.setLayout(new GridLayout(0, 6, 0, 0));

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panelHeader_1.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		lblId = new JLabel("STT");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setForeground(new Color(0, 0, 0));
		lblId.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1.add(lblId);

		panel_1_2 = new JPanel();
		panel_1_2.setBackground(new Color(255, 255, 255));
		panelHeader_1.add(panel_1_2);
		panel_1_2.setLayout(new BorderLayout(0, 0));

		lblWord = new JLabel("Từ vựng");
		lblWord.setHorizontalAlignment(SwingConstants.CENTER);
		lblWord.setForeground(new Color(0, 0, 0));
		lblWord.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1_2.add(lblWord);

		panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(255, 255, 255));
		panelHeader_1.add(panel_2_1);
		panel_2_1.setLayout(new BorderLayout(0, 0));

		lblMeaning = new JLabel("");
		lblMeaning.setHorizontalAlignment(SwingConstants.CENTER);
		lblMeaning.setForeground(new Color(0, 0, 0));
		lblMeaning.setFont(new Font("Arial", Font.PLAIN, 14));
		
		panel_2_1.add(lblMeaning);

		panel_1_2_2 = new JPanel();
		panel_1_2_2.setBackground(new Color(255, 255, 255));
		panelHeader_1.add(panel_1_2_2);
		panel_1_2_2.setLayout(new BorderLayout(0, 0));

		lblWordType = new JLabel();
		lblWordType.setHorizontalAlignment(SwingConstants.CENTER);
		lblWordType.setForeground(new Color(0, 0, 0));
		lblWordType.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1_2_2.add(lblWordType);
		

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panelHeader_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		lblImage = new JLabel();
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setForeground(new Color(0, 0, 0));
		lblImage.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_2.add(lblImage);

		panel_1_2_1 = new JPanel();
		panel_1_2_1.setBackground(new Color(255, 255, 255));
		panelHeader_1.add(panel_1_2_1);
		panel_1_2_1.setLayout(new BorderLayout(0, 0));

		lblCategory = new JLabel();
		lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategory.setForeground(new Color(0, 0, 0));
		lblCategory.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1_2_1.add(lblCategory);

		
	}

	

}
