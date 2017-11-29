/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.sql.Timestamp;

/**
 *
 * @author robert
 */
public class ItemInstance {
    
    private int item_Instance_Id;
    private String timestamp;
    private Item item;
    
    public ItemInstance(int id, Item item, String timestamp){
        this.item = item;
        this.timestamp = timestamp;
        this.item_Instance_Id = id;
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
