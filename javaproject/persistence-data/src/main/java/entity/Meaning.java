package entity;

public class Meaning extends Entity {
	private String content;
	private Integer vocabularyId;

	public Meaning() {
		super();
	}

	public Meaning(String content, Integer vocabularyId) {
		super();
		this.content = content;
		this.vocabularyId = vocabularyId;
	}

	public Meaning(Integer id, String content, Integer vocabularyId) {
		super(id);
		this.content = content;
		this.vocabularyId = vocabularyId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getVocabularyId() {
		return vocabularyId;
	}

	public void setVocabularyId(Integer vocabularyId) {
		this.vocabularyId = vocabularyId;
	}

	@Override
	public String toString() {
		return "Meaning [id=" + id + ", content=" + content + ", vocabularyId=" + vocabularyId + "]";
	}

}
