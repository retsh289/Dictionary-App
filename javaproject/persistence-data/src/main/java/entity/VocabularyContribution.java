package entity;

public class VocabularyContribution  extends Entity{
	private String word;
	private String meaning;
	private Integer userId;
	public VocabularyContribution() {
		super();
	}
	public VocabularyContribution(String word, String meaning, Integer userId) {
		super();
		this.word = word;
		this.meaning = meaning;
		this.userId = userId;
	}
	public VocabularyContribution(Integer id, String word, String meaning, Integer userId) {
		super(id);
		this.word = word;
		this.meaning = meaning;
		this.userId = userId;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "VocabularyContribution [id=" + id + ", word=" + word + ", meaning=" + meaning + ", userId=" + userId
				+ "]";
	}
	
	
}
