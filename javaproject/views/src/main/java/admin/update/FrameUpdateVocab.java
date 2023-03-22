package admin.update;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import admin.item.ItemMeaning;
import admin.item.ItemVocab;
import dao.VocabularyDAO;
import dao.impl.CategoryDAOImpl;
import dao.impl.MeaningDAOImpl;
import dao.impl.PhoneticDAOImpl;
import dao.impl.VocabularyDAOImpl;
import dao.impl.WordTypeDAOImpl;
import entity.Category;
import entity.Example;
import entity.Meaning;
import entity.Phonetic;
import entity.RelativeWord;
import entity.Vocabulary;
import helper.ErrorMessage;
import helper.FrameUtils;
import helper.ImageUtils;
import helper.StringUtils;
import jaco.mp3.player.MP3Player;
import service.CategoryService;
import service.UserService;
import service.VocabularyService;

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.DebugGraphics;
import java.awt.GridLayout;
import javax.swing.ScrollPaneConstants;

public class FrameUpdateVocab extends JFrame {
	private static String pronunciationURL = null;
	private static MP3Player mp3 = null;

	private VocabularyDAO vocabDAO;
	private VocabularyService vocabService;
	private Map<String, Object> data;
	private List<ItemMeaning> meaningItems;
	private ArrayList<HashMap<String, String>> meaningAndExamples;

	private final int PANEL_ITEM_MEANING_HEIGHT = 127;
	private int CURRENT_PANELS_MN_EX_HEIGHT = 345;
	private int HEIGHT_ADDED = 0;

	
	private Vocabulary vocab;
	public ItemVocab itemVocab;
	private static FrameUpdateVocab myInstance;
	
	public static FrameUpdateVocab getMyInstance(Vocabulary vocab) {
		if (myInstance == null) {
			myInstance = new FrameUpdateVocab(vocab);
		} else {
			myInstance.dispose();
			myInstance = new FrameUpdateVocab(vocab);
		}
		return myInstance;
	}
	
	public FrameUpdateVocab(Vocabulary vocab) {
		this.vocab = vocab;
		initComponent();
		meaningAndExamples = new ArrayList<>();
		data = new HashMap<>();
		data.put("id", Integer.toString(vocab.getId()));

		FrameUtils.alignFrameScreenCenter(this);

		loadData(vocab);
	}

	private void loadData(Vocabulary vocab) {
		vocabService = new VocabularyService();
		vocabDAO = new VocabularyDAOImpl();
		textWord.setText(vocab.getWord());
		comboWordType.setSelectedIndex(vocab.getWordTypeId() - 1);
		Category cate = new CategoryDAOImpl().select(vocab.getCategoryId());
		if (cate != null) {
			comboCategory.setSelectedItem(StringUtils.toCapitalize(cate.getName().toString()));
		}
		List<RelativeWord> relatives = vocabDAO.selectAllRelativesByVocabId(vocab.getId());
		String relativesStr = relatives.stream().map(rel -> rel.getWord().toString())
				.collect(Collectors.joining(";"));
		textRelatives.setText(relativesStr);

		List<Phonetic> phonetics = vocabDAO.selAllPhoneticByVocabId(vocab.getId());
		String phoneticsStr = phonetics.stream().map(ph -> ph.getContent().toString())
				.collect(Collectors.joining(";"));
		textPhonetic.setText(phoneticsStr);
		
		List<Meaning> meanings = vocabDAO.selectAllMeaningByVocabId(vocab.getId());
		
		int i = 0;
		if(meanings.size() >= 1) {
			for(Meaning mn : meanings) {
				if(StringUtils.formatWord(mn.getContent()) != "") {
					addItemMn();
					List<Example> examples = new MeaningDAOImpl().selectAllExampleByMeaningId(mn.getId());
					StringBuffer str = new StringBuffer();
					for (Example ex : examples) {
						if (!ex.getContent().equals("")) {
							str.append(ex.getContent().trim() + ";" + ex.getMeaning().trim() + "\n");
						}
					}
					meaningItems.get(i).setMeaningText(mn.getContent());;
					meaningItems.get(i).setExampleText(str.toString());
					i++;
				}
			}
			
			if(meanings.size() == 1) addItemMn();
		} else {
			addItemMn();
			addItemMn();
		}

		if (vocab.getImage() != null) {
			final int ROW_HEIGHT = 170;
			lblShowImage.setIcon(ImageUtils.getImageByURL("vocabulary", vocab.getImage(), ROW_HEIGHT));
		}

		if (vocab.getPronunciation() != null) {
			pronunciationURL = ImageUtils.pathToResource + "/pronunciation/" + vocab.getPronunciation();
		}
	}

