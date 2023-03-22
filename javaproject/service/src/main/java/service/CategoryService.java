package service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import dao.impl.CategoryDAOImpl;
import dao.impl.VocabularyDAOImpl;
import entity.Category;
import entity.Vocabulary;
import helper.ErrorMessage;
import helper.ImageUtils;
import helper.StringUtils;
import helper.Validation;

public class CategoryService {
	private CategoryDAOImpl dao;

	public CategoryService() {
		dao = new CategoryDAOImpl();
	}
	
	public boolean deleteCateFromVocab(Vocabulary vocabulary) {
		vocabulary.setCategoryId(null);
		new VocabularyDAOImpl().update(vocabulary);
		return true;
	}

	public boolean add(Map<String, String> data) {
		ErrorMessage.ERROR_MESSAGES = null;
		String name = data.get("category");
		String imageDir = data.get("image"); // C:\Users\ADMIN\OneDrive\Documents\teamwork.png

//		System.out.println(name + "\n" + imageDir);

		if (formatName(name).equals("")) {
			ErrorMessage.ERROR_MESSAGES = "Không được để trống tên chủ đề";
			return false;
		} else if (!Validation.checkLength(formatName(name), 1, 50)) {
			ErrorMessage.ERROR_MESSAGES = "Độ dài chủ đề tối đa 50 ký tự";
			return false;
		} else if (!imageDir.equals("")) {
			try {
				Path newDir = Paths
						.get(ImageUtils.pathToResource + "\\category\\" + StringUtils.fileNameFormat(name) + ".png");
				Files.copy(Paths.get(imageDir), newDir, StandardCopyOption.REPLACE_EXISTING);
			} catch (Exception e2) {
//				e2.printStackTrace();
				return false;
			}
		}
		dao.insert(new Category(name, StringUtils.fileNameFormat(name) + ".png"));
		return true;
	}

	public boolean update(Map<String, String> data) {
		ErrorMessage.ERROR_MESSAGES = null;
		Integer cateId = Integer.parseInt(data.get("id"));
		String name = data.get("category");
		String imageDir = data.get("image"); // C:\Users\ADMIN\OneDrive\Documents\admin.png

		System.out.println(name + " - " + imageDir);
		
		Category originalCate = dao.select(cateId);
//		validate
		if (formatName(name).equals("")) {
			ErrorMessage.ERROR_MESSAGES = "Không được để trống tên chủ đề";
			return false;
		} else if (!Validation.checkLength(formatName(name), 1, 50)) {
			ErrorMessage.ERROR_MESSAGES = "Độ dài chủ đề tối đa 50 ký tự";
			return false;
		} else if (formatName(name).equals(originalCate.getName()) && imageDir == null) {
			ErrorMessage.ERROR_MESSAGES = "Bạn phải thay đổi thông tin mới có thể cập nhật";
			return false;
		}

//		Logic Update
//		nếu thay đổi tên 
		String imageName = null;
		if (!formatName(name).equals(originalCate.getName())) {
//			thay đổi hình
			if (imageDir != null) {
				try {
//				 	xoa file cu
					Path oldImageFile = Paths.get(ImageUtils.pathToResource + "\\category\\" + originalCate.getName() + ".png");
					Files.deleteIfExists(oldImageFile);
//					them file moi
					Path newDir = Paths.get(ImageUtils.pathToResource + "\\category\\" + StringUtils.fileNameFormat(name) + ".png");
					Files.copy(Paths.get(imageDir), newDir, StandardCopyOption.REPLACE_EXISTING);
					imageName = StringUtils.fileNameFormat(name) + ".png";
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
//				kh thay đổi hình
				File oldImageFile = new File(ImageUtils.pathToResource + "\\category\\" + originalCate.getImageIcon());
				File newImageFile = new File(ImageUtils.pathToResource + "\\category\\" + StringUtils.fileNameFormat(name) + ".png");
				oldImageFile.renameTo(newImageFile);
				
				imageName = StringUtils.fileNameFormat(name) + ".png";
			}
		} else {
//			k thay đổi tên
			if(imageDir != null) {
				try {
					Path newDir = Paths.get(ImageUtils.pathToResource + "\\category\\" + StringUtils.fileNameFormat(name) + ".png");
					Files.copy(Paths.get(imageDir), newDir, StandardCopyOption.REPLACE_EXISTING);
					imageName = StringUtils.fileNameFormat(name) + ".png";
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		dao.update(new Category(cateId, name, imageName));
		return true;
	}

	public boolean delete(Category category) {
		try {
			List<Vocabulary> vocabs = dao.selectAllVocabByCategoryId(category.getId());
			if (vocabs != null) {
				for (Vocabulary vocab : vocabs) {
					vocab.setCategoryId(null);
					new VocabularyDAOImpl().update(vocab);
				}
			}
			dao.delete(category);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	private String formatName(String name) {
		return name.trim().replaceAll("\\s+", "_").toLowerCase();
	}
}
