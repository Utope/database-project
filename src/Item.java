
public class Item {
	private int itemId;
	private String name;
	private String description;
	
	public Item(int id, String name, String description) {
		this.itemId = id;
		this.name = name;
		this.description = description;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
