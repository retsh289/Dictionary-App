package admin.panel;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import admin.gui.FrameDashboard;
import service.Authorization;

public class PanelResponse extends JPanel {
	private JPanel topBar;
	private PanelMainContent mainContent;
	
	public FrameDashboard frameParent;
	private PanelFeedback panelFeedback;
	private PanelVocabContribution panelVocabContribution;
	
	private static PanelResponse myInstance;
	
	public static PanelResponse getMyInstance() {
		if (myInstance == null) {
			myInstance = new PanelResponse();
		}
		return myInstance;
	}	
	
	private class SideBarMenu extends PanelSideBarFeedback{
		public void doPanelVocabContributionMousePressed(MouseEvent e) {
			panelVocabContribution = new PanelVocabContribution();
			panelVocabContribution.panelParent = getOuter();
			menuClicked(panelVocabContribution);
			menuChanged(getPanelVocabContribution(), getLblVocabContribution());
		}
		public void doPanelFeedbackMousePressed(MouseEvent e) {
			if(Authorization.loggedrole == 1 ) {
				panelFeedback = new PanelFeedback();
				panelFeedback.panelParent = getOuter();
				menuClicked(panelFeedback);
				menuChanged(getPanelFeedback(),getLblFeedback());
			} else {
				JOptionPane.showMessageDialog(this, "Bạn không đủ quyền hạn để thực hiện!");
			}
		}
		
	}
	
	public PanelResponse() {
		setLayout(null);
		setBounds(0, 0, 1085, 729);
		setBackground(new Color(242, 247, 255));
		
		topBar = new JPanel();
		topBar.setBackground(new Color(255, 255, 255));
		topBar.setBounds(43, 0, 450, 50);
		topBar.setLayout(null);
		add(topBar);
		SideBarMenu sideBarMenu = new SideBarMenu();
		topBar.add(sideBarMenu);
		
		mainContent = new PanelMainContent();
		mainContent.setBounds(0, 61, 1085, 668);
		mainContent.setLayout(null);		
		add(mainContent);
		
		panelVocabContribution = new PanelVocabContribution();
		panelVocabContribution.setBounds(0, 0, 1085, 668);
		panelFeedback = new PanelFeedback();
		panelFeedback.setBounds(0, 0, 1085, 668);
		
		mainContent.add(panelVocabContribution);
		mainContent.add(panelFeedback);
		
		sideBarMenu.doPanelVocabContributionMousePressed(null);
	}
	
	private PanelResponse getOuter() {
		return PanelResponse.this;
	}
	
	public void callPanel(JPanel panel) {
		//remove
		mainContent.removeAll();
		mainContent.repaint();
		mainContent.revalidate();
		//repaint
		mainContent.add(panel);
		mainContent.repaint();
		mainContent.revalidate();
	}
	private void menuClicked(JPanel panel) {
		callPanel(panel);
	}
}
