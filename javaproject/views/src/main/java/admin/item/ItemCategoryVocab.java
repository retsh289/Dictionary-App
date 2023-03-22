package admin.item;

import javax.swing.JPanel;
import dao.impl.WordTypeDAOImpl;
import entity.Vocabulary;
import helper.ImageUtils;
import helper.StringUtils;
import service.CategoryService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

import admin.gui.FrameCategory;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemCategoryVocab extends JPanel {

	private JLabel lblWord;
	private WordTypeDAOImpl typeDao;
	private CategoryService cateService;
	private Vocabulary vocab;
	public FrameCategory frameCate;

	public ItemCategoryVocab(Vocabulary vocab, int x, int y) {
		initComponent(vocab, x, y);
	}

	private void initComponent(Vocabulary vocab, int x, int y) {	
		this.vocab = vocab;
		setBackground(new Color(242, 247, 255));
		setLayout(null);
		setBounds(x,y, 200, 200);
		typeDao = new WordTypeDAOImpl();
		
		JLabel lblImage = new JLabel("");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBackground(new Color(0, 0, 0));
		lblImage.setBounds(30, 0, 140, 140);
		add(lblImage);
		lblImage.setIcon(ImageUtils.getImageByURL("vocabulary", vocab.getImage(), 140));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(37, 57, 111));
		panel.setBounds(0, 140, 200, 60);
		add(panel);
		panel.setLayout(null);
		lblWord = new JLabel("");
		lblWord.setHorizontalAlignment(SwingConstants.CENTER);
		lblWord.setBounds(0, 0, 200, 30);
		panel.add(lblWord);
		lblWord.setForeground(new Color(255, 255, 255));
		lblWord.setFont(new Font("Arial", Font.PLAIN, 18));
		lblWord.setText(StringUtils.toCapitalize(vocab.getWord()) + " (" + typeDao.get(vocab.getWordTypeId()).toLowerCase() + ") ");
		
		JButton btnG = new JButton("Gỡ");
		btnG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBtnGActionPerformed(e);
			}
		});
		btnG.setForeground(Color.WHITE);
		btnG.setFont(new Font("Arial", Font.BOLD, 14));
		btnG.setBorder(null);
		btnG.setBackground(new Color(205, 16, 64));
		btnG.setBounds(71, 30, 50, 25);
		panel.add(btnG);
	}
	

	protected void doBtnGActionPerformed(ActionEvent e) {
		int option = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa chủ đề này này?", "Xóa chủ đề", JOptionPane.YES_NO_OPTION);
		if(option == JOptionPane.YES_OPTION) {
			cateService = new CategoryService();
			if(cateService.deleteCateFromVocab(vocab)) {
				JOptionPane.showMessageDialog(this, "Xoá chủ đề thành công!");
				
				JPanel panel = frameCate.getPanel();
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				frameCate.loadData();
			}
		}
	}
}