	private void initComponent() {
		setResizable(false);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1129, 827);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		panelParent = new JPanel();
		panelParent.setBounds(0, 56, 1113, 637);
		panelParent.setLayout(new BorderLayout(0, 0));
		getContentPane().add(panelParent);

		panelChild = new JPanel();
		panelChild.setBackground(new Color(255, 255, 255));
		panelChild.setBounds(0, 56, 1113, 746);
		panelChild.setLayout(null);
		panelParent.add(panelChild);

		JScrollPane jspEx1 = new JScrollPane(panelChild, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelParent.add(jspEx1, BorderLayout.CENTER);

		lblNewLabel = new JLabel("Cập nhật từ vựng");
		lblNewLabel.setBounds(20, 11, 219, 34);
		lblNewLabel.setForeground(new Color(37, 57, 111));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(lblNewLabel);

		lblWord = new JLabel("Từ vựng");
		lblWord.setBounds(31, 30, 84, 21);
		lblWord.setForeground(Color.BLACK);
		lblWord.setFont(new Font("Arial", Font.PLAIN, 14));
		panelChild.add(lblWord);

		btnAdd = new JButton("Cập nhật");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddActionPerformed(e);
			}
		});
		btnAdd.setBounds(519, 724, 150, 44);
		btnAdd.setBackground(new Color(67, 98, 190));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setFont(new Font("Arial", Font.BOLD, 16));
		contentPane.add(btnAdd);

		lblWordType = new JLabel("Loại từ");
		lblWordType.setBounds(350, 21, 96, 21);
		lblWordType.setForeground(Color.BLACK);
		lblWordType.setFont(new Font("Arial", Font.PLAIN, 14));
		panelChild.add(lblWordType);

		comboWordType = new JComboBox<>();
		comboWordType.setBounds(445, 21, 173, 38);
		comboWordType.setBackground(new Color(255, 255, 255));
		comboWordType.setFont(new Font("Arial", Font.PLAIN, 14));
		panelChild.add(comboWordType);

		textWord = new JTextField();
		textWord.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textWord.setBounds(137, 21, 175, 37);
		panelChild.add(textWord);
		textWord.setColumns(10);

		panel = new JPanel();
		panel.setBounds(0, 0, 1289, 62);
		panel.setBackground(new Color(242, 247, 255));
		contentPane.add(panel);

		lblPronunciation = new JLabel("Phát âm");
		lblPronunciation.setBounds(31, 83, 96, 21);
		lblPronunciation.setForeground(Color.BLACK);
		lblPronunciation.setFont(new Font("Arial", Font.PLAIN, 14));
		panelChild.add(lblPronunciation);

		lblImage = new JLabel("Hình ảnh");
		lblImage.setBounds(350, 83, 96, 21);
		lblImage.setForeground(Color.BLACK);
		lblImage.setFont(new Font("Arial", Font.PLAIN, 14));
		panelChild.add(lblImage);

		btnImage = new JButton("Tải ảnh lên");
		btnImage.setBounds(445, 83, 175, 37);
		btnImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnImage_actionPerformed(e);
			}
		});
		btnImage.setForeground(new Color(0, 0, 0));
		btnImage.setFont(new Font("Arial", Font.BOLD, 14));
		btnImage.setBackground(new Color(242, 247, 255));
		panelChild.add(btnImage);

		btnPronunciation = new JButton(" Tải âm thanh lên");
		btnPronunciation.setBounds(137, 83, 175, 37);
		btnPronunciation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnPronunciation_actionPerformed(e);
			}
		});
		btnPronunciation.setForeground(new Color(0, 0, 0));
		btnPronunciation.setFont(new Font("Arial", Font.BOLD, 14));
		btnPronunciation.setBackground(new Color(242, 247, 255));
		panelChild.add(btnPronunciation);

		Border border = BorderFactory.createLineBorder(Color.BLUE, 1);

		textRelatives = new JTextField();
		textRelatives.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textRelatives.setColumns(10);
		textRelatives.setBounds(137, 207, 481, 37);
		panelChild.add(textRelatives);

		lblRelatives = new JLabel("Từ liên quan");
		lblRelatives.setForeground(Color.BLACK);
		lblRelatives.setFont(new Font("Arial", Font.PLAIN, 14));
		lblRelatives.setBounds(31, 206, 108, 34);
		panelChild.add(lblRelatives);

		lblCategory = new JLabel("Thể loại");
		lblCategory.setForeground(Color.BLACK);
		lblCategory.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCategory.setBounds(31, 146, 108, 37);
		panelChild.add(lblCategory);

		comboCategory = new JComboBox<String>();
		comboCategory.setFont(new Font("Arial", Font.PLAIN, 16));
		comboCategory.setBackground(Color.WHITE);
		comboCategory.setBounds(139, 145, 479, 38);
		panelChild.add(comboCategory);

		panel_1 = new JPanel();
		panel_1.setBounds(672, 20, 412, 39);
		panelChild.add(panel_1);

		btnPlaySound = new JButton("Play Sound");
		btnPlaySound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPlaySoundActionPerformed(e);
			}
		});
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		panel_1.add(btnPlaySound);

		btnStopSound = new JButton("Stop Sound");
		btnStopSound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStopSoundActionPerformed(e);
			}
		});
		panel_1.add(btnStopSound);

		btnPlus = new JButton("+");
		btnPlus.setBounds(37, 724, 44, 44);
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnPlus_actionPerformed(e);
			}
		});
		btnPlus.setForeground(Color.WHITE);
		btnPlus.setFont(new Font("Arial", Font.BOLD, 16));
		btnPlus.setBackground(new Color(67, 98, 190));
		contentPane.add(btnPlus);
		meaningItems = new ArrayList<>();

		JLabel lblPhonetic = new JLabel("Phiên âm");
		lblPhonetic.setForeground(Color.BLACK);
		lblPhonetic.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPhonetic.setBounds(31, 280, 108, 34);
		panelChild.add(lblPhonetic);

		textPhonetic = new JTextField();
		textPhonetic.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPhonetic.setColumns(10);
		textPhonetic.setBounds(137, 278, 481, 37);
		panelChild.add(textPhonetic);

		panelShowImage = new JPanel();
		panelShowImage.setBackground(new Color(255, 255, 255));
		panelShowImage.setBounds(672, 112, 412, 170);
		panelChild.add(panelShowImage);
		panelShowImage.setLayout(new BorderLayout(0, 0));

		lblShowImage = new JLabel("");
		lblShowImage.setHorizontalAlignment(SwingConstants.CENTER);
		panelShowImage.add(lblShowImage);
		lblShowImage.setBorder(border);
		WordTypeDAOImpl daoTypeWord = new WordTypeDAOImpl();
		CategoryDAOImpl cateDao = new CategoryDAOImpl();
		
		daoTypeWord.selectAll().forEach(pro -> comboWordType.addItem(pro.getType()));
		comboCategory.addItem(null);
		cateDao.selectAll().forEach(
				pro -> comboCategory.addItem(pro.getName().substring(0, 1).toUpperCase() + pro.getName().substring(1)));
		FrameUtils.alignFrameScreenCenter(this);
	}

	protected void do_btnPlus_actionPerformed(ActionEvent e) {
		addItemMn();
	}

	private void addItemMn() {
		ItemMeaning item = new ItemMeaning(CURRENT_PANELS_MN_EX_HEIGHT);
		CURRENT_PANELS_MN_EX_HEIGHT += PANEL_ITEM_MEANING_HEIGHT;
		panelChild.setPreferredSize(new Dimension(panelChild.getWidth(), 600 + HEIGHT_ADDED));
		HEIGHT_ADDED += PANEL_ITEM_MEANING_HEIGHT;
		panelChild.add(item);
		meaningItems.add(item);
	}

	protected void do_btnImage_actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser("desktop://");
		chooser.setDialogTitle("Hình ảnh");
		chooser.setFileFilter(new FileNameExtensionFilter("image(jpg, png, gif)", "jpg", "png", "gif"));
		chooser.setAcceptAllFileFilterUsed(false);

		int result = chooser.showOpenDialog(null);
		File f = null;
		if (result == chooser.APPROVE_OPTION) {
			f = chooser.getSelectedFile();
			final int ROW_HEIGHT = 171;
			lblShowImage.setIcon(ImageUtils.getImageByFile(f, ROW_HEIGHT));
			data.put("image", f.toPath().toString());
		}
	}

	protected void do_btnPronunciation_actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser("desktop://");
		chooser.setDialogTitle("Âm thanh");
		chooser.setFileFilter(new FileNameExtensionFilter("Sound (mp3)", "mp3"));
		chooser.setAcceptAllFileFilterUsed(false);
		int result = chooser.showOpenDialog(null);
		File f = null;
		if (result == chooser.APPROVE_OPTION) {
			f = chooser.getSelectedFile();
			this.pronunciationURL = f.getAbsolutePath();
			System.out.println(pronunciationURL);
			data.put("pronunciation", f.getAbsolutePath());
		}
	}

	protected void btnPlaySoundActionPerformed(ActionEvent e) {
		if (pronunciationURL == null) {
			JOptionPane.showMessageDialog(this, "Bạn chưa upload file phát âm");
		} else {
			mp3 = new MP3Player(new File(pronunciationURL));
			mp3.play();
		}
	}

	protected void btnAddActionPerformed(ActionEvent e) {
		data.put("word", textWord.getText());
		data.put("type", Integer.toString(comboWordType.getSelectedIndex() + 1));
		data.put("category", comboCategory.getSelectedItem() == null ? "" : comboCategory.getSelectedItem().toString());
		data.put("relatives", textRelatives.getText());
		data.put("phonetic", textPhonetic.getText());
		
		HashMap<String, String> mnEx;
		for(ItemMeaning item : meaningItems) {
			mnEx = new HashMap<>();
			mnEx.put("meaning", item.getMeaningText());
			mnEx.put("example", item.getExampleText());
			meaningAndExamples.add(mnEx);
		}
		data.put("meaningAndEx", meaningAndExamples);
		
		vocabService = new VocabularyService();
		if (vocabService.update(data)) {
			JOptionPane.showMessageDialog(this, "Cập nhật từ vựng thành công");
			dispose();
			
			JPanel panel = this.itemVocab.panelParent.getPanel();
			panel.repaint();
			panel.revalidate();
			this.itemVocab.panelParent.loadData();
		} else {
			JOptionPane.showMessageDialog(this, ErrorMessage.ERROR_MESSAGES);
		}

	}

	protected void btnStopSoundActionPerformed(ActionEvent e) {
		if (pronunciationURL == null) {
			JOptionPane.showMessageDialog(this, "Bạn chưa upload file phát âm");
		} else {
			mp3.stop();
		}
	}

	private JPanel contentPane;
	private JTextField textWord;
	private JLabel lblNewLabel;
	private JLabel lblWord;
	private JButton btnAdd;
	private JLabel lblWordType;
	private JPanel panel;
	private JLabel lblPronunciation;
	private JLabel lblImage;
	private JButton btnImage;
	private JButton btnPronunciation;
	private JPanel panelShowImage;
	private JLabel lblShowImage;
	private JTextField textRelatives;
	private JLabel lblRelatives;
	private JLabel lblCategory;
	private JComboBox<String> comboWordType;
	private JComboBox<String> comboCategory;
	private JButton btnPlaySound;
	private JPanel panel_1;
	private JButton btnStopSound;
	private JButton btnPlus;
	private JPanel panelParent;
	private JPanel panelChild;
	private JTextField textPhonetic;

}
