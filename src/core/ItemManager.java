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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author robert
 */
public class ItemManager {
    
    private static ItemManager itemManager = new ItemManager();
        
    private ArrayList<Item> items;
    private Inventory item_instances;
    
    
    private ItemManager(){
      
    }
    
    public static ItemManager Instance(){
        return ItemManager.itemManager;
    }
    
    public Item findItemById(int id){
        Iterator it = items.iterator();;
        while(it.hasNext()){
            Item item = (Item) it.next();
            if(item.getItemId() == id){
                return item;
            }
        }
        return null;
    }
    
    public ItemInstance findItemInstanceById(int id){
        Iterator it = item_instances.iterator();
        while(it.hasNext()){
            ItemInstance itemInstance = (ItemInstance) it.next();
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
    
    public ItemInstance createItemInstance(Item item, String timestamp){
        ItemInstance itemInstance = Repository.Instance().createItemInstance(item, timestamp);
        if(itemInstance != null){
            item_instances.add(itemInstance);
        }
        return itemInstance;
    }
    
    public void init(){
       items = Repository.Instance().getAllItems();
       item_instances = (Inventory) Repository.Instance().getAllItemInstances();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Inventory getItem_instances() {
        return item_instances;
    }

    /*
    All Iterators and methods to get iterators are below here
    */
    
    public Iterator instanceIteratorName(String name){
        return this.item_instances.ItemNameIterator(name);
    }
    
    public Iterator instanceIteratorItemInstanceTypeId(int id){
        return this.item_instances.ItemIdIterator(id);
    }
    
    public class EntityInstanceItemsIterator implements Iterator{
        
        int index;
        
        @Override
        public boolean hasNext() {
            while(index  < item_instances.size()){
                ItemInstance itemInstance = item_instances.get(index);
                boolean temp = false;
                
                Iterator it = EntityManager.Instance().getEntitysIterator();
                while(it.hasNext()){
                    Entity entity = (Entity) it.next();
                    if(entity.getInventory().contains(itemInstance)){
                        temp = true;
                    }
                }
                
                if(temp){
                    return true;
                }else{
                    index++;
                }
            }
            
            return false;
        }

        @Override
        public Object next() {
            return item_instances.get(index++);
        }
    }
    
    public class PlayerInstanceItemsIterator implements Iterator{
        
        int index;
        
        @Override
        public boolean hasNext() {
            while(index  < item_instances.size()){
                ItemInstance itemInstance = item_instances.get(index);
                boolean temp = false;
                
                Iterator it = PlayerManager.Instance().getPlayerIterator();
                while(it.hasNext()){
                    Player player = (Player) it.next();
                    if(player.getInventory().contains(itemInstance)){
                        temp = true;
                    }
                }
                
                if(temp){
                    return true;
                }else{
                    index++;
                }
            }
            return false;
        }

        @Override
        public Object next() {
           return item_instances.get(index);
        }
        
    }
    
    
   
}
