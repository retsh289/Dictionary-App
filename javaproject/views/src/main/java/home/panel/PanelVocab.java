package home.panel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import dao.impl.VocabularyDAOImpl;
import entity.Vocabulary;
import home.gui.FrameHome;
import home.item.ItemVocab;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ScrollPaneConstants;

import admin.panel.PanelDashboard;

import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.Font;
import java.awt.GridLayout;

public class PanelVocab extends JPanel {
	private JPanel panel;
	private JTextField textSearch;
	private JPanel panelLeft;
	private JPanel panelParent;
	private JScrollPane scrollPane;
	private JPanel panelList;


	private ItemVocab item;
	private PanelDetailVocab panelDetailVocab;
	private List<Vocabulary> vocabs;
	public FrameHome frameParent;
	private static PanelVocab myInstance;

	public PanelDetailVocab getPanelDetailVocab() {
		return panelDetailVocab;
	}
	
	public static PanelVocab getMyInstance() {
		if (myInstance == null) {
			myInstance = new PanelVocab();
		}
		return myInstance;
	}

	public PanelVocab() {
		initComponent();

		VocabularyDAOImpl dao = new VocabularyDAOImpl();
		vocabs = dao.selectAll();

		loadData(vocabs);
	}
	
	public void loadData(List<Vocabulary> vocabs) {
		Integer y = 0;
		for (int i = 0; i < vocabs.size(); i++) {
			item = new ItemVocab(vocabs.get(i));
			item.setLocation(0, y);
			item.panelDetailVocab = panelDetailVocab;
			panelList.add(item);
			y = y + 29;
		}
		
		panelList.setPreferredSize(new Dimension(100, y));
		panelList.revalidate();
		panelList.repaint();
	}

	
	public void searchVocab() {
		panelList.removeAll();
		VocabularyDAOImpl dao = new VocabularyDAOImpl();
		List<Vocabulary> vocabs = dao.searchAll(textSearch.getText());
		loadData(vocabs);
	}

	private void initComponent() {
		setBorder(null);
		setBackground(new Color(37, 57, 111));
		setBounds(0, 0, 1302, 702);
		setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1292, 691);
		add(panel);
		panel.setLayout(null);

		panelLeft = new JPanel();
		panelLeft.setBackground(new Color(37, 57, 111));
		panelLeft.setBounds(0, 0, 265, 691);
		panel.add(panelLeft);
		panelLeft.setLayout(null);

		textSearch = new JTextField();
		textSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textSearch.setBounds(10, 10, 245, 40);
		panelLeft.add(textSearch);

		panelParent = new JPanel();
		panelParent.setBounds(10, 66, 245, 614);
		panelLeft.add(panelParent);
		panelParent.setLayout(new GridLayout(1, 0, 0, 0));

		scrollPane = new JScrollPane();
		panelParent.add(scrollPane);

		panelList = new JPanel();
		scrollPane.setViewportView(panelList);
		panelList.setLayout(null);
		textSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchVocab();
			}
		});


		Vocabulary vocab = new VocabularyDAOImpl().select(1);
		panelDetailVocab = PanelDetailVocab.getMyInstance(vocab);
		panelDetailVocab.setBounds(265, 0, 1027, 691);
		panel.add(panelDetailVocab);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(262, 0, 1030, 691);
		panel.add(lblNewLabel);
	}
}
