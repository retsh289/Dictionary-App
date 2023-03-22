package admin.update;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import admin.item.ItemCategory;
import admin.item.ItemVocab;
import admin.panel.PanelAdmin;
import dao.impl.CategoryDAOImpl;
import entity.Category;
import helper.ErrorMessage;
import helper.FrameUtils;
import helper.ImageUtils;
import service.CategoryService;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;



public class FrameUpdateCategory extends JFrame {

	private JPanel contentPane;
	private JTextField textCategory;
	private JLabel lblImageShow;
	private JPanel panelImageShow;
	private JLabel lblTopic;
	private JLabel lblCategory;
	private JButton btnUpdate;
	private JPanel panel;
	private JLabel lblImage;
	private JButton btnImage;
	
	private Map<String, String> data;
	private CategoryService cateService;
	
	public ItemCategory itemCate;
	private Category category;
	private static FrameUpdateCategory myInstance;
	
	public static FrameUpdateCategory getMyInstance(Category category) {
		if (myInstance == null) {
			myInstance = new FrameUpdateCategory(category);
		} else {
			myInstance.dispose();
			myInstance = new FrameUpdateCategory(category);
		}
		return myInstance;
	}
	
	public FrameUpdateCategory(Category category) {
		this.category = category;
		initComponent();
		FrameUtils.alignFrameScreenCenter(this);
		
		data = new HashMap<>();
		cateService = new CategoryService();
		data.put("id", Integer.toString(category.getId()));
		
		textCategory.setText(category.getName());
		final int ROW_HEIGHT = 171;
		lblImageShow.setIcon(ImageUtils.getImageByURL("category", category.getImageIcon(), ROW_HEIGHT));
	}

	private void initComponent() {
		setResizable(false);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 428, 491);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTopic = new JLabel("Thêm chủ đề");
		lblTopic.setBounds(20, 11, 219, 34);
		lblTopic.setForeground(new Color(37, 57, 111));
		lblTopic.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(lblTopic);
		
		lblCategory = new JLabel("Chủ đề");
		lblCategory.setBounds(44, 97, 84, 21);
		lblCategory.setForeground(Color.BLACK);
		lblCategory.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(lblCategory);
		
		btnUpdate = new JButton("Cập Nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUpdateActionPerformed(e);
			}
		});
		btnUpdate.setBounds(137, 386, 133, 44);
		btnUpdate.setBackground(new Color(67, 98, 190));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 16));
		contentPane.add(btnUpdate);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 412, 62);
		panel.setBackground(new Color(242, 247, 255));
		contentPane.add(panel);
		
		JLabel lblImage = new JLabel("Hình ảnh");
		lblImage.setBounds(44, 164, 96, 21);
		lblImage.setForeground(Color.BLACK);
		lblImage.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(lblImage);
		
		JButton btnImage = new JButton("Tải ảnh lên");
		btnImage.setBounds(192, 156, 175, 37);
		btnImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					do_btnImage_actionPerformed(e);
				} catch (URISyntaxException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnImage.setForeground(new Color(0, 0, 0));
		btnImage.setFont(new Font("Arial", Font.BOLD, 14));
		btnImage.setBackground(new Color(242, 247, 255));
		contentPane.add(btnImage);
		
		textCategory = new JTextField();
		textCategory.setFont(new Font("Arial", Font.PLAIN, 14));
		textCategory.setBounds(192, 90, 175, 37);
		contentPane.add(textCategory);
		textCategory.setColumns(10);
		
		panelImageShow = new JPanel();
		panelImageShow.setBounds(10, 204, 392, 171);
		contentPane.add(panelImageShow);
		panelImageShow.setLayout(new BorderLayout(0, 0));
		
		lblImageShow = new JLabel("");
		lblImageShow.setHorizontalAlignment(SwingConstants.CENTER);
		panelImageShow.add(lblImageShow);
		lblImageShow.setForeground(new Color(0, 0, 0));
		lblImageShow.setBackground(new Color(192, 192, 192));
	}
	
	protected void do_btnImage_actionPerformed(ActionEvent e) throws URISyntaxException, IOException {
		JFileChooser chooser = new JFileChooser("desktop://");
		chooser.setDialogTitle("Hình ảnh");
		chooser.setFileFilter(
					new FileNameExtensionFilter("image(jpg, png, gif)","jpg","png","gif" )
				);
		chooser.setAcceptAllFileFilterUsed(false);
		int result = chooser.showOpenDialog( null);
		File rawFile = null;
		if(result == chooser.APPROVE_OPTION) {
			rawFile = chooser.getSelectedFile();
			final int ROW_HEIGHT = 171;
			lblImageShow.setIcon(ImageUtils.getImageByFile(rawFile.getAbsoluteFile(), ROW_HEIGHT));
			data.put("image", rawFile.toPath().toString());
		}
	}
	
	protected void btnUpdateActionPerformed(ActionEvent e) {
		data.put("category", textCategory.getText());
		if(cateService.update(data)) {
			JOptionPane.showMessageDialog(this, "Cập nhật chủ đề thành công");
			dispose();
			
			JPanel panel = this.itemCate.panelParent.getPanel();
			panel.repaint();
			panel.revalidate();
			this.itemCate.panelParent.loadData();
		} else {
			JOptionPane.showMessageDialog(this, ErrorMessage.ERROR_MESSAGES);
		}
		
	}
}
