/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author robert
 */
public class ItemInstance {
    
    private int item_Instance_Id;
    private String timestamp;
    private Item item;
    private Player player;
    private Entity entity;
    
    public ItemInstance(int id, Item item, String timestamp, Player player, Entity entity){
        this.item = item;
        this.timestamp = timestamp;
        this.item_Instance_Id = id;
        this.entity = entity;
        this.player = player;
    }
    
    public Player getPlayer(){
        return this.player;
    }
    
    public Entity getEntity(){
        return this.entity;
    }
    
    public void setPlayer(Player player){
        this.player = player;
    }
    
    public void setEntity(Entity entity){
        this.entity = entity;
    }
    
    public int getItemInstanceId(){
        return this.item_Instance_Id;
    }
    
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    public String toString(){
        return "[" + this.item_Instance_Id + ", " + item.getName() + ", " + timestamp + "]";
    }
    
}
