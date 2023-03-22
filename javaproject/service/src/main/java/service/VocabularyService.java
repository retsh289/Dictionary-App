package service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dao.PhoneticDAO;
import dao.impl.BookmarkDAOImpl;
import dao.impl.CategoryDAOImpl;
import dao.impl.ExampleDAOImpl;
import dao.impl.HistoryDAOImpl;
import dao.impl.MeaningDAOImpl;
import dao.impl.PhoneticDAOImpl;
import dao.impl.RelativeWordDAOImpl;
import dao.impl.TheoryDAOImpl;
import dao.impl.VocabularyDAOImpl;
import entity.Bookmark;
import entity.Example;
import entity.History;
import entity.Meaning;
import entity.Phonetic;
import entity.RelativeWord;
import entity.Vocabulary;
import helper.ErrorMessage;
import helper.ImageUtils;
import helper.StringUtils;
import helper.Validation;

public class VocabularyService {
	private VocabularyDAOImpl dao;

	public VocabularyService() {
		dao = new VocabularyDAOImpl();
	}

	public boolean add(Map<String, Object> data) {
		ErrorMessage.ERROR_MESSAGES = null;
		String word = (String) data.get("word");
		String type = (String) data.get("type");
		String relative = (String) data.get("relatives"); // can be null
		String phonetic = (String) data.get("phonetic"); // can be null
		String category = (String) data.get("category"); // can be null
		String imageDir = (String) data.get("image"); // can be null //C:\Users\ADMIN\OneDrive\Documents\admin.png
		String pronunciationDir = (String) data.get("pronunciation"); // can be null

		List<HashMap<String, String>> meaningAndExamples = (ArrayList<HashMap<String, String>>) data.get("meaningAndEx");

//		VALIDATE
		if (!validateVocab(word, relative, phonetic, meaningAndExamples)) {
			return false;
		}

//		SAVE FILES
		if (imageDir != null) {
			try {
				ImageUtils.saveFile(imageDir, "vocabulary", StringUtils.fileNameFormat(word), "png");
			} catch (Exception e2) {
//				e2.printStackTrace();
				return false;
			}
		}

		if (pronunciationDir != null) {
			try {
				ImageUtils.saveFile(pronunciationDir, "pronunciation", StringUtils.fileNameFormat(word), "mp3");
			} catch (Exception e2) {
				e2.printStackTrace();
				return false;
			}
		}

		insertVocab(word, type, category, imageDir, pronunciationDir, relative, phonetic, meaningAndExamples);

		return true;
	}

	private void insertVocab(String word, String type, String category, String imageDir, String pronunciationDir,
			String relative, String phonetic, List<HashMap<String, String>> meaningAndExamples) {
		// INSERT
		Integer cateId = new CategoryDAOImpl().getIdFromCateName(category.toLowerCase()) == -1 ? null
				: new CategoryDAOImpl().getIdFromCateName(category.toLowerCase());
		String imageName = null;
		if (imageDir != null) {
			imageName = StringUtils.fileNameFormat(word + ".png");
		}
		String pronunciationName = null;
		if (pronunciationDir != null) {
			pronunciationName = StringUtils.fileNameFormat(word + ".mp3");
		}

		// insert into vocabulary
		Vocabulary vocab = new Vocabulary(word, imageName, pronunciationName, cateId, Integer.parseInt(type));
		Integer insertedVocabId = dao.insertGetId(vocab);

		// insert into relatives
		if (!relative.trim().equals("")) {
			List<String> relativeWords = Arrays.asList(relative.split(";"));
			RelativeWordDAOImpl relDao = new RelativeWordDAOImpl();
			relativeWords.forEach(rel -> {
				if (!rel.trim().equals("")) {
					relDao.insert(new RelativeWord(null, rel.trim(), insertedVocabId));
				}
			});
		}

		// insert into phonetic
		if (!phonetic.trim().equals("")) {
			List<String> phonetics = Arrays.asList(phonetic.split(";"));
			PhoneticDAO phDAO = new PhoneticDAOImpl();
			phonetics.forEach(ph -> {
				if (!ph.trim().equals("")) {
					phDAO.insert(new Phonetic(ph.trim().toLowerCase(), insertedVocabId));
				}
			});
		}

		// insert into meanings and example
		int i = 0;

		for (HashMap<String, String> mnEx : meaningAndExamples) {
			String mn = mnEx.get("meaning");
			String ex = mnEx.get("example");

			if (!mn.trim().equals("")) {
				// insert to meaning
				Integer insertedMeaningId = new MeaningDAOImpl().insertGetId(new Meaning(mn, insertedVocabId));

				// insert to example
				if (!ex.trim().equals("")) {
					List<String> exampleRow = Arrays.asList(ex.split("\\r?\\n"));
					for (String str : exampleRow) {
						if (!exampleRow.isEmpty()) {
							List<String> exampleRowDetail = Arrays.asList(str.split(";"));
							// have only example or meaning of example
							if (exampleRowDetail.size() >= 2) {
								new ExampleDAOImpl().insert(new Example(exampleRowDetail.get(0),
										exampleRowDetail.get(1), insertedMeaningId));
							}
						}
					}
				}
			}
		}
	}

