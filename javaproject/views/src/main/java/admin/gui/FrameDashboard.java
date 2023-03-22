package admin.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.border.LineBorder;
import admin.panel.PanelAdmin;
import admin.panel.PanelCategory;
import admin.panel.PanelDashboard;
import admin.panel.PanelResponse;
import admin.panel.PanelLesson;
import admin.panel.PanelMainContent;
import admin.panel.PanelMember;
import admin.panel.PanelSideBar;
import admin.panel.PanelVocab;
import helper.FrameUtils;
import helper.ImageUtils;
import home.gui.FrameHome;
import home.gui.FrameSignIn;
import service.Authorization;

public class FrameDashboard extends JFrame {

	private JPanel contentPane;
	private PanelMember panelMember;
	private PanelVocab panelVocab;
	private PanelAdmin panelAdmin;
	private PanelDashboard panelDashboard;
	private PanelCategory panelCategory;
	private PanelLesson panelLesson;
	private PanelResponse panelResponse;

	private PanelMainContent mainContent;

	class SideBarMenu extends PanelSideBar {
		private FrameSignIn frOut;
		protected void do_panelDashboard_mouseClicked(MouseEvent e) {
			panelDashboard = new PanelDashboard();
			panelDashboard.frameParent = getOuter();
			menuClicked(panelDashboard);
			menuChanged(getPanelDashboard(), getLblDashboard());
		}

		protected void do_panelVocab_mouseClicked(MouseEvent e) {
			panelVocab = PanelVocab.getMyInstance();
			panelVocab.frameParent = getOuter();
			menuClicked(panelVocab);
			menuChanged(getPanelVocab(), getLblVocab());
		}

		protected void do_panelCategory_mouseClicked(MouseEvent e) {
			panelCategory = PanelCategory.getMyInstance();
			panelCategory.frameParent = getOuter();
			menuClicked(panelCategory);
			menuChanged(getPanelCategory(), getLblCategory());
		}

		protected void do_panelMember_mouseClicked(MouseEvent e) {
			panelMember = PanelMember.getMyInstance();
			panelMember.frameParent = getOuter();
			menuClicked(panelMember);
			menuChanged(getPanelMember(), getLblMember());
		}

		protected void do_panelAdmin_mouseClicked(MouseEvent e) {
			panelAdmin = PanelAdmin.getMyInstance();
			panelAdmin.frameParent = getOuter();
			menuClicked(panelAdmin);
			menuChanged(getPanelAdmin(), getLblAdmin());
		}

		protected void do_panelLesson_mouseClicked(MouseEvent e) {
			panelLesson = PanelLesson.getMyInstance();
			panelLesson.frameParent = getOuter();
			menuClicked(panelLesson);
			menuChanged(getPanelLesson(), getLblLesson());
		}

		protected void do_panelFeedback_mouseClicked(MouseEvent e) {
			panelResponse = PanelResponse.getMyInstance();
			panelResponse.frameParent = getOuter();
			menuClicked(panelResponse);
			menuChanged(getPanelFeedback(), getLblFeedback());
		}
		protected void do_panelLogOut_mouseClicked(MouseEvent e) {
			dispose();
			Authorization.setNull();
			frOut = new FrameSignIn();
			frOut.setVisible(true);
		}
		protected void do_panelHome_mouseClicked(MouseEvent e) {
			dispose();
			FrameHome frame = new FrameHome();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
			frame.setVisible(true);
		}
	}

	private FrameDashboard getOuter() {
		return FrameDashboard.this;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FrameDashboard frame = new FrameDashboard();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public FrameDashboard() {
		initComponent();
	}

	private void initComponent() {
		setResizable(false);
		setTitle("Dashboard");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(FrameDashboard.class.getResource("/image/dictionary-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1318, 816);

		contentPane = new JPanel();
		contentPane.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		// Top Bar
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(217, 0, 1085, 50);
		contentPane.add(panel_1);
		panel_1.setBackground(new Color(242, 247, 255));
		panel_1.setLayout(null);
		// Side Bar
		JPanel sideBar = new JPanel();
		sideBar.setBackground(new Color(255, 255, 255));
		sideBar.setBounds(0, 0, 217, 777);
		contentPane.add(sideBar);
		sideBar.setLayout(null);
		SideBarMenu sideBarMenu = new SideBarMenu();
		sideBar.add(sideBarMenu);

		// Main Content
		mainContent = new PanelMainContent();
		mainContent.setBounds(217, 50, 1085, 729);
		mainContent.setLayout(null);
		contentPane.add(mainContent);

		panelDashboard = new PanelDashboard();
		panelDashboard.loadData();
		mainContent.add(panelDashboard);

		FrameUtils.alignFrameScreenCenter(this);

	}

	public void callPanel(JPanel panel) {
		// remove
		mainContent.removeAll();
		mainContent.repaint();
		mainContent.revalidate();
		// repaint
		mainContent.add(panel);
		mainContent.repaint();
		mainContent.revalidate();
	}

	private void menuClicked(JPanel panel) {
		callPanel(panel);
	}
}
