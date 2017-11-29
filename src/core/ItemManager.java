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
    private ArrayList<ItemInstance> item_instances;
    
    
    private ItemManager(){
        this.items = new ArrayList<>();
        this.item_instances = new ArrayList<>();
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

    public ArrayList<ItemInstance> getItem_instances() {
        return item_instances;
    }
    
    public void printContents(){
        Iterator it = items.iterator();
        System.out.println("Items");
        while(it.hasNext()){
            System.out.println(it.next());
        }
        
        Iterator iti = item_instances.iterator();
        System.out.println("Item_Instances");
        while(iti.hasNext()){
            System.out.println(iti.next());
        }
    }
    
    public static void main(String[] args){
        ItemManager.Instance().init();
        ItemManager.Instance().printContents();
        
        
    }
}
