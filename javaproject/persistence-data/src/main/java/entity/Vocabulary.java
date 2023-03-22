package entity;

public class Vocabulary extends Entity {
	private String word;
	private String image;
	private String pronunciation;

	private Integer categoryId;
	private Integer wordTypeId;

	public Vocabulary() {
		super();
	}

	public Vocabulary(String word, String image, String pronunciation, Integer categoryId, Integer wordTypeId) {
		this.word = word;
		this.pronunciation = pronunciation;
		this.image = image;
		this.categoryId = categoryId;
		this.wordTypeId = wordTypeId;
	}

	public Vocabulary(Integer id, String word, String image, String pronunciation, Integer categoryId,
			Integer wordTypeId) {
		super(id);
		this.word = word;
		this.pronunciation = pronunciation;
		this.image = image;
		this.categoryId = categoryId;
		this.wordTypeId = wordTypeId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getPronunciation() {
		return pronunciation;
	}

	public void setPronunciation(String pronunciation) {
		this.pronunciation = pronunciation;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getWordTypeId() {
		return wordTypeId;
	}

	public void setWordTypeId(Integer wordTypeId) {
		this.wordTypeId = wordTypeId;
	}

	@Override
	public String toString() {
		return "Vocabulary [id=" + id + ", word=" + word + ", image=" + image + ", pronunciation=" + pronunciation
				+ ", categoryId=" + categoryId + ", wordTypeId=" + wordTypeId + "]";
	}
}
