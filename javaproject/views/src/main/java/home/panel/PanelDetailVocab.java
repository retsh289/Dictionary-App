package home.panel;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dao.BookmarkDAO;
import dao.UserDAO;
import dao.VocabularyDAO;
import dao.impl.BookmarkDAOImpl;
import dao.impl.CategoryDAOImpl;
import dao.impl.MeaningDAOImpl;
import dao.impl.PhoneticDAOImpl;
import dao.impl.UserDAOImpl;
import dao.impl.VocabularyDAOImpl;
import dao.impl.WordTypeDAOImpl;
import entity.Bookmark;
import entity.Category;
import entity.Example;
import entity.Meaning;
import entity.Phonetic;
import entity.RelativeWord;
import entity.Vocabulary;
import helper.IconImage;
import helper.ImageUtils;
import helper.StringUtils;
import home.gui.FrameCategory;
import jaco.mp3.player.MP3Player;
import service.Authorization;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class PanelDetailVocab extends JPanel {

	private JPanel contentPane;

	private JLabel lblPhonetics;
	private JLabel lblRelatives;
	private JLabel lblRelativesTitle;

	private JLabel lblImage;
	private JLabel lblImageCategory;
	private JLabel lblBackground;

	private JLabel lblPronunciation;
	private JToggleButton tglbtnNewToggleButton;
	private JLabel lblWord;
	private JLabel lblWordType;
	private JTextArea textArea;
	private JLabel lblCategory;
	private JScrollPane scroll;
	private JPanel panel;
	
	private JLabel hyperlink;
	private Vocabulary vocab;

	private PanelVocab panelVocab;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_3;
	private static String googleSearchURI;
	private BookmarkDAO bmDAO;
	private UserDAO userDAO;
	private VocabularyDAO vocabDAO;
	
	private static String pronunciation;

	private static PanelDetailVocab myInstance;

	public static PanelDetailVocab getMyInstance(Vocabulary vocab) {
		if (myInstance == null) {
			myInstance = new PanelDetailVocab(vocab);
		} else {
			myInstance.setData(vocab);
		}
		return myInstance;
	}

	public PanelDetailVocab(Vocabulary vocab) {
		this.vocab = vocab;
		bmDAO = new BookmarkDAOImpl();
		userDAO = new UserDAOImpl();
		vocabDAO = new VocabularyDAOImpl();
		initComponent();

		setData(vocab);
	}

	public void setData(Vocabulary vocab) {
		pronunciation = vocab.getPronunciation();
		this.vocab = vocab;
		
		lblWord.setText(StringUtils.toCapitalize(vocab.getWord()));
		lblWordType.setText(new WordTypeDAOImpl().get(vocab.getWordTypeId()));

		lblPhonetics.setText(vocabDAO.selAllPhoneticByVocabId(vocab.getId())
						.stream().map(ph -> ph.getContent())
							.collect(Collectors.joining(", ")));
		
		lblRelatives.setText("<html>" + vocabDAO.selectAllRelativesByVocabId(vocab.getId())
				.stream().map(rel -> StringUtils.toCamelCase(rel.getWord()))
					.collect(Collectors.joining("<br/>")) + "</html>");
		
		
		final int ROW_HEIGHT = 160;
		lblImage.setIcon(ImageUtils.getImageByURL("vocabulary", vocab.getImage(), ROW_HEIGHT));
		
		Category cate = new CategoryDAOImpl().select(vocab.getCategoryId());
		if (cate != null) {
			lblCategory.setText("Thể loại: " + StringUtils.toCapitalize(cate.getName()));
			final int ROW_HEIGHT_CATEGORY = 75;
			lblImageCategory.setIcon(ImageUtils.getImageByURL("category", cate.getImageIcon(), ROW_HEIGHT_CATEGORY));
		}
		
		List<Meaning> meanings = vocabDAO.selectAllMeaningByVocabId(vocab.getId());
		StringBuilder txt = new StringBuilder();
		
		List<Example> examples;
		for (Meaning mn : meanings) {
			if (!mn.getContent().isEmpty()) {
				txt.append(StringUtils.toCamelCase(mn.getContent()) + "\n");
				
				examples = new MeaningDAOImpl().selectAllExampleByMeaningId(mn.getId());
				
				for (Example ex : examples) {
					if (!ex.getContent().isEmpty()) {
						txt.append("    ");
						if(!ex.getMeaning().isEmpty()) {
							txt.append(ex.getContent() + "\n    =>");
							txt.append(ex.getMeaning());
						}
					}
					txt.append("\n");
				}
				txt.append("\n");
			}
		}
		textArea.setText(txt.toString());
		this.googleSearchURI = createGoogleHyperlink(vocab.getWord());
		hyperlink.setText("Search Google: " + vocab.getWord());
		
		bookmarkFeature();
	}

	private void bookmarkFeature() {
		IconImage icon = new IconImage();
		// set Icon
		try {
			if (new BookmarkDAOImpl().checkExistBookmarkInDb(userDAO.selectIdByUserEmail(Authorization.email),
					vocab.getId()) == null) {
				tglbtnNewToggleButton.setIcon((new ImageIcon(icon.getStarAltImg())));
			} else {
				tglbtnNewToggleButton.setIcon(new ImageIcon(icon.getStarImg()));
			}

		} catch (Exception e2) {
		}

		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (((JToggleButton) e.getSource()).isSelected() == true) {
					if (Authorization.email != null) {
						if (bmDAO.checkExistBookmark(userDAO.selectIdByUserEmail(Authorization.email),
								vocab.getId()) == null) {
							bmDAO.insert(new Bookmark(vocab.getId(), userDAO.selectIdByUserEmail(Authorization.email)));
						}
						tglbtnNewToggleButton.setIcon(new ImageIcon(icon.getStarImg()));
					}
				} else if (((JToggleButton) e.getSource()).isSelected() == false) {
					if (Authorization.email != null) {
						List<Bookmark> x = bmDAO.checkExistBookmark(userDAO.selectIdByUserEmail(Authorization.email),
								vocab.getId());
						if (x != null) {
							x.forEach(y -> bmDAO.delete(y));
						}
						tglbtnNewToggleButton.setIcon((new ImageIcon(icon.getStarAltImg())));

					}
				}

			}
		});
	}

	private void initComponent() {
		setBounds(0, 0, 1027, 691);
		setBackground(new Color(255, 255, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout());

		lblBackground = new JLabel();
		lblBackground.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/image/backgroundVocab.png"))
				.getImage().getScaledInstance(1027, 691, Image.SCALE_SMOOTH)));
		add(lblBackground);

		lblWord = new JLabel();
		lblWord.setFont(new Font("Arial", Font.BOLD, 30));
		lblWord.setBackground(new Color(255, 255, 255));
		lblWord.setForeground(Color.black);
		lblWord.setBounds(158, 106, 333, 50);
		lblBackground.add(lblWord);
