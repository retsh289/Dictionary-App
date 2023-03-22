package entity;

public class WordType extends Entity {
	private String type;

	public WordType() {
		super();
	}

	public WordType(String type) {
		super();
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "WordType [id=" + id + ", type=" + type + "]";
	}

}
