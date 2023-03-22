package entity;

public class Theory extends Entity {
	private Integer vocabId;
	private Integer lessonId;

	public Theory() {
		super();
	}

	public Theory(Integer vocabId, Integer lessonId) {
		super();
		this.vocabId = vocabId;
		this.lessonId = lessonId;
	}

	public Theory(Integer id, Integer vocabId, Integer lessonId) {
		super(id);
		this.vocabId = vocabId;
		this.lessonId = lessonId;
	}
	
	

	public Integer getVocabId() {
		return vocabId;
	}

	public void setVocabId(Integer vocabId) {
		this.vocabId = vocabId;
	}

	public Integer getLessonId() {
		return lessonId;
	}

	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}

	@Override
	public String toString() {
		return "Theory [id=" + id + ", vocabId=" + vocabId + ", lessonId=" + lessonId + "]";
	}

}