//		
		lblWordType = new JLabel();
		lblWordType.setFont(new Font("Arial", Font.PLAIN, 16));
		lblWordType.setBounds(142, 210, 362, 31);
		lblBackground.add(lblWordType);
//		
		lblPhonetics = new JLabel("");
		lblPhonetics.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPhonetics.setBounds(142, 173, 359, 20);
		lblBackground.add(lblPhonetics);
//		
		lblImage = new JLabel();
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setIcon(new ImageIcon());
		lblImage.setFont(new Font("Arial", Font.PLAIN, 14));
		lblImage.setBounds(611, 166, 307, 160);
		lblBackground.add(lblImage);
		
		
		lblRelativesTitle = new JLabel("Từ liên quan");
		lblRelativesTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelativesTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRelativesTitle.setBounds(792, 359, 126, 23);
		lblBackground.add(lblRelativesTitle);
		
		
		lblRelatives = new JLabel();
		lblRelatives.setHorizontalAlignment(SwingConstants.LEFT);
		lblRelatives.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRelatives.setBounds(794, 390, 123, 85);
		lblBackground.add(lblRelatives);
//		
		lblPronunciation = new JLabel("");
		lblPronunciation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				soundMouseClick(e);
			}
		});

		lblPronunciation.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/image/sound.png")).getImage()
				.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
		lblPronunciation.setBounds(538, 189, 40, 40);
		lblBackground.add(lblPronunciation);
//			
		tglbtnNewToggleButton = new JToggleButton("");
		tglbtnNewToggleButton.setBorderPainted(false);
		tglbtnNewToggleButton.setContentAreaFilled(false);
		tglbtnNewToggleButton.setBorder(null);
		tglbtnNewToggleButton.setBorderPainted(false);
		tglbtnNewToggleButton.setBounds(862, 89, 46, 46);
		lblBackground.add(tglbtnNewToggleButton);
//		 
		lblCategory = new JLabel();
		lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategory.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCategory.setBounds(611, 364, 173, 25);
		lblBackground.add(lblCategory);
//

		lblImageCategory = new JLabel();
		lblImageCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageCategory.setIcon(new ImageIcon());
		lblImageCategory.setFont(new Font("Arial", Font.PLAIN, 14));
		lblImageCategory.setBounds(611, 390, 174, 75);
		
		lblBackground.add(lblImageCategory);

		panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(136, 288, 441, 280);
		panel.setLayout(new BorderLayout());
		lblBackground.add(panel);
//
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setDisabledTextColor(new Color(0, 0, 0));
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textArea.setEditable(false);

		scroll = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(textArea);
		scroll.setBorder(null);
		panel.add(scroll);
		
		hyperlink = new JLabel();
		hyperlink.setHorizontalAlignment(SwingConstants.CENTER);
		hyperlink.setFont(new Font("Tahoma", Font.PLAIN, 16));
		hyperlink.setBounds(609, 540, 308, 23);
		hyperlink.setText("ABC");
		lblBackground.add(hyperlink);
		hyperlink.addMouseListener(new MouseAdapter() {
			 
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(googleSearchURI));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
 
            @Override
            public void mouseExited(MouseEvent e) {
            	hyperlink.setText("Search Google: " + vocab.getWord());
            }
 
            @Override
            public void mouseEntered(MouseEvent e) {
            	hyperlink.setText("<html><a href=''>" + "Search Google: " + vocab.getWord() + "</a></html>");
            }
 
        });
	}
	
	private String createGoogleHyperlink(String word) {
		StringBuilder s = new StringBuilder();
		s.append("https://www.google.com/search?q=%5B");
		s.append(word);
		s.append("%5D+ngh%C4%A9a+ti%E1%BA%BFng+vi%E1%BB%87t+l%C3%A0+g%C3%AC");
		return s.toString();
	}

	protected void soundMouseClick(MouseEvent e) {
		if (vocab.getPronunciation() != null) {
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
