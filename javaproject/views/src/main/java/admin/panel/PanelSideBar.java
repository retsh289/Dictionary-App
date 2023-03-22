package admin.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

import dao.impl.UserDAOImpl;
import entity.User;
import helper.IconImage;
import home.gui.FrameHome;

public class PanelSideBar extends JPanel {
	private JPanel panelVocab;
	private JLabel lblCategory;
	private JLabel lblVocab;
	private JPanel panelMember;
	private JLabel lblMember;
	private JPanel panelAdmin;
	private JLabel lblAdmin;
	private JPanel panelDashboard;
	private JLabel lblDashboard;
	private JPanel panelCategory;
	private JLabel lblLesson;
	private JPanel panelLesson;
	private JPanel panelFeedback;
	private JLabel lblFeedback;

	public JPanel getPanelVocab() {
		return panelVocab;
	}
	public void setPanelVocab(JPanel panelVocab) {
		this.panelVocab = panelVocab;
	}
	public JLabel getLblCategory() {
		return lblCategory;
	}
	public void setLblCategory(JLabel lblCategory) {
		this.lblCategory = lblCategory;
	}
	public JLabel getLblVocab() {
		return lblVocab;
	}
	public void setLblVocab(JLabel lblVocab) {
		this.lblVocab = lblVocab;
	}
	public JPanel getPanelMember() {
		return panelMember;
	}
	public void setPanelMember(JPanel panelMember) {
		this.panelMember = panelMember;
	}
	public JLabel getLblMember() {
		return lblMember;
	}
	public void setLblMember(JLabel lblMember) {
		this.lblMember = lblMember;
	}
	public JPanel getPanelAdmin() {
		return panelAdmin;
	}
	public void setPanelAdmin(JPanel panelAdmin) {
		this.panelAdmin = panelAdmin;
	}
	public JLabel getLblAdmin() {
		return lblAdmin;
	}
	public void setLblAdmin(JLabel lblAdmin) {
		this.lblAdmin = lblAdmin;
	}
	public JPanel getPanelDashboard() {
		return panelDashboard;
	}
	public void setPanelDashboard(JPanel panelDashboard) {
		this.panelDashboard = panelDashboard;
	}
	public JLabel getLblDashboard() {
		return lblDashboard;
	}
	public void setLblDashboard(JLabel lblDashboard) {
		this.lblDashboard = lblDashboard;
	}
	public JPanel getPanelCategory() {
		return panelCategory;
	}
	public void setPanelCategory(JPanel panelCategory) {
		this.panelCategory = panelCategory;
	}
	public JLabel getLblLesson() {
		return lblLesson;
	}
	public void setLblLesson(JLabel lblLesson) {
		this.lblLesson = lblLesson;
	}
	public JPanel getPanelLesson() {
		return panelLesson;
	}
	public void setPanelLesson(JPanel panelLesson) {
		this.panelLesson = panelLesson;
	}
	public JPanel getPanelFeedback() {
		return panelFeedback;
	}
	public void setPanelFeedback(JPanel panelFeedback) {
		this.panelFeedback = panelFeedback;
	}
	public JLabel getLblFeedback() {
		return lblFeedback;
	}
	public void setLblFeedback(JLabel lblFeedback) {
		this.lblFeedback = lblFeedback;
	}
	/**
	 * Create the panel.
	 */
	public PanelSideBar() {
		initComponent();
		
	}
	private void initComponent() {
		setLayout(null);
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 217, 777);
		
		// Icon Image
		IconImage icon = new IconImage();
		
		// Logo Panel
		JPanel panelLogo = new JPanel();
		panelLogo.setBackground(new Color(255, 255, 255));
		panelLogo.setLayout(null);
		panelLogo.setBounds(0, 0, 217, 78);
		add(panelLogo);
		JLabel lblLogo = new JLabel("EV Dictionary");
		lblLogo.setForeground(new Color(37, 57, 111));
		lblLogo.setFont(new Font("Arial", Font.BOLD, 20));
		lblLogo.setBounds(73, 16, 134, 39);
		panelLogo.add(lblLogo);
		JLabel lblIconLogo = new JLabel("");
		lblIconLogo.setBounds(20, 11, 55, 50);
		panelLogo.add(lblIconLogo);
		lblIconLogo.setBackground(new Color(0, 0, 0));
		lblIconLogo.setIcon(new ImageIcon(icon.getLogoImg()));
		
