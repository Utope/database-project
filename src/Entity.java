
public abstract class Entity {
	private int entityId;
	
	private String name;
	private int maxHealth;
	private int currentHealth;
	private int damage;

	public Entity(int entityId, String name, int maxHealth, int damage){
		this.entityId = entityId;
		this.name = name;
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
		this.damage = damage;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Entity) {
			if(this.entityId == ((Entity)obj).entityId) {
				return super.equals(obj);
			}
		}
		return false;
	}
	
	public boolean isAlive() {
		if(currentHealth > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getEntityId(){
		return this.entityId;
	}
	
	public void setEntityId(int id) {
		this.entityId = id;
	}
}
