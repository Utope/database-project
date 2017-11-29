/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author robert
 */
public class EntityType {
    private int id;
    private String name;
    private String description;
    private int base_attack;
    private int base_defense;
    private int base_hit;
    private int base_health;
    
    public EntityType(int id, String name, String description, int base_attack, int base_defense, int base_hit, int base_health){
        this.id = id;
        this.name = name;
        this.description = description;
        this.base_attack = base_attack;
        this.base_defense = base_defense;
        this.base_hit = base_hit;
        this.base_health = base_health;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getBase_attack() {
        return base_attack;
    }

    public int getBase_defense() {
        return base_defense;
    }

    public int getBase_hit() {
        return base_hit;
    }

    public int getBase_health() {
        return base_health;
    }
    
    @Override
    public String toString(){
        return "[" + this.getId() + ", " + this.getName() + "]";
    }
    
    
}
