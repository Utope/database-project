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
public class EntityItemDrop {
    private int id;
    private Item item;
    private int dropChance;
    private int minCount;
    private int maxCount;
    
    public EntityItemDrop(int id, Item item, int dropChance, int minCount, int maxCount){
        this.id = id;
        this.item = item;
        this.dropChance = dropChance;
        this.maxCount = maxCount;
        this.minCount = minCount;
    }

    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public int getDropChance() {
        return dropChance;
    }

    public int getMinCount() {
        return minCount;
    }

    public int getMaxCount() {
        return maxCount;
    }
    
    
}
