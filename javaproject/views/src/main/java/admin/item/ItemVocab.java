package admin.item;

import java.awt.Color;
import javax.swing.JPanel;
import entity.Category;
import entity.Meaning;
import entity.Vocabulary;
import helper.FrameUtils;
import helper.ImageUtils;
import helper.StringUtils;
import service.VocabularyService;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import admin.panel.PanelVocab;
import admin.update.FrameUpdateVocab;
import dao.impl.CategoryDAOImpl;
import dao.impl.VocabularyDAOImpl;
import dao.impl.WordTypeDAOImpl;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemVocab extends JPanel {

	private Vocabulary vocab;
	private VocabularyService vocabService;
	public	PanelVocab panelParent;
	

	public ItemVocab(Vocabulary vocab, int y) {
		this.vocab = vocab;
		
		initComponent(y);
		lblId.setText(vocab.getId().toString());
		lblWord.setText(StringUtils.toCapitalize(vocab.getWord()));
		lblImage.setIcon(ImageUtils.getImageByURL("vocabulary", vocab.getImage(), 78));
		List<Meaning> means = new VocabularyDAOImpl().selectAllMeaningByVocabId(vocab.getId());
		String meansStr = means.stream().limit(4)
				.map(mean -> StringUtils.toCapitalize(mean.getContent()))
				.collect(Collectors.joining("<br/>"));
		if (means.size() > 5) {
			meansStr += " ...";
		}
		lblMeaning.setText("<html>" + meansStr + "</html>");
		panel_2_1.add(lblMeaning);
		
		Category cate = new CategoryDAOImpl().select(vocab.getCategoryId());
		lblCategory.setText((cate != null) ? cate.getName().toUpperCase() : "");
		lblWordType.setText(new WordTypeDAOImpl().get(vocab.getWordTypeId()));
	}

	private void initComponent(int y) {
		setLayout(null);
		setBounds(0, y, 980, 80);

		panel = new JPanel();
		panel.setBounds(0, 77, 995, 3);
		add(panel);
		panel.setBackground(new Color(238, 238, 238));

		panelHeader_1 = new JPanel();
		panelHeader_1.setBounds(0, 0, 980, 80);
		add(panelHeader_1);
		panelHeader_1.setLayout(new GridLayout(1, 8, 0, 0));

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
		

		panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(new Color(255, 255, 255));
		panelHeader_1.add(panel_1_1_1);
		panel_1_1_1.setLayout(null);

		btnEdit = new JButton("Sửa");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditActionPerformed(e);
			}
		});
		btnEdit.setBounds(32, 25, 58, 30);
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Arial", Font.BOLD, 14));
		btnEdit.setBorder(null);
		btnEdit.setBackground(new Color(67, 98, 190));
		panel_1_1_1.add(btnEdit);

		panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(255, 255, 255));
		panelHeader_1.add(panel_1_1);

		btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteActionPerformed(e);
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
		btnDelete.setBorder(null);
		btnDelete.setBackground(new Color(205, 16, 64));
		btnDelete.setBounds(32, 25, 60, 30);
		panel_1_1.add(btnDelete);
	}
	
	protected void btnEditActionPerformed(ActionEvent e) {
		FrameUpdateVocab fr = new FrameUpdateVocab(vocab);
		fr.itemVocab = this;
		fr.setVisible(true);
	}

	protected void btnDeleteActionPerformed(ActionEvent e) {
		int option = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa từ vựng này?", "Xóa từ vựng",
				JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			vocabService = new VocabularyService();
			if (vocabService.delete(vocab)) {
				JOptionPane.showMessageDialog(this, "Xoá từ vựng thành công!");
				
				JPanel panel = this.panelParent.getPanel();
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				this.panelParent.loadData();;
			}
		}
	}
	
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
	private JPanel panel_1_1_1;
	private JButton btnEdit;
	private JPanel panel_1_1;
	private JButton btnDelete;
}
