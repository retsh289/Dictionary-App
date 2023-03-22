package admin.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import admin.insert.FrameInsertMember;
import admin.item.ItemUser;
import admin.item.ItemVocabContribution;
import dao.VocabularyContributionDAO;
import dao.impl.UserDAOImpl;
import dao.impl.VocabularyContributionDAOImpl;
import entity.User;
import entity.VocabularyContribution;
import helper.FrameUtils;

public class PanelVocabContribution extends JPanel{
	private JLabel lblStatusPage;
	private JLabel lblRowCount;	
	private JPanel panel;
	private JScrollPane scrollPane;
	private VocabularyContributionDAO dao; // data
	
//	Controll data
	private Integer pageNumber;
	private Integer rowsOfPage;
	private Integer totalOfRows;
	private Integer totalPage;
	private JTextField txtPage;
	private JComboBox cbbNumberOfRows;
	public PanelResponse panelParent;

	private static PanelVocabContribution myInstance;
	
	public static PanelVocabContribution getMyInstance() {
		if (myInstance == null) {
			myInstance = new PanelVocabContribution();
		}
		return myInstance;
	}
	
	public PanelVocabContribution() {
		initComponent();
		dao = new VocabularyContributionDAOImpl();
		pageNumber = 1;
		rowsOfPage = dao.countVocabContri() > 10 ? 10 : dao.countVocabContri();
		
		loadData();
	}
	private void initComponent() {
		setLayout(null);
		setBounds(0, 0, 1085, 668);
		setBackground(new Color(242, 247, 255));
		JLabel lblDashboard = new JLabel("Từ vựng đóng góp");
		lblDashboard.setForeground(new Color(37, 57, 111));
		lblDashboard.setFont(new Font("Arial", Font.BOLD, 20));
		lblDashboard.setBounds(41, 11, 220, 39);
		add(lblDashboard);
		scrollPane = new JScrollPane();
		scrollPane.setForeground(new Color(0, 0, 0));
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(41, 61, 995, 448);
		add(scrollPane);
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		printTopPageComponent();
		printControllComponent();
	}
	private void printTopPageComponent() {
		JLabel lblBreadcrumb = new JLabel("Trang chủ / Từ vựng đóng góp");
		lblBreadcrumb.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBreadcrumb.setBounds(849, 21, 189, 14);
		add(lblBreadcrumb);
	}
	
	private void printTitleComponent(JPanel panel) {
		JPanel panelHeader = new JPanel();
		panelHeader.setBounds(0, 0, 995, 40);
		panel.add(panelHeader);
		panelHeader.setLayout(new GridLayout(0, 6, 0, 0));
		
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
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1_2.add(lblEmail);
		
		JPanel panel_1_3 = new JPanel();
		panel_1_3.setBackground(new Color(37, 57, 111));
		panelHeader.add(panel_1_3);
		panel_1_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Từ vựng");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1_3.add(lblNewLabel_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(37, 57, 111));
		panelHeader.add(panel_1_1);
		panel_1_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblXa = new JLabel("Nghĩa");
		lblXa.setHorizontalAlignment(SwingConstants.CENTER);
		lblXa.setForeground(Color.WHITE);
		lblXa.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1_1.add(lblXa);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(new Color(37, 57, 111));
		panelHeader.add(panel_1_1_1);
		panel_1_1_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSa = new JLabel("Thêm");
		lblSa.setHorizontalAlignment(SwingConstants.CENTER);
		lblSa.setForeground(Color.WHITE);
		lblSa.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1_1_1.add(lblSa);
		
		JPanel panel_1_1_1_1 = new JPanel();
		panel_1_1_1_1.setBackground(new Color(37, 57, 111));
		panelHeader.add(panel_1_1_1_1);
		panel_1_1_1_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblXa_1 = new JLabel("Xóa");
		lblXa_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblXa_1.setForeground(Color.WHITE);
		lblXa_1.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1_1_1_1.add(lblXa_1);
	}
	
	private void printControllComponent() {
		lblStatusPage = new JLabel("Trang 1 of 0");
		lblStatusPage.setFont(new Font("Arial", Font.PLAIN, 12));
		lblStatusPage.setBounds(221, 600, 76, 39);
		add(lblStatusPage);

		lblRowCount = new JLabel("Số dòng: 0");
		lblRowCount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRowCount.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRowCount.setBounds(764, 600, 104, 27);
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
		btnFirst.setBounds(110, 549, 121, 40);
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
		btnLast.setBounds(848, 549, 121, 40);
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
		btnPrevious.setBounds(270, 549, 121, 40);
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
		btnNext.setBounds(688, 549, 121, 40);
		add(btnNext);

		cbbNumberOfRows = new JComboBox();
		cbbNumberOfRows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbbNumberOfRowsActionPerformed(e);
			}
		});
		cbbNumberOfRows.setFont(new Font("Arial", Font.BOLD, 12));
		cbbNumberOfRows.setModel(new DefaultComboBoxModel(new String[] { "10", "20", "50" }));
		cbbNumberOfRows.setBounds(430, 549, 220, 40);
		add(cbbNumberOfRows);
		
		txtPage = new JTextField();
		txtPage.setBounds(430, 603, 220, 40);
		txtPage.setColumns(10);
		txtPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPageActionPerformed(e);
			}
		});
		add(txtPage);
		
	}
	
	
	public void loadData() {
		totalOfRows = dao.countVocabContri();
		totalPage = (int) Math.ceil((double) totalOfRows / rowsOfPage);
		lblStatusPage.setText("Trang " + pageNumber + " / " + totalPage);
		lblRowCount.setText("Số dòng: " + totalOfRows);
		
		final int ITEM_HEIGHT = 63;
		Integer tableHeigth = Math.min(ITEM_HEIGHT * rowsOfPage, ITEM_HEIGHT * totalOfRows);
		Dimension dim = new Dimension(975, tableHeigth);
		panel.setPreferredSize(dim);
		scrollPane.setViewportView(panel);
		
		panel.removeAll();
		printTitleComponent(panel);

		int y = 40;		
		for(VocabularyContribution vc : dao.selectByPages(pageNumber, rowsOfPage)){
			ItemVocabContribution vcItem = new ItemVocabContribution(vc, y);
			vcItem.panelParent = this;
			panel.add(vcItem);
			y = y + 60;
		}	
	}
	
	public JPanel getPanel() {
		return panel;
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
		if(pageNumber < totalPage) {
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
}
