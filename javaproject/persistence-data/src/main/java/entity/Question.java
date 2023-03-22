package entity;

public class Question extends Entity {
	private String content;
	private Integer lessonId;

	public Question() {
		super();
	}

	public Question(String content, Integer lessonId) {
		super();
		this.content = content;
		this.lessonId = lessonId;
	}

	public Question(Integer id, String content, Integer lessonId) {
		super(id);
		this.content = content;
		this.lessonId = lessonId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getLessonId() {
		return lessonId;
	}

	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", content=" + content + ", lessonId=" + lessonId + "]";
	}

}
