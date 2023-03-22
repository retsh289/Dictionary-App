package entity;

public class Feedback extends Entity {
	private String content;
	private Integer userId;

	public Feedback() {
		super();
	}

	public Feedback(String content, Integer userId) {
		super();
		this.content = content;
		this.userId = userId;
	}

	public Feedback(Integer id, String content, Integer userId) {
		super(id);
		this.content = content;
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", content=" + content + ", userId=" + userId + "]";
	}

}
