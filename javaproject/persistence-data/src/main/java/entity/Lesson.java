package entity;

public class Lesson extends Entity {
	private String title;
	private String image;

	public Lesson() {
		super();
	}

	public Lesson(String title, String image) {
		super();
		this.image = image;
		this.title = title;
	}

	public Lesson(Integer id, String title, String image) {
		super(id);
		this.image = image;
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Lesson [id=" + id + ", image=" + image + ", title=" + title + "]";
	}

}
