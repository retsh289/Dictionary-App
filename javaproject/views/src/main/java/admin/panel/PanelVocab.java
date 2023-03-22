package admin.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import admin.gui.FrameDashboard;
import admin.insert.FrameInsertMember;
import admin.insert.FrameInsertVocab;
import admin.item.ItemVocab;
import dao.impl.VocabularyDAOImpl;
import entity.Vocabulary;
import helper.FrameUtils;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Component;

public class PanelVocab extends JPanel {
	private JLabel lblStatusPage;
	private JLabel lblRowCount;
	private JScrollPane scrollPane;
	private JPanel panel;
	private VocabularyDAOImpl dao; // data
	
//	Controll data
	private Integer pageNumber;
	private Integer rowsOfPage;
	private Integer totalOfRows;
	private Integer totalPage;
	private JTextField txtPage;
	private JComboBox cbbNumberOfRows;
	
	public FrameDashboard frameParent;
	private static PanelVocab myInstance;
	private JTextField txtSearch;
	
	public static PanelVocab getMyInstance() {
		if (myInstance == null) {
			myInstance = new PanelVocab();
		}
		return myInstance;
	}
	
	
	public JPanel getPanel() {
		return panel;
	}
	
	public PanelVocab() {
		initComponent();
		dao = new VocabularyDAOImpl();
		pageNumber = 1;
		rowsOfPage = dao.countNumberOfVocab() > 10 ? 10 : dao.countNumberOfVocab();
		
		loadData();
	}


	private void initComponent() {
		setLayout(null);
		setBounds(0, 0, 1085, 699);
		setBackground(new Color(242, 247, 255));
		JLabel lblDashboard = new JLabel("Từ vựng");
		lblDashboard.setForeground(new Color(37, 57, 111));
		lblDashboard.setFont(new Font("Arial", Font.BOLD, 20));
		lblDashboard.setBounds(41, 11, 134, 39);
		add(lblDashboard);
		scrollPane = new JScrollPane();
		scrollPane.setForeground(new Color(0, 0, 0));
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(43, 120, 995, 448);
		add(scrollPane);
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		printTopPageComponent();
		printControllComponent();
	}


