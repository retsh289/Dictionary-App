package entity;

public class UserLessonResult extends Entity {
	private Integer userId;
	private Integer lessonId;
	private Integer point;

	public UserLessonResult() {
		super();
	}

	public UserLessonResult(Integer userId, Integer lessonId, Integer point) {
		super();
		this.userId = userId;
		this.lessonId = lessonId;
		this.point = point;
	}

	public UserLessonResult(Integer id, Integer userId, Integer lessonId, Integer point) {
		super(id);
		this.userId = userId;
		this.lessonId = lessonId;
		this.point = point;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getLessonId() {
		return lessonId;
	}

	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "UserLessonResult [id=" + id + ", userId=" + userId + ", lessonId=" + lessonId + ", point=" + point
				+ "]";
	}

}
