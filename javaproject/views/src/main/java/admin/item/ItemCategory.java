package admin.item;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import entity.Category;
import helper.FrameUtils;
import helper.ImageUtils;
import helper.StringUtils;
import service.CategoryService;
import service.UserService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import admin.gui.FrameCategory;
import admin.panel.PanelAdmin;
import admin.panel.PanelCategory;
import admin.update.FrameUpdateCategory;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemCategory extends JPanel {
	private JPanel panel;
	private JPanel panelHeader_1;
	private JPanel panel_1;
	private JLabel lblId;
	private JPanel panel_1_2;
	private JLabel lblWord;
	private JPanel panel_1_2_1;
	private JLabel lblImage;
	private JPanel panel_1_1_1_1;
	private JButton btnDetail;
	private JPanel panel_1_1_1;
	private JButton btnEdit;
	private JPanel panel_1_1;
	private JButton btnDelete;
	
	public PanelCategory panelParent;
	
	private Category cate;
	private CategoryService cateService;
	
	public ItemCategory(Category cate, int y) {
		this.cate = cate;
		initComponent(y);
		lblId.setText(cate.getId().toString());
		lblWord.setText(StringUtils.toCapitalize(cate.getName()));
		lblImage.setIcon(ImageUtils.getImageByURL("category", cate.getImageIcon(), 60));

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
		panelHeader_1.setLayout(new GridLayout(0, 6, 0, 0));

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panelHeader_1.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setForeground(new Color(0, 0, 0));
		lblId.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1.add(lblId);

		panel_1_2 = new JPanel();
		panel_1_2.setBackground(new Color(255, 255, 255));
		panelHeader_1.add(panel_1_2);
		panel_1_2.setLayout(new BorderLayout(0, 0));

		lblWord = new JLabel("Chủ đề");
		lblWord.setHorizontalAlignment(SwingConstants.CENTER);
		lblWord.setForeground(new Color(0, 0, 0));
		lblWord.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1_2.add(lblWord);
		

		
		panel_1_2_1 = new JPanel();
		panel_1_2_1.setBackground(new Color(255, 255, 255));
		panelHeader_1.add(panel_1_2_1);
		panel_1_2_1.setLayout(new BorderLayout(0, 0));
		
		lblImage = new JLabel();
		panel_1_2_1.add(lblImage, BorderLayout.CENTER);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setForeground(new Color(0, 0, 0));
		lblImage.setFont(new Font("Arial", Font.PLAIN, 14));

		panel_1_1_1_1 = new JPanel();
		panel_1_1_1_1.setLayout(null);
		panel_1_1_1_1.setBackground(Color.WHITE);
		panelHeader_1.add(panel_1_1_1_1);
		
		btnDetail = new JButton("Chi tiết");
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDetailActionPerformed(e);
			}
		});
		btnDetail.setForeground(Color.WHITE);
		btnDetail.setFont(new Font("Arial", Font.BOLD, 14));
		btnDetail.setBorder(null);
		btnDetail.setBackground(new Color(67, 98, 190));
		btnDetail.setBounds(38, 25, 100, 30);
		panel_1_1_1_1.add(btnDetail);
		
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
		btnEdit.setBounds(54, 25, 73, 30);
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
				btnDeleteActionPerformed(e, cate);
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
		btnDelete.setBorder(null);
		btnDelete.setBackground(new Color(205, 16, 64));
		btnDelete.setBounds(56, 25, 75, 30);
		panel_1_1.add(btnDelete);
	}
	
	protected void btnDetailActionPerformed(ActionEvent e) {
		FrameCategory detail = FrameCategory.getMyInstance(cate);
		detail.itemCate = this;
		detail.setVisible(true);
	}
	protected void btnEditActionPerformed(ActionEvent e) {
		FrameUpdateCategory fr = FrameUpdateCategory.getMyInstance(cate);
		fr.itemCate = this;
		fr.setVisible(true);
	}
	
	protected void btnDeleteActionPerformed(ActionEvent e, Category category) {
		int option = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa chủ đề này này?", "Xóa chủ đề", JOptionPane.YES_NO_OPTION);
		if(option == JOptionPane.YES_OPTION) {
			cateService = new CategoryService();
			if(cateService.delete(category)) {
				JOptionPane.showMessageDialog(this, "Xoá chủ đề thành công!");
				
				JPanel panel = this.panelParent.getPanel();
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				this.panelParent.loadData();
			}
		}
	}
}