	private void printTopPageComponent() {
		JLabel lblBreadcrumb = new JLabel("Trang chủ / Từ vựng");
		lblBreadcrumb.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBreadcrumb.setBounds(904, 21, 134, 14);
		add(lblBreadcrumb);
		
		JButton btnAdd = new JButton("Thêm từ vựng");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnAdd_actionPerformed(e);
			}
		});
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setFont(new Font("Arial", Font.BOLD, 14));
		btnAdd.setBorder(null);
		btnAdd.setBackground(new Color(67, 98, 190));
		btnAdd.setBounds(891, 61, 147, 36);
		add(btnAdd);
	}

	private void printControllComponent() {
		lblStatusPage = new JLabel("Trang 1 of 0");
		lblStatusPage.setFont(new Font("Arial", Font.PLAIN, 12));
		lblStatusPage.setBounds(226, 642, 76, 39);
		add(lblStatusPage);

		lblRowCount = new JLabel("Số dòng: 0");
		lblRowCount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRowCount.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRowCount.setBounds(769, 642, 104, 27);
		add(lblRowCount);

		JButton btnFirst = new JButton("Trang đầu");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFirstActionPerformed(e);
			}
		});
		btnFirst.setForeground(new Color(255, 255, 255));
		btnFirst.setFont(new Font("Arial", Font.BOLD, 12));
		btnFirst.setBackground(new Color(67, 98, 190));
		btnFirst.setBounds(115, 591, 121, 40);
		add(btnFirst);

		JButton btnLast = new JButton("Trang cuối");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLastActionPerformed(e);
			}
		});
		btnLast.setForeground(new Color(255, 255, 255));
		btnLast.setFont(new Font("Arial", Font.BOLD, 12));
		btnLast.setBackground(new Color(67, 98, 190));
		btnLast.setBounds(853, 591, 121, 40);
		add(btnLast);

		JButton btnPrevious = new JButton("Trang trước");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPreviousActionPerformed(e);
			}
		});
		btnPrevious.setForeground(new Color(255, 255, 255));
		btnPrevious.setFont(new Font("Arial", Font.BOLD, 12));
		btnPrevious.setBackground(new Color(67, 98, 190));
		btnPrevious.setBounds(275, 591, 121, 40);
		add(btnPrevious);

		JButton btnNext = new JButton("Trang sau");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNextActionPerformed(e);
			}
		});
		btnNext.setForeground(new Color(255, 255, 255));
		btnNext.setFont(new Font("Arial", Font.BOLD, 12));
		btnNext.setBackground(new Color(67, 98, 190));
		btnNext.setBounds(693, 591, 121, 40);
		add(btnNext);

		cbbNumberOfRows = new JComboBox();
		cbbNumberOfRows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbbNumberOfRowsActionPerformed(e);
			}
		});
		cbbNumberOfRows.setFont(new Font("Arial", Font.BOLD, 12));
		cbbNumberOfRows.setModel(new DefaultComboBoxModel(new String[] { "10", "20", "50" }));
		cbbNumberOfRows.setBounds(435, 591, 220, 40);
		add(cbbNumberOfRows);
		
		txtPage = new JTextField();
		txtPage.setBounds(435, 645, 220, 40);
		txtPage.setColumns(10);
		txtPage.setText(new String("1"));
		txtPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPageActionPerformed(e);
			}
		});
		add(txtPage);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchVocab();
			}
		});
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSearch.setBounds(41, 61, 255, 36);
		add(txtSearch);
		txtSearch.setColumns(10);
		
	}

	private void printTitleComponent(JPanel panel) {
		JPanel panelHeader = new JPanel();
		panelHeader.setLayout(new GridLayout(0, 8, 0, 0));
		panelHeader.setBounds(-5, 0, 990, 40);
		panel.add(panelHeader);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(37, 57, 111));
		panelHeader.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1.add(lblNewLabel);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBackground(new Color(37, 57, 111));
		panelHeader.add(panel_1_2);
		panel_1_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWord = new JLabel("Từ vựng");
		lblWord.setAlignmentY(Component.TOP_ALIGNMENT);
		lblWord.setHorizontalAlignment(SwingConstants.CENTER);
		lblWord.setForeground(Color.WHITE);
		lblWord.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1_2.add(lblWord);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(37, 57, 111));
		panelHeader.add(panel_2_1);
		panel_2_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMeaning = new JLabel("Ý nghĩa");
		lblMeaning.setHorizontalAlignment(SwingConstants.CENTER);
		lblMeaning.setForeground(Color.WHITE);
		lblMeaning.setFont(new Font("Arial", Font.BOLD, 14));
		panel_2_1.add(lblMeaning);
		
		JPanel panel_1_2_2 = new JPanel();
		panel_1_2_2.setBackground(new Color(37, 57, 111));
		panelHeader.add(panel_1_2_2);
		panel_1_2_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWordType = new JLabel("Loại từ");
		lblWordType.setHorizontalAlignment(SwingConstants.CENTER);
		lblWordType.setForeground(Color.WHITE);
		lblWordType.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1_2_2.add(lblWordType);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(37, 57, 111));
		panelHeader.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblImage = new JLabel("Hình ảnh");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setForeground(Color.WHITE);
		lblImage.setFont(new Font("Arial", Font.BOLD, 14));
		panel_2.add(lblImage);
		
		JPanel panel_1_2_1 = new JPanel();
		panel_1_2_1.setBackground(new Color(37, 57, 111));
		panelHeader.add(panel_1_2_1);
		panel_1_2_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCategory = new JLabel("Chủ đề");
		lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategory.setForeground(Color.WHITE);
		lblCategory.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1_2_1.add(lblCategory);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(new Color(37, 57, 111));
		panelHeader.add(panel_1_1_1);
		panel_1_1_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblEdit = new JLabel("Sửa");
		lblEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblEdit.setForeground(Color.WHITE);
		lblEdit.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1_1_1.add(lblEdit);
		
		
		JPanel panel_1_1 = new JPanel();
		panelHeader.add(panel_1_1);
		panel_1_1.setBackground(new Color(37, 57, 111));
		panel_1_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblXa = new JLabel("Xóa");
		lblXa.setHorizontalAlignment(SwingConstants.CENTER);
		lblXa.setForeground(Color.WHITE);
		lblXa.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1_1.add(lblXa);
	}

	public void loadData() {
		totalOfRows = dao.countNumberOfVocab();
		totalPage = (int) Math.ceil((double)totalOfRows / rowsOfPage);
		lblStatusPage.setText("Trang " + pageNumber + " / " + totalPage);
		lblRowCount.setText("Số dòng: " + totalOfRows);
		
		
		final int ITEM_HEIGHT = 88;
		Integer tableHeigth = Math.min(ITEM_HEIGHT * rowsOfPage, ITEM_HEIGHT * totalOfRows);
		Dimension dim = new Dimension(975, tableHeigth);
		panel.setPreferredSize(dim);
		scrollPane.setViewportView(panel);
		
		panel.removeAll();
		printTitleComponent(panel);
		int y = 40;
		scrollPane.setViewportView(panel);
		for(Vocabulary vocab : dao.selectByPages(pageNumber, rowsOfPage)){
			ItemVocab vocabItem = new ItemVocab(vocab, y);	
			vocabItem.panelParent = this;
			panel.add(vocabItem);
			y = y + 84;
		}	
	}
	
	public void loadData(List<Vocabulary> vocabs) {
		totalOfRows = dao.countNumberOfVocab();
		totalPage = (int) Math.ceil((double)totalOfRows / rowsOfPage);
		lblStatusPage.setText("Trang " + pageNumber + " / " + totalPage);
		lblRowCount.setText("Số dòng: " + totalOfRows);
		
		
		final int ITEM_HEIGHT = 88;
		Integer tableHeigth = Math.min(ITEM_HEIGHT * rowsOfPage, ITEM_HEIGHT * totalOfRows);
		Dimension dim = new Dimension(975, tableHeigth);
		panel.setPreferredSize(dim);
		scrollPane.setViewportView(panel);
		
		panel.removeAll();
		printTitleComponent(panel);
		int y = 40;
		scrollPane.setViewportView(panel);
		for(Vocabulary vocab : vocabs){
			ItemVocab vocabItem = new ItemVocab(vocab, y);	
			vocabItem.panelParent = this;
			panel.add(vocabItem);
			y = y + 84;
		}
	}
	
	protected void do_btnAdd_actionPerformed(ActionEvent e) {
		FrameInsertVocab frame = FrameInsertVocab.getMyInstance();
		frame.panelParent = this;
		if(!frame.isVisible()) {
			FrameUtils.alignFrameScreenCenter(frame);
			frame.setVisible(true);
		}
	}
	
	public void searchVocab() {
		if(!txtSearch.getText().equals("")) {
			VocabularyDAOImpl dao = new VocabularyDAOImpl();
			List<Vocabulary> vocabs = dao.searchAll(txtSearch.getText());
			panel.repaint();
			panel.revalidate();
			loadData(vocabs);
		} else {
			loadData();
		}
	}
	
	protected void txtPageActionPerformed(ActionEvent e) {
		if(txtPage.getText() != null) {
			try {
				int page = Integer.parseInt(txtPage.getText());
				if(page >= 1 && page <= totalPage) {
					pageNumber = page;
					loadData();
				} else {
					JOptionPane.showMessageDialog(null, "Số trang chỉ được nhập từ 1 đến " + (int) Math.ceil(totalPage));
					txtPage.setText(pageNumber.toString());
				}
				
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(panel, "Chỉ được phép nhập số!");
			}
		} else {
			pageNumber = 1;
			loadData();
		}
		
	}
	protected void btnFirstActionPerformed(ActionEvent e) {
		pageNumber = 1;
		txtPage.setText(pageNumber.toString());
		loadData();
	}
	protected void btnPreviousActionPerformed(ActionEvent e) {
		if(pageNumber > 1) {
			pageNumber--;
			txtPage.setText(pageNumber.toString());
			loadData();
		}
	}
	protected void btnNextActionPerformed(ActionEvent e) {
		if(pageNumber < totalPage.intValue()) {
			pageNumber++;
			txtPage.setText(pageNumber.toString());
			loadData();
		}
	}
	protected void btnLastActionPerformed(ActionEvent e) {
		pageNumber = totalPage.intValue();
		txtPage.setText(pageNumber.toString());
		loadData();
	}
	protected void cbbNumberOfRowsActionPerformed(ActionEvent e) {
		if(dao != null) {
			pageNumber = 1;
			txtPage.setText(pageNumber.toString());
			rowsOfPage = Integer.parseInt(cbbNumberOfRows.getSelectedItem().toString());
			loadData();
		}
	}
	protected void doTxtSearchActionPerformed(ActionEvent e) {
		System.out.println(txtSearch.getText());
	}
}
