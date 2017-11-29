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
public class EntityManager {
    private static EntityManager entityManager = new EntityManager();
    
    private ArrayList<EntityType> entityTypes;
    private ArrayList<Entity> entitys;
    
    public static EntityManager Instance(){
        return EntityManager.entityManager;
    }
    
    private EntityManager(){
        this.entitys = new ArrayList<>();
        this.entityTypes = new ArrayList<>();
    }
    
    public void init(){
    
        try {
            Repository.Instance().loadEntityTypes();
            Repository.Instance().loadEntitys();
        } catch (SQLException ex) {
            Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //Verification on if can add can be added here
    public boolean addEntityType(EntityType entityType){
        this.entityTypes.add(entityType);
        return true;
    }
    
    //Verification on if can add can be added here
    public boolean addEntity(Entity entity){
        this.entitys.add(entity);
        return true;
    }
    
    public ArrayList<Entity> getEntitys(){
        return this.entitys;
    }
    
    public ArrayList<EntityType> getEntityTypes(){
        return this.entityTypes;
    }
    
    /*
    below is iterator methods
    */
    
    public Iterator getEntitysIterator(){
        return entitys.iterator();
    }
    
    public Iterator getEntityTypesIterator(){
        return entityTypes.iterator();
    }
}
