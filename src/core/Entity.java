package core;

public class Entity {
	private int entityId;
        private EntityType entityType;
        private Player player;
	private Inventory inventory;
        private String name;
	private int maxHealth;
	private int currentHealth;
	private int attack;
        private int defense;
        private int hit;
        

    public Entity(int entityId, Player player, EntityType entityType, Inventory inventory, String name, int maxHealth, int currentHealth, int attack, int defense, int hit) {
        this.entityId = entityId;
        this.player = player;
        this.inventory = inventory;
        this.entityType = entityType;
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.attack = attack;
        this.defense = defense;
        this.hit = hit;
    }
    
    public Inventory getInventory(){
        return this.inventory;
    }
    
    public EntityType getEntityType(){
        return this.entityType;
    }

    public int getEntityId() {
        return entityId;
    }

    public Player getPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHit() {
        return hit;
    }


        
}
