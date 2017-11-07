
public class Monster extends Entity {
	private int monsterId;
	
	public Monster(int entityId, String name, int maxHealth, int damage) {
		super(entityId, name, maxHealth, damage);
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
	
}
