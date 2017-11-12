package core;

public class Loot {
	Item item;
	float dropChance;
	
	public Loot(Item item, float dropChance) {
		this.item = item;
		this.dropChance = dropChance;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public float getDropChance() {
		return dropChance;
	}

	public void setDropChance(float dropChance) {
		this.dropChance = dropChance;
	}
	
}
