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
import java.util.List;
import java.util.Map;

import dao.QuestionDAO;
import dao.impl.AnswerDAOImpl;
import dao.impl.LessonDAOImpl;
import dao.impl.QuestionDAOImpl;
import dao.impl.TheoryDAOImpl;
import entity.Answer;
import entity.Lesson;
import entity.Question;
import entity.Theory;
import helper.ErrorMessage;
import helper.ImageUtils;
import helper.StringUtils;
import helper.Validation;

public class LessonService {
	private LessonDAOImpl lsDAO;
	private TheoryDAOImpl thDAO;
	private QuestionDAOImpl qsDAO;
	private AnswerDAOImpl ansDAO;
	
	public LessonService() {
		lsDAO = new LessonDAOImpl();
		thDAO = new TheoryDAOImpl();
		qsDAO = new QuestionDAOImpl();
		ansDAO = new AnswerDAOImpl();
	}
	public boolean add(Map<String, Object> data) {
		String title = (String) data.get("title");
		String imageDir = (String) data.get("image");
		
//		đã validate questions and answers 
		List<HashMap<String, String>> questionAndAnswers = (ArrayList<HashMap<String, String>>) data.get("questionAndAnswers");
		
		
//		validate title 
		if(!title.equals("")) {
			if(!Validation.checkLength(title, 1, 100)) {
				ErrorMessage.ERROR_MESSAGES = "Độ dài tiêu đề tối đa 100 ký tự";
				return false;
			}
		}  else {
			ErrorMessage.ERROR_MESSAGES = "Ô tiêu đề không được để trống!";
			return false;
		}
		
//		save file
		String imageName = null;
		if (imageDir != null) {
			try {
				ImageUtils.saveFile(imageDir, "lesson", StringUtils.fileNameFormat(title), "png");
				imageName = StringUtils.fileNameFormat(title) + ".png";
			} catch (Exception e2) {
//				e2.printStackTrace();
				return false;
			}
		}
		
//		insert
		Lesson ls = new Lesson(title, imageName);
		Integer insertedLsId = lsDAO.insertGetId(ls);
		
		for(HashMap<String, String> qAndA : questionAndAnswers) {
			if(qAndA != null) {
//				qAndA.forEach((key, value) -> {
//				  System.out.println(key + "=" + value );  
//				});
				Integer vocabId = Integer.parseInt(qAndA.get("vocabId"));
				thDAO.insert(new Theory(vocabId, insertedLsId)) ;
				insertQsAndAns(qAndA.get("question1"), qAndA.get("answer1"), insertedLsId);
				insertQsAndAns(qAndA.get("question2"), qAndA.get("answer2"), insertedLsId);
				insertQsAndAns(qAndA.get("question3"), qAndA.get("answer3"), insertedLsId);
			}
		}
		return true;
	}
	
	
	public boolean update(Map<String, Object> data) {
		Integer lsId = Integer.parseInt((String) data.get("id"));
		String title = (String) data.get("title");
		String imageDir = (String) data.get("image");
		
		Lesson originLesson = lsDAO.select(lsId);
//		đã validate questions and answers 
		List<HashMap<String, String>> questionAndAnswers = (ArrayList<HashMap<String, String>>) data.get("questionAndAnswers");
		
//		validate title 
		if(!title.equals("")) {
			if(!Validation.checkLength(title, 1, 100)) {
				ErrorMessage.ERROR_MESSAGES = "Độ dài tiêu đề tối đa 100 ký tự";
				return false;
			}
		}  else {
			ErrorMessage.ERROR_MESSAGES = "Ô tiêu đề không được để trống!";
			return false;
		}
		
//		update name
		String imageName = null;
		if(!title.equals(originLesson.getTitle())){
//			update img
			if(imageDir != null) {
				try {
//					remove old file 
					File oldImageFile = new File(ImageUtils.pathToResource + "\\lesson\\" + StringUtils.fileNameFormat(originLesson.getTitle()) + ".png");
					Files.deleteIfExists(oldImageFile.toPath());

//					them file moi
					Path newDir = Paths.get(ImageUtils.pathToResource + "\\lesson\\" + StringUtils.fileNameFormat(title) + ".png");
					Files.copy(Paths.get(imageDir), newDir, StandardCopyOption.REPLACE_EXISTING);
				} catch (Exception e2) {
//					e2.printStackTrace();
					return false;
				}
			} else {
//				ko update img
				File oldImageFile = new File(
						ImageUtils.pathToResource + "\\lesson\\" + originLesson.getImage());
				File newImageFile = new File(ImageUtils.pathToResource + "\\lesson\\" + StringUtils.fileNameFormat(title) + ".png");
				oldImageFile.renameTo(newImageFile);
			}
			imageName = StringUtils.fileNameFormat(title) + ".png";
		} else {
			if(imageDir != null) {
				Path newDir = Paths.get(ImageUtils.pathToResource + "\\lesson\\" + StringUtils.fileNameFormat(title) + ".png");
				try {
					Files.copy(Paths.get(imageDir), newDir, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
				imageName = StringUtils.fileNameFormat(title) + ".png";
			} else {
				imageName = originLesson.getImage();
			}
		}
		
		Lesson ls = new Lesson(lsId, title, imageName);
		lsDAO.update(ls);
		
		deleteOnOtherTables(ls);
		insertOnOtherTables(ls, questionAndAnswers);
		
		return true;
	}
	
	private void insertOnOtherTables(Lesson ls, List<HashMap<String, String> >questionAndAnswers) {
		for(HashMap<String, String> qAndA : questionAndAnswers) {
			if(qAndA != null) {
//				qAndA.forEach((key, value) -> {
//				  System.out.println(key + "=" + value );  
//				});
				Integer vocabId = Integer.parseInt(qAndA.get("vocabId"));
				thDAO.insert(new Theory(vocabId, ls.getId())) ;
				insertQsAndAns(qAndA.get("question1"), qAndA.get("answer1"), ls.getId());
				insertQsAndAns(qAndA.get("question2"), qAndA.get("answer2"), ls.getId());
				insertQsAndAns(qAndA.get("question3"), qAndA.get("answer3"), ls.getId());
			}
		}
	}
	
	private void deleteOnOtherTables(Lesson ls) {
		List<Question> questions = new QuestionDAOImpl().selAllQuestionByLessonId(ls.getId());
		List<Theory> theories = new TheoryDAOImpl().selAllTheoriesByLessonId(ls.getId());
		
		for(Question qs : questions) {
			List<Answer> answers = new AnswerDAOImpl().selAllAnswerByQuestionId(qs.getId());
			answers.stream().forEach(an -> ansDAO.delete(an));
			qsDAO.delete(qs);
		}
		
		theories.stream().forEach(th -> thDAO.delete(th));
	}
	
	public boolean delete(Lesson ls) {
		try {
			List<Question> questions = new QuestionDAOImpl().selAllQuestionByLessonId(ls.getId());
			List<Theory> theories = new TheoryDAOImpl().selAllTheoriesByLessonId(ls.getId());
			
			for(Question qs : questions) {
				List<Answer> answers = new AnswerDAOImpl().selAllAnswerByQuestionId(qs.getId());
				answers.stream().forEach(an -> ansDAO.delete(an));
				qsDAO.delete(qs);
			}
			
			theories.stream().forEach(th -> thDAO.delete(th));
			
			lsDAO.delete(ls);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private void insertQsAndAns(String qs, String ans, Integer lessonId) {
		Question question = new Question(qs, lessonId); 
		Integer insertedQsId = qsDAO.insertGetId(question);
		List<String> answers = Arrays.asList(ans.split(";"));
		
		boolean isTrue = true;
		for(String an : answers) {
			an = an.toLowerCase().trim();
			Answer answer = new Answer(an, insertedQsId, isTrue);
			ansDAO.insert(answer);
			isTrue = false;
		}
	}
	
	private boolean validateLesson(String title) {
		if(!title.equals("")) {
			if(!Validation.checkLength(title, 1, 100)) {
				ErrorMessage.ERROR_MESSAGES = "Độ dài tiêu đề tối đa 100 ký tự";
				return false;
			}
		}  else {
			ErrorMessage.ERROR_MESSAGES = "Ô tiêu đề không được để trống!";
			return false;
		}
		
		
		return true;
	}
}
