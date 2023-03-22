package helper;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageUtils {
	public static Path pathToResource= Paths.get(".").normalize().toAbsolutePath().getParent().getParent().resolve("resources");

//	use when upload img frame
	public static ImageIcon getImageByFile(File f, final int ROW_HEIGHT) {
		if (f != null) {
			try {
				BufferedImage bimg = ImageIO.read(f);
				int imgWidth = bimg.getWidth();
				int imgHeight = bimg.getHeight();
				int rowWidth = (ROW_HEIGHT * imgWidth) / imgHeight;
				return new ImageIcon(new ImageIcon(f.getAbsolutePath()).getImage().getScaledInstance(rowWidth,
						ROW_HEIGHT, Image.SCALE_SMOOTH));
			} catch (Exception e) {
			}
		}
		return null;
	}
	
	public static boolean saveFile(String oldDir, String folder, String fileName, String fileExtension) {
		Path newDir = Paths.get(pathToResource + "\\" + folder + "\\" + StringUtils.fileNameFormat(fileName) + "." + fileExtension);
		try {
			Files.copy(Paths.get(oldDir), newDir, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static ImageIcon getImageByURL(String folderName, String imageName, final int ROW_HEIGHT) {
		URL imageUrl = null;
		try {
			imageUrl = new URL("file:/" + pathToResource.toString() + "/" + folderName + "/" + imageName);
		} catch (MalformedURLException e1) {
//			e1.printStackTrace();
		}
		if (imageUrl != null) {
			try {
				BufferedImage bimg = ImageIO.read(imageUrl);
				int imgWidth = bimg.getWidth();
				int imgHeight = bimg.getHeight();
				int rowWidth = (ROW_HEIGHT * imgWidth) / imgHeight;
				return new ImageIcon(
						new ImageIcon(imageUrl).getImage().getScaledInstance(rowWidth, ROW_HEIGHT, Image.SCALE_SMOOTH));
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
		return null;
	}
}