	private boolean validateVocab(String word, String relative, String phonetic,
			List<HashMap<String, String>> meaningAndExamples) {
		if (word.equals("")) {
			ErrorMessage.ERROR_MESSAGES = "Ô từ vựng không được để trống!";
			return false;
		} else if (!relative.equals("") && !Validation.checkLength(relative, 1, 300)) {
			ErrorMessage.ERROR_MESSAGES = "Độ dài các từ liên quan tối đa 200 ký tự";
			return false;
		} else if (!phonetic.equals("") && !Validation.checkLength(phonetic, 1, 300)) {
			ErrorMessage.ERROR_MESSAGES = "Độ dài các phiên âm tối đa 200 ký tự";
			return false;
		}

		for (HashMap<String, String> mnEx : meaningAndExamples) {
			if (!mnEx.get("meaning").equals("") && !Validation.checkLength(mnEx.get("meaning"), 1, 50)) {
				ErrorMessage.ERROR_MESSAGES = "Độ dài của các ô ý nghĩa tối đa 50 ký tự";
				return false;
			}
			if (!mnEx.get("example").equals("") && !Validation.checkLength(mnEx.get("example"), 1, 400)) {
				ErrorMessage.ERROR_MESSAGES = "Độ dài của các ô ví dụ tối đa 400 ký tự";
				return false;
			}
		}
		return true;
	}

