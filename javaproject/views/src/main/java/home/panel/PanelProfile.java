package home.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

import admin.panel.PanelMember;
import home.gui.FrameHome;
import home.panel.info.PanelBookmark;
import home.panel.info.PanelHistory;
import home.panel.info.PanelRespose;
import home.panel.info.PanelProfileBar;
import home.panel.info.PanelInfoMember;

public class PanelProfile extends JPanel {

	private JPanel panel;
	private PanelHistory history;
	private PanelBookmark bookmark;
	private PanelInfoMember member;
	private PanelRespose response;
	private JPanel panelMainContent;
	public FrameHome frameParent;

	private static PanelProfile myInstance;

	public static PanelProfile getMyInstance() {
		if (myInstance == null) {
			myInstance = new PanelProfile();
		}
		return myInstance;
	}

	class ProfileBar extends PanelProfileBar {
		protected void doPanelMemberMouseClicked(MouseEvent e) {
			member = PanelInfoMember.getMyInstance();
			menuClicked(member);
			menuChanged(getPanelMember(), getLblMember());
		}

		protected void doPanelBookmarkMouseClicked(MouseEvent e) {
			bookmark = new PanelBookmark();
			menuClicked(bookmark);
			menuChanged(getPanelBookmark(), getLblBookmark());
		}

		protected void doPanelHistoryMouseClicked(MouseEvent e) {
			history = new PanelHistory();
			menuClicked(history);
			menuChanged(getPanelHistory(), getLblHistory());
		}

		protected void doPanelResposeMouseClicked(MouseEvent e) {
			response = PanelRespose.getMyInstance();
			menuClicked(response);
			menuChanged(getPanelRespose(), getLblRespose());
		}
	}

	public PanelProfile() {
		initComponent();
	}

	private void initComponent() {
		setBorder(null);
		setBackground(new Color(37, 57, 111));
		setBounds(0, 0, 1302, 702);
		setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 0, 1282, 691);
		add(panel);
		panel.setLayout(null);

		// SideBae
		JPanel panelSideBar = new JPanel();
		panelSideBar.setBackground(new Color(255, 255, 255));
		panelSideBar.setBounds(0, 0, 240, 691);
		panel.add(panelSideBar);
		panelSideBar.setLayout(null);

		ProfileBar profile = new ProfileBar();
		profile.setSize(240, 691);
		panelSideBar.add(profile);

		// Main Content Panel
		panelMainContent = new JPanel();
		panelMainContent.setBackground(new Color(242, 247, 255));
		panelMainContent.setBounds(240, 0, 1042, 691);
		panelMainContent.setLayout(new BorderLayout());
		panel.add(panelMainContent);
		
		member = PanelInfoMember.getMyInstance();
		panelMainContent.add(member, BorderLayout.CENTER);
		profile.doPanelMemberMouseClicked(null);
	}

	public void callPanel(JPanel panel) {
		// remove
		panelMainContent.removeAll();
		panelMainContent.repaint();
		panelMainContent.revalidate();
		// repaint
		panelMainContent.add(panel);
		panelMainContent.repaint();
		panelMainContent.revalidate();
	}

	public void menuClicked(JPanel panel) {
		callPanel(panel);
	}

}
