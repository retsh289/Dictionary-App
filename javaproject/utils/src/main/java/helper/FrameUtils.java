package helper;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameUtils {
	private static Dimension DIM = Toolkit.getDefaultToolkit().getScreenSize();
	public static void alignFrameScreenCenter(JFrame frame) {
		frame.setLocation(DIM.width/2-frame.getSize().width/2, DIM.height/2-frame.getSize().height/2);
	}
}
