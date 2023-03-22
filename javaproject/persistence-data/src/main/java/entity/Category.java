package entity;

public class Category extends Entity {
	private String name;
	private String imageIcon;

	public Category() {
		super();
	}

	public Category(String name, String imageIcon) {
		this.name = name;
		this.imageIcon = imageIcon;
	}

	public Category(Integer id, String name, String imageIcon) {
		super(id);
		this.name = name;
		this.imageIcon = imageIcon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(String imageIcon) {
		this.imageIcon = imageIcon;
	}

	@Override
	public String toString() {
		return "Categories [id=" + id + ", name=" + name + ", imageIcon=" + imageIcon + "]";
	}

}
