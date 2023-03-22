package helper;

import java.awt.Image;
import javax.swing.ImageIcon;

public class IconImage {
	private Image logoImg = new ImageIcon(getClass().getResource("/image/dictionary-icon.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
	private Image memberImg = new ImageIcon(getClass().getResource("/image/member.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private Image adminImg = new ImageIcon(getClass().getResource("/image/admin.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private Image vocabImg = new ImageIcon(getClass().getResource("/image/vocab.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private Image homeImg = new ImageIcon(getClass().getResource("/image/home.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private Image dashboardImg = new ImageIcon(getClass().getResource("/image/dashboard.png")).getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH);
	private Image topicImg = new ImageIcon(getClass().getResource("/image/topic.png")).getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH);
	private Image starImg = new ImageIcon(getClass().getResource("/image/star.png")).getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH);
	private Image starAltImg = new ImageIcon(getClass().getResource("/image/star-alt.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image emailImg = new ImageIcon(getClass().getResource("/image/email.png")).getImage().getScaledInstance(15,15, Image.SCALE_SMOOTH);
	private Image passwordImg = new ImageIcon(getClass().getResource("/image/password.png")).getImage().getScaledInstance(15,15, Image.SCALE_SMOOTH);
	private Image dictImg = new ImageIcon(getClass().getResource("/image/dict.png")).getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
	private Image lessonImg = new ImageIcon(getClass().getResource("/image/lesson.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private Image feedbackImg = new ImageIcon(getClass().getResource("/image/feedback.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private Image historyImg = new ImageIcon(getClass().getResource("/image/history.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private Image bookmarkImg = new ImageIcon(getClass().getResource("/image/bookmark.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private Image generalImg = new ImageIcon(getClass().getResource("/image/general.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private Image logoutImg = new ImageIcon(getClass().getResource("/image/logout.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private Image bgImg = new ImageIcon(getClass().getResource("/image/bg.png")).getImage().getScaledInstance(428, 532, Image.SCALE_SMOOTH);
	public Image getBgImg() {
		return bgImg;
	}
	public Image getFeedbackImg() {
		return feedbackImg;
	}
	public Image getLessonImg() {
		return lessonImg;
	}
	public Image getDictImg() {
		return dictImg;
	}
	public Image getLogoImg() {
		return logoImg;
	}
	public Image getMemberImg() {
		return memberImg;
	}
	public Image getAdminImg() {
		return adminImg;
	}
	public Image getVocabImg() {
		return vocabImg;
	}
	public Image getHomeImg() {
		return homeImg;
	}
	public Image getDashboardImg() {
		return dashboardImg;
	}
	public Image getTopicImg() {
		return topicImg;
	}
	public Image getStarImg() {
		return starImg;
	}
	public Image getStarAltImg() {
		return starAltImg;
	}
	public Image getEmailImg() {
		return emailImg;
	}
	public Image getPasswordImg() {
		return passwordImg;
	}
	public Image getHistoryImg() {
		return historyImg;
	}
	public Image getBookmarkImg() {
		return bookmarkImg;
	}
	public Image getGeneralImg() {
		return generalImg;
	}
	public Image getLogoutImg() {
		return logoutImg;
	}
}
