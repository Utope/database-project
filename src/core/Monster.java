package core;
import java.util.ArrayList;

public class Monster extends Entity {
	private int monsterId;
	private ArrayList<Loot> loots;
	
	public Monster(int entityId, String name, int maxHealth, int damage) {
		super(entityId, name, maxHealth, damage);
		loots = new ArrayList<Loot>();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Monster) {
			if(this.monsterId == ((Monster)obj).monsterId) {
				return super.equals(obj);
			}
		}
		return false;
	}

	public int getMonsterId() {
		return monsterId;
	}

	public void setMonsterId(int monsterId) {
		this.monsterId = monsterId;
	}
	
	public void addLoot(Loot loot) {
		this.loots.add(loot);
	}
	
	public ArrayList<Loot> getLoots() {
		return this.loots;
	}
	
}