		// Home Panel
		JPanel panelHome = new JPanel();
		panelHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_panelHome_mouseClicked(e);
			}
		});
		panelHome.setLayout(null);
		panelHome.setBackground(Color.WHITE);
		panelHome.setBounds(0, 637, 217, 70);
		add(panelHome);
		JLabel lblHome = new JLabel("Trang chủ");
		lblHome.setForeground(new Color(37, 57, 143));
		lblHome.setFont(new Font("Arial", Font.BOLD, 16));
		lblHome.setBounds(78, 25, 129, 20);
		panelHome.add(lblHome);
		JLabel lblIconHome = new JLabel("");
		lblIconHome.setBounds(30, 20, 46, 29);
		panelHome.add(lblIconHome);
		lblIconHome.setIcon(new ImageIcon(icon.getHomeImg()));
		
		// Dashboard Panel
		panelDashboard = new JPanel();
		panelDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_panelDashboard_mouseClicked(e);
			}
		});
		panelDashboard.setBorder(null);
		panelDashboard.setLayout(null);
		panelDashboard.setBackground(new Color(37, 57, 111));
		panelDashboard.setBounds(0, 78, 217, 70);
		add(panelDashboard);
		lblDashboard = new JLabel("Dashboard");
		lblDashboard.setForeground(new Color(255, 255, 255));
		lblDashboard.setFont(new Font("Arial", Font.BOLD, 16));
		lblDashboard.setBounds(78, 25, 94, 20);
		panelDashboard.add(lblDashboard);
		JLabel lblIconDashboard = new JLabel("");
		lblIconDashboard.setBounds(30, 18, 46, 29);
		panelDashboard.add(lblIconDashboard);
		lblIconDashboard.setIcon(new ImageIcon(icon.getDashboardImg()));
		
		// Vocab Panel
		panelVocab = new JPanel();
		panelVocab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_panelVocab_mouseClicked(e);
			}
		});
		panelVocab.setBackground(new Color(255, 255, 255));
		panelVocab.setBounds(0, 148, 217, 70);
		add(panelVocab);
		panelVocab.setLayout(null);
		JLabel lblIconVocab = new JLabel("");
		lblIconVocab.setBackground(new Color(255, 255, 255));
		lblIconVocab.setForeground(new Color(255, 255, 255));
		lblIconVocab.setBounds(30, 20, 29, 31);
		panelVocab.add(lblIconVocab);
		lblIconVocab.setIcon(new ImageIcon(icon.getVocabImg()));
		lblVocab = new JLabel("Từ vựng");
		lblVocab.setFont(new Font("Arial", Font.BOLD, 16));
		lblVocab.setForeground(new Color(37, 57, 143));
		lblVocab.setBounds(78, 25, 94, 20);
		panelVocab.add(lblVocab);
		
		// Category Panel
		panelCategory = new JPanel();
		panelCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_panelCategory_mouseClicked(e);
			}
		});
		panelCategory.setLayout(null);
		panelCategory.setBackground(Color.WHITE);
		panelCategory.setBounds(0, 218, 217, 70);
		add(panelCategory);
		JLabel lblIconCategory = new JLabel("");
		lblIconCategory.setForeground(Color.WHITE);
		lblIconCategory.setBackground(Color.WHITE);
		lblIconCategory.setBounds(30, 20, 29, 31);
		panelCategory.add(lblIconCategory);
		lblIconCategory.setIcon(new ImageIcon(icon.getTopicImg()));
		lblCategory = new JLabel("Chủ đề");
		lblCategory.setForeground(new Color(37, 57, 143));
		lblCategory.setFont(new Font("Arial", Font.BOLD, 16));
		lblCategory.setBounds(78, 25, 94, 20);
		panelCategory.add(lblCategory);
		
		
		// Member Panel
		panelMember = new JPanel();
		panelMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_panelMember_mouseClicked(e);
			}
		});
		panelMember.setBackground(new Color(255, 255, 255));
		panelMember.setBounds(0, 288, 217, 70);
		panelMember.setLayout(null);
		add(panelMember);
		lblMember = new JLabel("Thành viên");
		lblMember.setBounds(75, 25, 87, 19);
		panelMember.add(lblMember);
		lblMember.setForeground(new Color(37, 57, 111));
		lblMember.setFont(new Font("Arial", Font.BOLD, 16));
		JLabel lblIconMember = new JLabel("");
		lblIconMember.setBounds(30, 20, 35, 28);
		panelMember.add(lblIconMember);
		lblIconMember.setIcon(new ImageIcon(icon.getMemberImg()));
		

		// Admin Panel
		panelAdmin = new JPanel();
		panelAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_panelAdmin_mouseClicked(e);
			}
		});
		panelAdmin.setBackground(new Color(255, 255, 255));
		panelAdmin.setBounds(0, 357, 217, 70);
		panelAdmin.setLayout(null);
		add(panelAdmin);
		lblAdmin = new JLabel("Quản trị viên");
		lblAdmin.setForeground(new Color(37, 57, 143));
		lblAdmin.setFont(new Font("Arial", Font.BOLD, 16));
		lblAdmin.setBounds(78, 25, 128, 19);
		panelAdmin.add(lblAdmin);
		JLabel lblIconAdmin = new JLabel("");
		lblIconAdmin.setBounds(30, 20, 46, 24);
		panelAdmin.add(lblIconAdmin);
		lblIconAdmin.setIcon(new ImageIcon(icon.getAdminImg()));
		
		// Panel Lesson
		panelLesson = new JPanel();
		panelLesson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_panelLesson_mouseClicked(e);
			}
		});
		panelLesson.setLayout(null);
		panelLesson.setBackground(Color.WHITE);
		panelLesson.setBounds(0, 427, 217, 70);
		add(panelLesson);
		
		lblLesson = new JLabel("Bài học");
		lblLesson.setForeground(new Color(37, 57, 143));
		lblLesson.setFont(new Font("Arial", Font.BOLD, 16));
		lblLesson.setBounds(78, 25, 128, 19);
		panelLesson.add(lblLesson);
		
		JLabel lblIconLesson = new JLabel("");
		lblIconLesson.setBounds(30, 20, 46, 24);
		panelLesson.add(lblIconLesson);
		lblIconLesson.setIcon(new ImageIcon(icon.getLessonImg()));
		
		// Panel Feedback
		panelFeedback = new JPanel();
		panelFeedback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_panelFeedback_mouseClicked(e);
			}
		});
		panelFeedback.setLayout(null);
		panelFeedback.setBackground(Color.WHITE);
		panelFeedback.setBounds(0, 497, 217, 70);
		add(panelFeedback);
		
		lblFeedback = new JLabel("Phản hồi");
		lblFeedback.setForeground(new Color(37, 57, 143));
		lblFeedback.setFont(new Font("Arial", Font.BOLD, 16));
		lblFeedback.setBounds(78, 25, 128, 19);
		panelFeedback.add(lblFeedback);
		
		JLabel lblIconFeedback = new JLabel("");
		lblIconFeedback.setBounds(30, 20, 46, 24);
		panelFeedback.add(lblIconFeedback);
		lblIconFeedback.setIcon(new ImageIcon(icon.getFeedbackImg()));
				
		// Panel LogOut
		JPanel panelLogOut = new JPanel();
		panelLogOut.setLayout(null);
		panelLogOut.setBackground(new Color(124, 141, 181));
		panelLogOut.setBounds(0, 707, 217, 70);
		panelLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_panelLogOut_mouseClicked(e);
			}
		});
		add(panelLogOut);
		
		JLabel lblLogOut = new JLabel("Đăng xuất");
		lblLogOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogOut.setForeground(new Color(255, 255, 255));
		lblLogOut.setFont(new Font("Arial", Font.BOLD, 18));
		lblLogOut.setBounds(0, 0, 217, 70);
		panelLogOut.add(lblLogOut);
		
	}
	protected void do_panelHome_mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
		
		
	}
	public void menuChanged(JPanel panel, JLabel label) {
		lblVocab.setForeground(new Color(37, 57, 111));
		panelVocab.setBackground(new Color(255, 255, 255));
		lblMember.setForeground(new Color(37, 57, 111));
		panelMember.setBackground(new Color(255, 255, 255));
		lblAdmin.setForeground(new Color(37, 57, 111));
		panelAdmin.setBackground(new Color(255, 255, 255));
		lblDashboard.setForeground(new Color(37, 57, 111));
		panelDashboard.setBackground(new Color(255, 255, 255));
		lblCategory.setForeground(new Color(37, 57, 111));
		panelCategory.setBackground(new Color(255, 255, 255));
		lblLesson.setForeground(new Color(37, 57, 111));
		panelLesson.setBackground(new Color(255, 255, 255));
		lblFeedback.setForeground(new Color(37, 57, 111));
		panelFeedback.setBackground(new Color(255, 255, 255));
		label.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(37, 57, 111));
	}
	protected void do_panelDashboard_mouseClicked(MouseEvent e) {
	}
	protected void do_panelVocab_mouseClicked(MouseEvent e) {
	}
	protected void do_panelCategory_mouseClicked(MouseEvent e) {
	}
	protected void do_panelMember_mouseClicked(MouseEvent e) {
	}
	protected void do_panelAdmin_mouseClicked(MouseEvent e) {
	}
	protected void do_panelLesson_mouseClicked(MouseEvent e) {
	}
	protected void do_panelFeedback_mouseClicked(MouseEvent e) {
	}
	protected void do_panelLogOut_mouseClicked(MouseEvent e) {
		
	}
	public static void main(String[] args) {
		User x = new User(17,"hungn12333@gmail.com", "Aa@12345", 1);
		new UserDAOImpl().updateRole(x);
	}
}
