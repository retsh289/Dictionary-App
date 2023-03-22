package admin.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import admin.gui.FrameDashboard;
import admin.item.ItemCard;
import admin.item.ItemVocabDashboard;
import dao.impl.CategoryDAOImpl;
import dao.impl.UserDAOImpl;
import dao.impl.VocabularyDAOImpl;
import entity.Vocabulary;
import helper.FrameUtils;
import home.gui.FrameSignIn;
import service.Authorization;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelDashboard extends JPanel {
	private JLabel lblDashboard;
	private JLabel lblBreadcrumb;
	private ItemCard cardVocab;
	private ItemCard cardCate;
	private ItemCard cardMember;
	private ItemCard cardAdmin;
	private JScrollPane scrollPane;
	private JPanel panel;
	
	private JPanel panelData;
	private VocabularyDAOImpl dao; // data
	private JLabel lblTop5;
	
	public FrameDashboard frameParent;

	private static PanelDashboard myInstance;
	
	public static PanelDashboard getMyInstance() {
		if (myInstance == null) {
			myInstance = new PanelDashboard();
		}
		return myInstance;
	}	
	public PanelDashboard() {
		dao = new VocabularyDAOImpl();
		initComponent();
		cardVocab.setData("dictionary.png", "Vocabulary" , new VocabularyDAOImpl().countNumberOfVocab().toString());
		cardCate.setData("category.png", "Category", new CategoryDAOImpl().countNumberOfCate().toString());
		cardMember.setData("user.png", "User", new UserDAOImpl().countNumberOfUser().toString());
		cardAdmin.setData("admin.png", "Admin", new UserDAOImpl().countNumberOfAdmin().toString());
		loadData();
	}

	private void initComponent() {
		setLayout(null);
		setBounds(0, 0, 1085, 699);
		setBackground(new Color(242, 247, 255));
		lblDashboard = new JLabel("Dashboard");
		lblDashboard.setForeground(new Color(37, 57, 111));
		lblDashboard.setFont(new Font("Arial", Font.BOLD, 20));
		lblDashboard.setBounds(43, 11, 178, 50);
		add(lblDashboard);
		if(Authorization.email!=null) {
			lblBreadcrumb = new JLabel(Authorization.email.toUpperCase());
		}else {
			lblBreadcrumb = new JLabel("Đăng Nhập".toUpperCase());
		}

		lblBreadcrumb.setFont(new Font("Arial", Font.BOLD, 18));
		lblBreadcrumb.setForeground(new Color(37, 57, 111));
		lblBreadcrumb.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBreadcrumb.setBounds(756, 20, 292, 39);
		lblBreadcrumb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblBreadcrumbMouseClicked(e);
			}

		});
		lblBreadcrumb.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblBreadcrumb);
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(43, 61, 1005, 200);
		panel.setLayout(new GridLayout(0, 4, 10, 10));
		add(panel);
		
		cardVocab = new ItemCard();
		cardVocab.setColor1(new Color(142, 142, 250));
		cardVocab.setColor2(new Color(123, 123, 245));
        panel.add(cardVocab);
        
        cardCate = new ItemCard();
        cardCate.setColor1(new Color(186, 123, 247));
        cardCate.setColor2(new Color(167, 94, 236));
        panel.add(cardCate);
        
        cardMember = new ItemCard();
        cardMember.setColor1(new Color(241, 208, 62));
        cardMember.setColor2(new Color(211, 184, 61));
        panel.add(cardMember);
        
        cardAdmin = new ItemCard();
        cardAdmin.setColor1(new Color(221, 62, 84));
        cardAdmin.setColor2(new Color(236, 47, 75));
        panel.add(cardAdmin);
        
        scrollPane = new JScrollPane();
		scrollPane.setForeground(new Color(0, 0, 0));
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(43, 318, 1005, 381);
		add(scrollPane);
		
		lblTop5 = new JLabel("5 Từ Vựng Mới Nhất");
		lblTop5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTop5MouseClicked(e);
			}
		});
		lblTop5.setForeground(new Color(37, 57, 111));
		lblTop5.setFont(new Font("Arial", Font.BOLD, 20));
		lblTop5.setBounds(43, 268, 498, 39);
		add(lblTop5);
		
        panelData = new JPanel();
        panelData.setLayout(null);
        panelData.setBackground(Color.WHITE);
	}
	
	private void printTitleComponent(JPanel panel) {
		JPanel panelHeader = new JPanel();
		panelHeader.setLayout(new GridLayout(0, 6, 0, 0));
		panelHeader.setBounds(0, 0, 1005, 40);
		panel.add(panelHeader);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0,154,206));
		panelHeader.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1.add(lblNewLabel);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBackground(new Color(0,154,206));
		panelHeader.add(panel_1_2);
		panel_1_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWord = new JLabel("Từ vựng");
		lblWord.setAlignmentY(Component.TOP_ALIGNMENT);
		lblWord.setHorizontalAlignment(SwingConstants.CENTER);
		lblWord.setForeground(Color.WHITE);
		lblWord.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1_2.add(lblWord);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(0,154,206));
		panelHeader.add(panel_2_1);
		panel_2_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMeaning = new JLabel("Ý nghĩa");
		lblMeaning.setHorizontalAlignment(SwingConstants.CENTER);
		lblMeaning.setForeground(Color.WHITE);
		lblMeaning.setFont(new Font("Arial", Font.BOLD, 14));
		panel_2_1.add(lblMeaning);
		
		JPanel panel_1_2_2 = new JPanel();
		panel_1_2_2.setBackground(new Color(0,154,206));
		panelHeader.add(panel_1_2_2);
		panel_1_2_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWordType = new JLabel("Loại từ");
		lblWordType.setHorizontalAlignment(SwingConstants.CENTER);
		lblWordType.setForeground(Color.WHITE);
		lblWordType.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1_2_2.add(lblWordType);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0,154,206));
		panelHeader.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblImage = new JLabel("Hình ảnh");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setForeground(Color.WHITE);
		lblImage.setFont(new Font("Arial", Font.BOLD, 14));
		panel_2.add(lblImage);
		
		JPanel panel_1_2_1 = new JPanel();
		panel_1_2_1.setBackground(new Color(0,154,206));
		panelHeader.add(panel_1_2_1);
		panel_1_2_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCategory = new JLabel("Chủ đề");
		lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategory.setForeground(Color.WHITE);
		lblCategory.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1_2_1.add(lblCategory);
	}
	
	public void loadData() {
		panelData.removeAll();
		int totalVocabs = dao.countNumberOfVocab();
		
		printTitleComponent(panelData);
		panelData.setPreferredSize(new Dimension(975, 5 * 70));
		scrollPane.setViewportView(panelData);
		int y = 40;
		for(Vocabulary vocab : dao.sel5LastVocab()) {
			ItemVocabDashboard vocabItem = new ItemVocabDashboard(vocab, y);			
			panelData.add(vocabItem);
			y = y + 67;
		}
	}
	protected void lblTop5MouseClicked(MouseEvent e) {
	}
	protected void lblBreadcrumbMouseClicked(MouseEvent e) {
		if(Authorization.email != null) {
			UserDAOImpl userDao = new UserDAOImpl();
			int id = UserDAOImpl.getIdFromDbByAccount(Authorization.email);
		}else {
			FrameSignIn login = new FrameSignIn();
			FrameUtils.alignFrameScreenCenter(login);
			login.setVisible(true);
		}
			
	}
}
