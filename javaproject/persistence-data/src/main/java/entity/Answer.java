package entity;

public class Answer extends Entity {
	private String content;
	private Integer questionId;
	private Boolean isTrue;

	public Answer() {
		super();
	}

	public Answer(String content, Integer questionId, Boolean isTrue) {
		super();
		this.content = content;
		this.questionId = questionId;
		this.isTrue = isTrue;
	}

	public Answer(Integer id, String content, Integer questionId, Boolean isTrue) {
		super(id);
		this.content = content;
		this.questionId = questionId;
		this.isTrue = isTrue;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Boolean getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(Boolean isTrue) {
		this.isTrue = isTrue;
	}

	@Override
	public String toString() {
		return "Answer [content=" + content + ", questionId=" + questionId + ", isTrue=" + isTrue + "]";
	}

}
