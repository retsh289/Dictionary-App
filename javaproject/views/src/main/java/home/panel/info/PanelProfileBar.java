package home.panel.info;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import helper.IconImage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class PanelProfileBar extends JPanel {
	private JPanel panelRespose;
	private JLabel lblRespose;
	private JPanel panelBookmark;
	private JLabel lblBookmark;
	private JPanel panelHistory;
	private JLabel lblHistory;
	private JPanel panelMember;
	private JLabel lblMember;

	
	public JPanel getPanelRespose() {
		return panelRespose;
	}

	public JLabel getLblRespose() {
		return lblRespose;
	}

	public JPanel getPanelBookmark() {
		return panelBookmark;
	}

	public JLabel getLblBookmark() {
		return lblBookmark;
	}

	public JPanel getPanelHistory() {
		return panelHistory;
	}

	public JLabel getLblHistory() {
		return lblHistory;
	}

	public JPanel getPanelMember() {
		return panelMember;
	}

	public JLabel getLblMember() {
		return lblMember;
	}

	public PanelProfileBar() {
		setLayout(null);
		setBackground(new Color(37, 57, 111));
		setBounds(0, 0, 240, 691);
		
		// Icon Image
		IconImage icon = new IconImage();
		
		// Member Panel
		panelMember = new JPanel();
		panelMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doPanelMemberMouseClicked(e);
			}
		});
		panelMember.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelMember.setLayout(null);
		panelMember.setBackground(Color.WHITE);
		panelMember.setBounds(0, 0, 240, 70);
		add(panelMember);
		
		lblMember = new JLabel("Thông tin tài khoản");
		lblMember.setForeground(new Color(37, 57, 143));
		lblMember.setFont(new Font("Arial", Font.BOLD, 16));
		lblMember.setBounds(78, 25, 152, 20);
		panelMember.add(lblMember);
		
		JLabel lblIconMember = new JLabel("");
		lblIconMember.setBounds(30, 20, 46, 29);
		panelMember.add(lblIconMember);
		lblIconMember.setIcon(new ImageIcon(icon.getMemberImg()));
		
		// Vocab Panel
		panelRespose = new JPanel();
		panelRespose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doPanelResposeMouseClicked(e);
			}
		});
		panelRespose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelRespose.setBackground(new Color(255, 255, 255));
		panelRespose.setBounds(0, 209, 240, 70);
		add(panelRespose);
		panelRespose.setLayout(null);
		JLabel lblIconVocab = new JLabel("");
		lblIconVocab.setBackground(new Color(255, 255, 255));
		lblIconVocab.setForeground(new Color(255, 255, 255));
		lblIconVocab.setBounds(30, 20, 29, 31);
		panelRespose.add(lblIconVocab);
		lblIconVocab.setIcon(new ImageIcon(icon.getVocabImg()));
		lblRespose = new JLabel("Góp ý");
		lblRespose.setFont(new Font("Arial", Font.BOLD, 16));
		lblRespose.setForeground(new Color(37, 57, 143));
		lblRespose.setBounds(78, 25, 152, 20);
		panelRespose.add(lblRespose);
		
		// Bookmark Panel
		panelBookmark = new JPanel();
		panelBookmark.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doPanelBookmarkMouseClicked(e);
			}
		});
		panelBookmark.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBookmark.setLayout(null);
		panelBookmark.setBackground(Color.WHITE);
		panelBookmark.setBounds(0, 69, 240, 70);
		add(panelBookmark);
		JLabel lblIconBookmark = new JLabel("");
		lblIconBookmark.setForeground(Color.WHITE);
		lblIconBookmark.setBackground(Color.WHITE);
		lblIconBookmark.setBounds(30, 20, 29, 31);
		panelBookmark.add(lblIconBookmark);
		lblIconBookmark.setIcon(new ImageIcon(icon.getBookmarkImg()));
		lblBookmark = new JLabel("Yêu thích");
		lblBookmark.setForeground(new Color(37, 57, 143));
		lblBookmark.setFont(new Font("Arial", Font.BOLD, 16));
		lblBookmark.setBounds(78, 25, 94, 20);
		panelBookmark.add(lblBookmark);
		
		
		// History Panel
		panelHistory = new JPanel();
		panelHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doPanelHistoryMouseClicked(e);
			}
		});
		panelHistory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelHistory.setBackground(new Color(255, 255, 255));
		panelHistory.setBounds(0, 139, 240, 70);
		panelHistory.setLayout(null);
		add(panelHistory);
		
		lblHistory = new JLabel("Lịch sử");
		lblHistory.setBounds(78, 25, 87, 19);
		panelHistory.add(lblHistory);
		lblHistory.setForeground(new Color(37, 57, 111));
		lblHistory.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblIconHistory = new JLabel("");
		lblIconHistory.setBounds(30, 20, 35, 28);
		panelHistory.add(lblIconHistory);
		lblIconHistory.setIcon(new ImageIcon(icon.getHistoryImg()));
	}

	public void menuChanged(JPanel panel, JLabel label) {
		lblRespose.setForeground(Color.white);
		panelRespose.setBackground(new Color(37, 57, 111));
		lblHistory.setForeground(Color.white);
		panelHistory.setBackground(new Color(37, 57, 111));
		lblBookmark.setForeground(Color.white);
		panelBookmark.setBackground(new Color(37, 57, 111));
		lblMember.setForeground(Color.white);
		panelMember.setBackground(new Color(37, 57, 111));
		label.setForeground(new Color(37, 57, 111));
		panel.setBackground(Color.white);
	}

	protected void doPanelBookmarkMouseClicked(MouseEvent e) {
	}
	protected void doPanelHistoryMouseClicked(MouseEvent e) {
	}
	protected void doPanelMemberMouseClicked(MouseEvent e) {
	}
	protected void doPanelResposeMouseClicked(MouseEvent e) {
	}
}
