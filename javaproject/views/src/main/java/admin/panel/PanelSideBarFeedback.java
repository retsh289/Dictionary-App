package admin.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import helper.IconImage;
import service.Authorization;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelSideBarFeedback extends JPanel {
	private JPanel panelVocabContribution;
	private JPanel panelFeedback;
	private JLabel lblVocabContribution;
	private JLabel lblFeedback;

	public PanelSideBarFeedback() {
		setLayout(null);
		setBounds(0, 0, 450, 50);
		setBackground(new Color(242, 247, 255));
		IconImage icon = new IconImage();

		panelVocabContribution = new JPanel();
		panelVocabContribution.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				doPanelVocabContributionMousePressed(e);
			}
		});
		panelVocabContribution.setBorder(null);
		panelVocabContribution.setLayout(null);
		panelVocabContribution.setBackground(new Color(37, 57, 111));
		panelVocabContribution.setBounds(0, 0, 250, 50);
		add(panelVocabContribution);
		lblVocabContribution = new JLabel("Từ vựng đóng góp");
		lblVocabContribution.setForeground(new Color(255, 255, 255));
		lblVocabContribution.setFont(new Font("Arial", Font.BOLD, 16));
		lblVocabContribution.setBounds(69, 15, 160, 20);
		panelVocabContribution.add(lblVocabContribution);
		JLabel lblIconDashboard = new JLabel("");
		lblIconDashboard.setBounds(28, 10, 46, 29);
		panelVocabContribution.add(lblIconDashboard);
		lblIconDashboard.setIcon(new ImageIcon(icon.getDashboardImg()));
		
		panelFeedback = new JPanel();
		panelFeedback.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				doPanelFeedbackMousePressed(e);
			}
		});
		panelFeedback.setBorder(null);
		panelFeedback.setLayout(null);
		panelFeedback.setBackground(new Color(37, 57, 111));
		panelFeedback.setBounds(250, 0, 200, 50);
		
		
		lblFeedback = new JLabel("Phản hồi");
		lblFeedback.setForeground(new Color(255, 255, 255));
		lblFeedback.setFont(new Font("Arial", Font.BOLD, 16));
		lblFeedback.setBounds(78, 15, 94, 20);
		panelFeedback.add(lblFeedback);
		JLabel lblIconFeedback = new JLabel("");
		lblIconFeedback.setBounds(30, 10, 46, 29);
		panelFeedback.add(lblIconFeedback);
		lblIconFeedback.setIcon(new ImageIcon(icon.getBookmarkImg()));
		
		
		
		if(Authorization.loggedrole==1) {
			add(panelFeedback);
		}
	}

	public void menuChanged(JPanel panel, JLabel label) {
		Component[] components = this.getComponents();
		for(Component cpm : components) {
			cpm.setForeground(new Color(71, 171, 255));
			cpm.setBackground(new Color(71, 171, 255));
		}
		
		label.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(37, 57, 111));
	}
	public JPanel getPanelVocabContribution() {
		return panelVocabContribution;
	}

	public void setPanelVocabContribution(JPanel panelVocabContribution) {
		this.panelVocabContribution = panelVocabContribution;
	}

	public JPanel getPanelFeedback() {
		return panelFeedback;
	}

	public void setPanelFeedback(JPanel panelFeedback) {
		this.panelFeedback = panelFeedback;
	}

	public JLabel getLblVocabContribution() {
		return lblVocabContribution;
	}

	public void setLblVocabContribution(JLabel lblVocabContribution) {
		this.lblVocabContribution = lblVocabContribution;
	}

	public JLabel getLblFeedback() {
		return lblFeedback;
	}

	public void setLblFeedback(JLabel lblFeedback) {
		this.lblFeedback = lblFeedback;
	}

	protected void doPanelVocabContributionMousePressed(MouseEvent e) {
	}
	protected void doPanelFeedbackMousePressed(MouseEvent e) {
	}
	
	
}
