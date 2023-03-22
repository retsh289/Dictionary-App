package home.panel;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.SwingConstants;

import service.Authorization;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Cursor;


public class PanelNavBar extends JPanel {
	private JPanel panelVocab;
	private JLabel lblVocab;
	private JPanel panelCategory;
	private JLabel lblCategory;
	private JPanel panelProfile;
	private JLabel lblProfile;
	private JPanel panelLesson;
	private JLabel lblLesson;
	private JLabel lblHome;
	private JPanel panelHome;
	private JPanel panelSignUp;
	private JLabel lblSignUp;
	private JPanel panelSignIn;
	private JLabel lblSignIn;
	private JLabel lblLogOut;
	private JPanel panelLogOut;
	public JPanel getPanelVocab() {
		return panelVocab;
	}
	public void setPanelVocab(JPanel panelVocab) {
		this.panelVocab = panelVocab;
	}
	public JLabel getLblVocab() {
		return lblVocab;
	}
	public void setLblVocab(JLabel lblVocab) {
		this.lblVocab = lblVocab;
	}
	public JPanel getPanelCategory() {
		return panelCategory;
	}
	public void setPanelCategory(JPanel panelCategory) {
		this.panelCategory = panelCategory;
	}
	public JLabel getLblCategory() {
		return lblCategory;
	}
	public void setLblCategory(JLabel lblCategory) {
		this.lblCategory = lblCategory;
	}
	public JPanel getPanelProfile() {
		return panelProfile;
	}
	public void setPanelProfile(JPanel panelProfile) {
		this.panelProfile = panelProfile;
	}
	public JLabel getLblProfile() {
		return lblProfile;
	}
	public void setLblProfile(JLabel lblProfile) {
		this.lblProfile = lblProfile;
	}
	public JPanel getPanelLesson() {
		return panelLesson;
	}
	public void setPanelLesson(JPanel panelLesson) {
		this.panelLesson = panelLesson;
	}
	public JLabel getLblLesson() {
		return lblLesson;
	}
	public void setLblLesson(JLabel lblLesson) {
		this.lblLesson = lblLesson;
	}
	public JLabel getLblHome() {
		return lblHome;
	}
	public void setLblHome(JLabel lblHome) {
		this.lblHome = lblHome;
	}
	public JPanel getPanelHome() {
		return panelHome;
	}
	public void setPanelHome(JPanel panelHome) {
		this.panelHome = panelHome;
	}
	public JPanel getPanelSignUp() {
		return panelSignUp;
	}
	public void setPanelSignUp(JPanel panelSignUp) {
		this.panelSignUp = panelSignUp;
	}
	public JLabel getLblSignUp() {
		return lblSignUp;
	}
	public void setLblSignUp(JLabel lblSignUp) {
		this.lblSignUp = lblSignUp;
	}
	public JPanel getPanelSignIn() {
		return panelSignIn;
	}
	public void setPanelSignIn(JPanel panelSignIn) {
		this.panelSignIn = panelSignIn;
	}
	public JLabel getLblSignIn() {
		return lblSignIn;
	}
	public void setLblSignIn(JLabel lblSignIn) {
		this.lblSignIn = lblSignIn;
	}
	public PanelNavBar() {
		initComponent();
			if (Authorization.email == null) {
				panelProfile.setVisible(false);
				panelLogOut.setVisible(false);
			} else {
				panelSignUp.setVisible(false);
				panelSignIn.setVisible(false);
			}
	}
	private void initComponent() {
		setBounds(0, 0, 1052, 77);
		setBackground(new Color(37, 57, 111));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setLayout(null);
		panel.setBackground(new Color(37, 57, 111));
		panel.setBounds(0, 0, 1052, 77);
		add(panel);
		
		// Vocab Panel
		panelVocab = new JPanel();
		panelVocab.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelVocab.setBorder(null);
		panelVocab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_panelVocab_mouseClicked(e);
			}
		});
		panelVocab.setBackground(Color.WHITE);
		panelVocab.setBounds(469, 27, 115, 50);
		panel.add(panelVocab);
		panelVocab.setLayout(new BorderLayout(0, 0));
		
		lblVocab = new JLabel("Từ vựng");
		lblVocab.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblVocab.setHorizontalAlignment(SwingConstants.CENTER);
		lblVocab.setFont(new Font("Arial", Font.BOLD, 14));
		panelVocab.add(lblVocab);
		
		// SignUp Panel
		panelSignUp = new JPanel();
		panelSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_panelSignUp_mouseClicked(e);
			}
		});
		panelSignUp.setBorder(null);
		panelSignUp.setBackground(Color.WHITE);
		panelSignUp.setBounds(927, 27, 115, 50);
		panel.add(panelSignUp);
		panelSignUp.setLayout(new BorderLayout(0, 0));
		
		lblSignUp = new JLabel("Đăng ký");
		lblSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setFont(new Font("Arial", Font.BOLD, 14));
		panelSignUp.add(lblSignUp);
		
		// SingIn Panel
		panelSignIn = new JPanel();
		panelSignIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelSignIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_panelSignIn_mouseClicked(e);
			}
		});
		panelSignIn.setBorder(null);
		panelSignIn.setBackground(Color.WHITE);
		panelSignIn.setBounds(812, 27, 115, 50);
		panel.add(panelSignIn);
		panelSignIn.setLayout(new BorderLayout(0, 0));
		
		lblSignIn = new JLabel("Đăng nhập");
		lblSignIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSignIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignIn.setFont(new Font("Arial", Font.BOLD, 14));
		panelSignIn.add(lblSignIn);
		
		// Category Panel
		panelCategory = new JPanel();
		panelCategory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelCategory.setBorder(null);
		panelCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_panelCategory_mouseClicked(e);
			}
		});
		panelCategory.setBackground(Color.WHITE);
		panelCategory.setBounds(584, 27, 115, 50);
		panel.add(panelCategory);
		panelCategory.setLayout(new BorderLayout(0, 0));
		
		lblCategory = new JLabel("Chủ đề");
		lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategory.setFont(new Font("Arial", Font.BOLD, 14));
		panelCategory.add(lblCategory);
		
		// Logout Panel
		panelLogOut = new JPanel();
		panelLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_panelLogOut_mouseClicked(e);
			}
		});
		panelLogOut.setBackground(Color.WHITE);
		panelLogOut.setBounds(926, 27, 115, 50);
		panel.add(panelLogOut);
		panelLogOut.setLayout(new BorderLayout(0, 0));
		
		lblLogOut = new JLabel("Đăng xuất");
		lblLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLogOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogOut.setFont(new Font("Arial", Font.BOLD, 14));
		panelLogOut.add(lblLogOut);
		
		// Lesson Panel
		panelLesson = new JPanel();
		panelLesson.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelLesson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_panelLesson_mouseClicked(e);
			}
		});
		panelLesson.setBorder(null);
		panelLesson.setBackground(Color.WHITE);
		panelLesson.setBounds(697, 27, 115, 50);
		panel.add(panelLesson);
		panelLesson.setLayout(new BorderLayout(0, 0));
		
		lblLesson = new JLabel("Bài học");
		lblLesson.setHorizontalAlignment(SwingConstants.CENTER);
		lblLesson.setFont(new Font("Arial", Font.BOLD, 14));
		panelLesson.add(lblLesson);
		
		// Profile Panel
		panelProfile = new JPanel();
		panelProfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_panelProfile_mouseClicked(e);
			}
		});
		panelProfile.setBorder(null);
		panelProfile.setBackground(Color.WHITE);
		panelProfile.setBounds(811, 27, 115, 50);
		panel.add(panelProfile);
		panelProfile.setLayout(new BorderLayout(0, 0));
		
		lblProfile = new JLabel("Thông tin");
		lblProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfile.setFont(new Font("Arial", Font.BOLD, 14));
		panelProfile.add(lblProfile);
		
		panelHome = new JPanel();
		panelHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_panelHome_mouseClicked(e);
			}
		});
		panelHome.setBorder(null);
		panelHome.setBackground(new Color(37, 57, 111));
		panelHome.setBounds(354, 27, 115, 50);
		panel.add(panelHome);
		panelHome.setLayout(new BorderLayout(0, 0));
		
		lblHome = new JLabel("Trang chủ");
		lblHome.setForeground(new Color(255, 255, 255));
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setFont(new Font("Arial", Font.BOLD, 14));
		panelHome.add(lblHome);
		
	}

	public void menuChanged(JPanel panel, JLabel label) {
		lblVocab.setForeground(new Color(37, 57, 111));
		panelVocab.setBackground(new Color(255, 255, 255));
		lblCategory.setForeground(new Color(37, 57, 111));
		panelCategory.setBackground(new Color(255, 255, 255));
		lblProfile.setForeground(new Color(37, 57, 111));
		panelProfile.setBackground(new Color(255, 255, 255));
		lblLesson.setForeground(new Color(37, 57, 111));
		panelLesson.setBackground(new Color(255, 255, 255));
		lblHome.setForeground(new Color(37, 57, 111));
		panelHome.setBackground(new Color(255, 255, 255));
		lblSignIn.setForeground(new Color(37, 57, 111));
		panelSignIn.setBackground(new Color(255, 255, 255));
		lblSignUp.setForeground(new Color(37, 57, 111));
		panelSignUp.setBackground(new Color(255, 255, 255));
		lblLogOut.setForeground(new Color(37, 57, 111));
		panelLogOut.setBackground(new Color(255, 255, 255));
		label.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(37, 57, 111));
	}
	
	protected void do_panelVocab_mouseClicked(MouseEvent e) {
		
	}
	protected void do_panelCategory_mouseClicked(MouseEvent e) {
	}
	protected void do_panelProfile_mouseClicked(MouseEvent e) {
	}
	protected void do_panelLesson_mouseClicked(MouseEvent e) {
	}
	protected void do_panelHome_mouseClicked(MouseEvent e) {
	}
	protected void do_panelSignUp_mouseClicked(MouseEvent e) {
	}
	protected void do_panelSignIn_mouseClicked(MouseEvent e) {
	}
	protected void do_panelLogOut_mouseClicked(MouseEvent e) {
	}
}
