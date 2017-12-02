/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.Random;

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
    
    public boolean roleDrop(){
        Random random = new Random();
        if(dropChance > (random.nextInt() % 11)){
            return true;
        }else{
            return false;
        }
    }
    
    public int roleDropCount(){
        Random random = new Random();
        int amount = random.nextInt() % (maxCount + 1);
        if(amount  < minCount){
            amount = minCount;
        }
        return amount;
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
    
    public String toString(){
        
        return "[" + getId() + "," + item.getName() + "," + dropChance + ", " + minCount + ", " + maxCount + "]"; 
    }
    
    
}
