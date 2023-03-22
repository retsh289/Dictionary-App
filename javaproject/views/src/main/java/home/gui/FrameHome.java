package home.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import admin.gui.FrameDashboard;
import admin.update.FrameUpdateMember;
import dao.impl.UserDAOImpl;
import helper.FrameUtils;
import helper.IconImage;
import home.panel.PanelCategory;
import home.panel.PanelHome;
import home.panel.PanelLesson;
import home.panel.PanelMainContent;
import home.panel.PanelNavBar;
import home.panel.PanelProfile;
import home.panel.PanelVocab;
import service.Authorization;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameHome extends JFrame {
	public FrameSignIn frOut;
	public FrameSignIn frIn;
	public static String flag = "PanelVocab";
	private JPanel contentPane;
	private PanelHome panelHome;
	private PanelCategory panelCategory;
	private PanelVocab panelVocab;
	private JLabel lblSignIn;
	private JPanel btnLogout;
	private JLabel lblLogout;
	private JPanel navBar;
	private PanelProfile panelProfile;
	private PanelLesson panelLesson;
	private PanelMainContent panelMain;

	private static FrameHome myInstance;

	public static FrameHome getMyInstance() {
		if (myInstance == null) {
			myInstance = new FrameHome();
		}
		return myInstance;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FrameHome frame = new FrameHome();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public class NavBar extends PanelNavBar {
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

		protected void do_panelProfile_mouseClicked(MouseEvent e) {
			panelProfile = new PanelProfile();
			panelProfile.frameParent = getOuter();
			menuClicked(panelProfile);
			menuChanged(getPanelProfile(), getLblProfile());
		}

		protected void do_panelLesson_mouseClicked(MouseEvent e) {
			panelLesson = new PanelLesson();
			panelLesson.frameParent = getOuter();
			menuClicked(panelLesson);
			menuChanged(getPanelLesson(), getLblLesson());
		}

		protected void do_panelHome_mouseClicked(MouseEvent e) {
			panelHome = new PanelHome();
			panelHome.frameParent = getOuter();
			menuClicked(panelHome);
			menuChanged(getPanelHome(), getLblHome());
		}

		protected void do_panelSignIn_mouseClicked(MouseEvent e) {
			dispose();
			frIn = new FrameSignIn();
			frIn.setVisible(true);
		}

		protected void do_panelLogOut_mouseClicked(MouseEvent e) {
			dispose();
			Authorization.setNull();
			frOut = new FrameSignIn();
			frOut.setVisible(true);
		}
	}

	public FrameHome() {
		initComponent();

		btnLogout = new JPanel();
		btnLogout.setBounds(982, 32, 93, 45);
		if (Authorization.email != null) {
			navBar.add(btnLogout);
		}
		btnLogout.setLayout(null);
		btnLogout.setBackground(Color.WHITE);

		lblLogout = new JLabel("Đăng Xuất");
		lblLogout.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogout.setFont(new Font("Arial", Font.BOLD, 14));
		lblLogout.setBounds(10, 11, 73, 23);
		btnLogout.add(lblLogout);
	}

	private FrameHome getOuter() {
		return FrameHome.this;
	}

	private void initComponent() {
		setResizable(false);
		setTitle("EV Dictionary");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(FrameDashboard.class.getResource("/image/dictionary-icon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1318, 816);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Logo Panel
		JPanel panel = new JPanel();
		panel.setBackground(new Color(37, 57, 111));
		panel.setBounds(0, 0, 250, 77);
		panel.setLayout(null);
		contentPane.add(panel);

		JPanel panelLogo = new JPanel();
		panelLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_panelLogo_mouseClicked(e);
			}
		});
		panelLogo.setBackground(new Color(37, 57, 111));
		panelLogo.setLayout(null);
		panelLogo.setBounds(0, 0, 250, 77);
		panel.add(panelLogo);

		JLabel lblLogo = new JLabel("EV Dictionary");
		lblLogo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogo.setBackground(new Color(255, 255, 255));
		lblLogo.setForeground(new Color(255, 255, 255));
		lblLogo.setFont(new Font("Arial", Font.BOLD, 20));
		lblLogo.setBounds(80, 16, 134, 39);
		panelLogo.add(lblLogo);

		IconImage icon = new IconImage();
		JLabel lblIconLogo = new JLabel("");
		lblIconLogo.setBounds(20, 12, 55, 50);
		panelLogo.add(lblIconLogo);
		lblIconLogo.setBackground(new Color(0, 0, 0));
		lblIconLogo.setIcon(new ImageIcon(icon.getLogoImg()));

		// NavBar
		NavBar navBarMenu = new NavBar();
		navBar = new JPanel();
		navBar.setBounds(250, 0, 1051, 77);
		navBar.add(navBarMenu);
		navBar.setLayout(null);
		contentPane.add(navBar);

		// Main Panel
		panelMain = new PanelMainContent();

		panelHome = PanelHome.getMyInstance();
		panelMain.add(panelHome);

		contentPane.add(panelMain);
		menuClicked(panelHome);
		FrameUtils.alignFrameScreenCenter(this);
	}
	
	public void navbarMenuChanged() {
		Component[] navComp = navBar.getComponents();
		if(navComp[0] instanceof NavBar) {
			((NavBar) navComp[0]).do_panelVocab_mouseClicked(null);
		}
	}
	
	public void navbarMenuChangedLesson() {
		Component[] navComp = navBar.getComponents();
		if(navComp[0] instanceof NavBar) {
			((NavBar) navComp[0]).do_panelLesson_mouseClicked(null);
		}
	}
	

	public void callPanel(JPanel panel) {
		// remove
		panelMain.removeAll();
		panelMain.repaint();
		panelMain.revalidate();
		// repaint
		panelMain.add(panel);
		panelMain.repaint();
		panelMain.revalidate();
	}

	public void menuClicked(JPanel panel) {
		callPanel(panel);
	}

	protected void do_panelLogo_mouseClicked(MouseEvent e) {
		if (Authorization.loggedrole != 3) {
			dispose();
			FrameDashboard dh = new FrameDashboard();
			dh.setVisible(true);
		} else {
			dispose();
			FrameHome dh = new FrameHome();
			dh.setVisible(true);
			System.out.println("xx");

		}
	}

	protected void do_panelLogIn_mouseClicked(MouseEvent e) {
		dispose();
		frIn = new FrameSignIn();
		frIn.setVisible(true);
		frIn.setLocation(400, 300);
	}

	protected void do_panelLogOut_mouseClicked(MouseEvent e) {
		if (Authorization.email != null) {
			UserDAOImpl userDao = new UserDAOImpl();
			int id = UserDAOImpl.getIdFromDbByAccount(Authorization.email);
			FrameUpdateMember updateMem = new FrameUpdateMember(userDao.select(id));
			updateMem.setVisible(true);
		} else {
			FrameSignIn login = new FrameSignIn();
			login.setVisible(true);
		}
	}

	protected void btnLogoutMouseClicked(MouseEvent e) {
		dispose();
		Authorization.setNull();
		frOut = new FrameSignIn();
		frOut.setLocation(400, 300);
		frOut.setVisible(true);
	}
}
