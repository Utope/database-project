package core;

import java.util.ArrayList;

public class Entity {
	private int entityId;
        private EntityType entityType;
        private Player player;
        private String name;
	private int maxHealth;
	private int currentHealth;
	private int attack;
        private int defense;
        private int hit;
        

    public Entity(int entityId, Player player, EntityType entityType, String name, int maxHealth, int currentHealth, int attack, int defense, int hit) {
        this.entityId = entityId;
        this.player = player;
        this.entityType = entityType;
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.attack = attack;
        this.defense = defense;
        this.hit = hit;
    }
    
        @Override
    public String toString(){
        return "[Entity Instance Id =" + entityId + "\n"
                + "\t" + "name=" + name + "\n"
                +"\t" + "currentHealth=" + currentHealth + "\n"
                +"\t" + "player=" + player + "\n"
                + "\t" + "EntityType=" + entityType.toString() + "\n";
                       
    }
    
    public void takeDamage(int damage){
        this.currentHealth = this.currentHealth - damage;
        if(this.currentHealth < 0){
            this.currentHealth = 0;
        }
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
