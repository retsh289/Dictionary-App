package admin.insert;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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

import admin.panel.PanelAdmin;
import admin.panel.PanelCategory;
import helper.ErrorMessage;
import helper.FrameUtils;
import helper.ImageUtils;
import service.CategoryService;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class FrameInsertCategory extends JFrame {

	private JPanel contentPane;
	private JTextField textCategory;
	private CategoryService cateService;
	private JLabel lblImageShow;
	private JPanel panelImageShow;
	private Map<String, String> data;

	public PanelCategory panelParent;
	private static FrameInsertCategory myInstance;

	public static FrameInsertCategory getMyInstance() {
		if (myInstance == null) {
			myInstance = new FrameInsertCategory();
		} else {
			myInstance.dispose();
			myInstance = new FrameInsertCategory();
		}
		return myInstance;
	}

	public FrameInsertCategory() {
		initComponent();
		data = new HashMap<>();

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

		JLabel lblTopic = new JLabel("Thêm chủ đề");
		lblTopic.setBounds(20, 11, 219, 34);
		lblTopic.setForeground(new Color(37, 57, 111));
		lblTopic.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(lblTopic);

		JLabel lblCategory = new JLabel("Chủ đề");
		lblCategory.setBounds(44, 97, 84, 21);
		lblCategory.setForeground(Color.BLACK);
		lblCategory.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(lblCategory);

		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddActionPerformed(e);
			}
		});
		btnAdd.setBounds(137, 386, 133, 44);
		btnAdd.setBackground(new Color(67, 98, 190));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setFont(new Font("Arial", Font.BOLD, 16));
		contentPane.add(btnAdd);

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
		FrameUtils.alignFrameScreenCenter(this);
	}

	protected void do_btnImage_actionPerformed(ActionEvent e) throws URISyntaxException, IOException {
		JFileChooser chooser = new JFileChooser("desktop://");
		chooser.setDialogTitle("Hình ảnh");
		chooser.setFileFilter(new FileNameExtensionFilter("image(jpg, png, gif)", "jpg", "png", "gif"));
		chooser.setAcceptAllFileFilterUsed(false);
		int result = chooser.showOpenDialog(null);
		File rawFile = null;

		if (result == chooser.APPROVE_OPTION) {
			rawFile = chooser.getSelectedFile();
			final int ROW_HEIGHT = 171;

			lblImageShow.setIcon(ImageUtils.getImageByFile(rawFile.getAbsoluteFile(), ROW_HEIGHT));
			data.put("image", rawFile.toPath().toString());
		}
	}

	protected void btnAddActionPerformed(ActionEvent e) {
		cateService = new CategoryService();
		data.put("category", textCategory.getText());

		if (cateService.add(data)) {
			JOptionPane.showMessageDialog(this, "Thêm chủ đề thành công");
			dispose();

			PanelCategory newPanelParent = new PanelCategory();
			newPanelParent.frameParent = this.panelParent.frameParent;
			this.panelParent.frameParent.callPanel(newPanelParent);
		} else {
			JOptionPane.showMessageDialog(this, ErrorMessage.ERROR_MESSAGES);
		}

	}
}
