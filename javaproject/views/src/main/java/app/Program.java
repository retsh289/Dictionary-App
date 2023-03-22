package app;

import java.awt.EventQueue;

import dao.impl.UserDAOImpl;
import entity.User;
import home.gui.FrameSignIn;

public class Program {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FrameSignIn frame = new FrameSignIn();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
	}
}
