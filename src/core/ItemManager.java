/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import database.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author robert
 */
public class ItemManager {
    
    private static ItemManager itemManager = new ItemManager();
        
    private ArrayList<Item> items;
    private ArrayList<ItemInstance> item_instances;
    
    
    private ItemManager(){
      
    }
    
    
    
    public static ItemManager Instance(){
        return ItemManager.itemManager;
    }
    
    public void setItemInstanceOwner(ItemInstance itemInstance, Object inventoryHolder){
       Repository.Instance().addInstanceItemToInventory(itemInstance, inventoryHolder);
    }
    
    public Item findItemByName(String name){
        for(Item item : this.items){
            if(item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }
    
    public String getTimestamp(){
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }
    
    public Item findItemById(int id){
        Iterator it = items.iterator();
        while(it.hasNext()){
            Item item = (Item) it.next();
            if(item.getItemId() == id){
                return item;
            }
        }
        return null;
    }
    
    public ItemInstance findItemInstanceById(int id){
        for (ItemInstance itemInstance : item_instances) {
            if(itemInstance.getItemInstanceId() == id){
                return itemInstance;
            }
        }
        return null;
    }
    
    public Item createItem(String name, String description){
        Item item = Repository.Instance().createItem(name, description);
        if(item != null){
            items.add(item);
        }
        return item;
        
    }
    
    public ArrayList<ItemInstance> getEntityInventory(Entity entity){
        ArrayList<ItemInstance> itemInstances = new ArrayList<ItemInstance>();
        
        for(ItemInstance itemInstance : this.item_instances){
            Entity ent = itemInstance.getEntity();
            if(ent.getEntityId() == entity.getEntityId()){
                    itemInstances.add(itemInstance);
                }
            }
        return itemInstances;
    }
    
    public ItemInstance createItemInstance(Item item, String timestamp){
        ItemInstance itemInstance = Repository.Instance().createItemInstance(item, timestamp);
        if(itemInstance != null){
            item_instances.add(itemInstance);
        }
        return itemInstance;
    }
    
    public void init(){
       items = Repository.Instance().getAllItems();
       item_instances =  Repository.Instance().getAllItemInstances();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<ItemInstance> getItem_instances() {
        return item_instances;
    }
    
    public ArrayList<ItemInstance> getPlayerInventory(Player player){
        ArrayList<ItemInstance> itemInstances = new ArrayList<ItemInstance>();
        
        for(ItemInstance itemInstance : item_instances){
            if(itemInstance.getPlayer() != null && itemInstance.getPlayer().getPlayerId() == player.getPlayerId()){
                itemInstances.add(itemInstance);
            }
        }
        return itemInstances;
    }
    
    public ArrayList<ItemInstance> getPlayerInventorys(){
        ArrayList<ItemInstance> itemInstances = new ArrayList<ItemInstance>();
        
        for(ItemInstance itemInstance : item_instances){
            if(itemInstance.getPlayer() != null){
                itemInstances.add(itemInstance);
            }
        }
        return itemInstances;
    }
    
    public ArrayList<ItemInstance> getEntityInventorys(){
        ArrayList<ItemInstance> itemInstances = new ArrayList<ItemInstance>();
        
        for(ItemInstance itemInstance : item_instances){
            if(itemInstance.getEntity() != null){
                itemInstances.add(itemInstance);
            }
        }
        return itemInstances;
    }

       public ArrayList<ItemInstance> getAllInventorys(){
        ArrayList<ItemInstance> itemInstances = new ArrayList<ItemInstance>();
        
        for(ItemInstance itemInstance : item_instances){
            if(itemInstance.getEntity() != null || itemInstance.getPlayer() != null){
                itemInstances.add(itemInstance);
            }
        }
        return itemInstances;
    }
       
    
   
}
