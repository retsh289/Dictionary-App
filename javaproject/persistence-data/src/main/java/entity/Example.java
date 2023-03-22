package entity;

public class Example extends Entity {
	private String content;
	private String meaning;
	private Integer meaningId;

	public Example() {
		super();
	}

	public Example(String content, String meaning, Integer meaningId) {
		super();
		this.content = content;
		this.meaning = meaning;
		this.meaningId = meaningId;
	}

	public Example(Integer id, String content, String meaning, Integer meaningId) {
		super(id);
		this.content = content;
		this.meaning = meaning;
		this.meaningId = meaningId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public Integer getMeaningId() {
		return meaningId;
	}

	public void setMeaningId(Integer meaningId) {
		this.meaningId = meaningId;
	}

	@Override
	public String toString() {
		return "Example [id=" + id + ", content=" + content + ", meaning=" + meaning + ", meaningId=" + meaningId + "]";
	}
}
