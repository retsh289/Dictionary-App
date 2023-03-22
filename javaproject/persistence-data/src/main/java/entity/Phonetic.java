package entity;

public class Phonetic extends Entity {
	private String content;
	private Integer vocabId;

	public Phonetic() {
		super();
	}

	public Phonetic(String content, Integer vocabId) {
		super();
		this.content = content;
		this.vocabId = vocabId;
	}

	public Phonetic(Integer id, String content, Integer vocabId) {
		super(id);
		this.content = content;
		this.vocabId = vocabId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getVocabId() {
		return vocabId;
	}

	public void setVocabId(Integer vocabId) {
		this.vocabId = vocabId;
	}

	@Override
	public String toString() {
		return "Phonetic [id=" + id + ", content=" + content + ", vocabId=" + vocabId + "]";
	}

}
