package core;

public class Hero extends Entity {
	private int heroId;
	
	public Hero(int entityId, String name, int maxHealth, int damage) {
		super(entityId, name, maxHealth, damage);
	}
	
	public int getHeroId() {
		return heroId;
	}
	
	public void setHeroId(int id) {
		this.heroId = id;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Hero) {
			if(this.heroId == ((Hero)obj).heroId) {
				return super.equals(obj);
			}
		}
		return false;
	}
}
