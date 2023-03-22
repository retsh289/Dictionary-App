package entity;

public class History extends Entity {
	private Integer vocabularyId;
	private Integer userId;

	public History() {
		super();
	}

	public History(Integer vocabularyId, Integer userId) {
		this.vocabularyId = vocabularyId;
		this.userId = userId;
	}

	public History(Integer id, Integer vocabularyId, Integer userId) {
		super(id);
		this.vocabularyId = vocabularyId;
		this.userId = userId;
	}

	public Integer getVocabularyId() {
		return vocabularyId;
	}

	public void setVocabularyId(Integer vocabularyId) {
		this.vocabularyId = vocabularyId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", vocabularyId=" + vocabularyId + ", userId=" + userId + "]";
	}
}