	public boolean update(Map<String, Object> data) {
		ErrorMessage.ERROR_MESSAGES = null;
		Integer id = Integer.parseInt((String) data.get("id"));
		String word = (String) data.get("word");
		String type = (String) data.get("type");
		String relative = (String) data.get("relatives"); // can be null
		String phonetic = (String) data.get("phonetic"); // can be null
		String category = (String) data.get("category"); // can be null
		String imageDir = (String) data.get("image"); // can be null //C:\Users\ADMIN\OneDrive\Documents\admin.png
		String pronunciationDir = (String) data.get("pronunciation"); // can be null
		List<HashMap<String, String>> meaningAndExamples = (ArrayList<HashMap<String, String>>) data
				.get("meaningAndEx");

		Integer cateId = new CategoryDAOImpl().getIdFromCateName(category.toLowerCase()) == -1 ? null
				: new CategoryDAOImpl().getIdFromCateName(category.toLowerCase());
		Vocabulary originalVocab = new VocabularyDAOImpl().select(id);

//		Validation
		if (!validateVocab(word, relative, phonetic, meaningAndExamples)) {
			return false;
		}

		String imageName = null;
		String pronunciationName = null;
//		Sửa tên từ vựng
		if (!formatName(word).equals(originalVocab.getWord())) {
//			update img 
			if (imageDir != null) {
				try {
//					xoa file cu
					File oldImageFile = new File(ImageUtils.pathToResource + "\\vocabulary\\" + originalVocab.getWord() + ".png");
					Files.deleteIfExists(oldImageFile.toPath());

//					them file moi
					Path newDir = Paths.get(ImageUtils.pathToResource + "\\vocabulary\\" + StringUtils.fileNameFormat(word) + ".png");
					Files.copy(Paths.get(imageDir), newDir, StandardCopyOption.REPLACE_EXISTING);
				} catch (Exception e2) {
//					e2.printStackTrace();
					return false;
				}
			} else {
//				ko update img
				File oldImageFile = new File(
						ImageUtils.pathToResource + "\\vocabulary\\" + originalVocab.getImage());
				File newImageFile = new File(ImageUtils.pathToResource + "\\vocabulary\\" + formatName(word) + ".png");
				oldImageFile.renameTo(newImageFile);
			}
			imageName = StringUtils.fileNameFormat(word) + ".png";

//			update pronun
			if (pronunciationDir != null) {
				try {
					Path newDir = Paths.get(ImageUtils.pathToResource + "\\pronunciation\\" + StringUtils.fileNameFormat(word) + ".mp3");
					Path a = Files.copy(Paths.get(pronunciationDir), newDir, StandardCopyOption.REPLACE_EXISTING);
				} catch (Exception e2) {
					e2.printStackTrace();
					return false;
				}
			} else {
//				ko update pronun
				File oldImageFile = new File(
						ImageUtils.pathToResource + "\\pronunciation\\" + originalVocab.getWord() + ".mp3");
				File newImageFile = new File(
						ImageUtils.pathToResource + "\\pronunciation\\" + formatName(word) + ".mp3");
				oldImageFile.renameTo(newImageFile);
			}
			pronunciationName = StringUtils.fileNameFormat(word) + ".mp3";

		} else {
//			ko sửa tên từ vựng
			if(imageDir != null) {
				Path newDir = Paths.get(ImageUtils.pathToResource + "\\vocabulary\\" + StringUtils.fileNameFormat(word) + ".png");
				try {
					Files.copy(Paths.get(imageDir), newDir, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				imageName = word + ".png";
			}
			
			if(pronunciationDir != null) {
				try {
					Path newDir = Paths.get(ImageUtils.pathToResource + "\\pronunciation\\" + StringUtils.fileNameFormat(word) + ".mp3");
					Path a = Files.copy(Paths.get(pronunciationDir), newDir, StandardCopyOption.REPLACE_EXISTING);
				} catch (Exception e2) {
					e2.printStackTrace();
					return false;
				}
				pronunciationName = word + ".mp3";
			}
		}
//		
		Vocabulary vocab = new Vocabulary(word, imageName, pronunciationName, cateId, Integer.parseInt(type));
		vocab.setId(id);
		dao.update(vocab);
//		
//		
//		
		deleteOnOtherTable(originalVocab);
		insertOtherTables(relative, phonetic, meaningAndExamples, vocab);
		return true;
	}

	private void insertOtherTables(String relative, String phonetic, List<HashMap<String, String>> meaningAndExamples,
			Vocabulary vocab) {
		// // insert into relatives
		if (!relative.trim().equals("")) {
			List<String> relativeWords = Arrays.asList(relative.split(";"));
			RelativeWordDAOImpl relDao = new RelativeWordDAOImpl();
			relativeWords.forEach(rel -> {
				if (!rel.trim().equals("")) {
					relDao.insert(new RelativeWord(null, rel.trim(), vocab.getId()));
				}
			});
		}

		// insert into phonetic
		if (!phonetic.trim().equals("")) {
			List<String> phonetics = Arrays.asList(phonetic.split(";"));
			PhoneticDAO phDAO = new PhoneticDAOImpl();
			phonetics.forEach(ph -> {
				if (!ph.trim().equals("")) {
					phDAO.insert(new Phonetic(ph.trim().toLowerCase(), vocab.getId()));
				}
			});
		}

		// insert into meanings and example
		int i = 0;

		for (HashMap<String, String> mnEx : meaningAndExamples) {
			String mn = mnEx.get("meaning");
			String ex = mnEx.get("example");

			if (!mn.trim().equals("")) {
				// insert to meaning
				Integer insertedMeaningId = new MeaningDAOImpl().insertGetId(new Meaning(mn, vocab.getId()));

				// insert to example
				if (!ex.trim().equals("")) {
					List<String> exampleRow = Arrays.asList(ex.split("\\r?\\n"));
					for (String str : exampleRow) {
						if (!exampleRow.isEmpty()) {
							List<String> exampleRowDetail = Arrays.asList(str.split(";"));
							// have only example or meaning of example
							if (exampleRowDetail.size() >= 2) {
								new ExampleDAOImpl().insert(new Example(exampleRowDetail.get(0),
										exampleRowDetail.get(1), insertedMeaningId));
							}
						}
					}
				}
			}
		}
	}

	private boolean checkExampleRowDetailMeaningIsNotNull(List<String> exampleRows) {
		for (String row : exampleRows) {
			List<String> rowDetail = Arrays.asList(row.split(";"));
			for (String item : rowDetail) {
				if (!item.trim().equals("")) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean delete(Vocabulary vocab) {
		VocabularyDAOImpl vocabDao = new VocabularyDAOImpl();
		MeaningDAOImpl meanDao = new MeaningDAOImpl();

		try {
			List<Meaning> meanings = vocabDao.selectAllMeaningByVocabId(vocab.getId());
				// delete meaning
			for (Meaning mean : meanings) {
				List<Example> examples = new MeaningDAOImpl().selectAllExampleByMeaningId(mean.getId());
				// delete example
				ExampleDAOImpl exDao = new ExampleDAOImpl();
				examples.stream().forEach(e -> exDao.delete(e));
				meanDao.delete(mean);
			}

//			delete relatives
			RelativeWordDAOImpl relDao = new RelativeWordDAOImpl();
			List<RelativeWord> relatives = vocabDao.selectAllRelativesByVocabId(vocab.getId());
			relatives.stream().forEach(rel -> relDao.delete(rel));
			
//			delete phonetics
			PhoneticDAOImpl phoneticDao = new PhoneticDAOImpl();
			List<Phonetic> phonetics = vocabDao.selAllPhoneticByVocabId(vocab.getId());
			phonetics.stream().forEach(ph -> phoneticDao.delete(ph));
			
//			delete bookmark
			BookmarkDAOImpl bmDao = new BookmarkDAOImpl();
			List<Bookmark> bookmarks = bmDao.selBookmarkByVocabId(vocab.getId());
			bookmarks.stream().forEach(bm -> bmDao.delete(bm));

//			delete history
			HistoryDAOImpl hstrDao = new HistoryDAOImpl();
			List<History> histories = hstrDao.selHistoryByVocabId(vocab.getId());
			histories.stream().forEach(h -> hstrDao.delete(h));
			
			
//			delete theory
			TheoryDAOImpl thDAO = new TheoryDAOImpl();
			thDAO.deleteByVocabId(vocab.getId());
//			delete vocabulary
			vocabDao.delete(vocab);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private String formatName(String name) {
		return name.trim().replaceAll("\\s+", "_").toLowerCase();
	}

	private void deleteOnOtherTable(Vocabulary vocab) {
		List<RelativeWord> originalRelatives = dao.selectAllRelativesByVocabId(vocab.getId());
		originalRelatives.forEach(rel -> new RelativeWordDAOImpl().delete(rel));

		List<Phonetic> originalPhonetics = dao.selAllPhoneticByVocabId(vocab.getId());
		originalPhonetics.forEach(ph -> new PhoneticDAOImpl().delete(ph));

		List<Meaning> originalMeanings = dao.selectAllMeaningByVocabId(vocab.getId());
		for (Meaning mn : originalMeanings) {
			List<Example> originalExamples = new MeaningDAOImpl().selectAllExampleByMeaningId(mn.getId());
			originalExamples.forEach(ex -> new ExampleDAOImpl().delete(ex));
			new MeaningDAOImpl().delete(mn);
		}
	}
}
