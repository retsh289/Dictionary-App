package home.item;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import entity.Category;
import helper.ImageUtils;
import helper.StringUtils;
import home.gui.FrameCategory;
import home.panel.PanelCategory;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class ItemCategory extends JPanel {
	private JLabel lblCategory;
	private JLabel lblImage;
	public PanelCategory panelParent;
	private Category cate;
	public ItemCategory(Category cate) {
		initComponent(cate);
		this.cate = cate;
		lblCategory.setText(StringUtils.toCapitalize(cate.getName() == null ? "" : cate.getName()));
		final int ROW_HEIGHT = 100;
		lblImage.setIcon(ImageUtils.getImageByURL("category", cate.getImageIcon(), ROW_HEIGHT));

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClick(e);
			}
		});
	}

	private void initComponent(Category cate) {
		setBackground(new Color(242, 247, 255));
		setLayout(null);
		setBounds(0, 0, 200, 200);
		setBorder(BorderFactory.createLineBorder(new Color(37, 57, 111), 2));

		lblCategory = new JLabel();
		lblCategory.setOpaque(true);
		lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategory.setFont(new Font("Arial", Font.BOLD, 16));
		lblCategory.setBounds(2, 148, 196, 50);
		lblCategory.setBackground(new Color(37, 57, 111));
		lblCategory.setForeground(new Color(255, 255, 255));
		add(lblCategory);

		lblImage = new JLabel();
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBounds(2, 24, 196, 100);
		add(lblImage);
	}

	protected void mouseClick(MouseEvent e) {
		FrameCategory fr = FrameCategory.getMyInstance(cate);
		fr.itemCate = this;
		fr.setVisible(true);
	}
}
