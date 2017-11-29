/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import database.Repository;
import java.sql.SQLException;
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
        this.items = new ArrayList<>();
        this.item_instances = new Inventory();
    }
    
    public static ItemManager Instance(){
        return ItemManager.itemManager;
    }
    
    //control how items
    public boolean addItem(Item item){
        items.add(item);
        return true;
    }
    
    //control how item instances are added
    public boolean addItem_Instance(ItemInstance itemInst){
        this.item_instances.add(itemInst);
        return true;
    }
    
    public void init(){
        try {
            Repository.Instance().loadItems();
            Repository.Instance().loadItemInstances();
        } catch (SQLException ex) {
            Logger.getLogger(ItemManager.class.getName()).log(Level.SEVERE, null, ex);
        }
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
