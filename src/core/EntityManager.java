/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import database.Repository;
import java.util.ArrayList;
import java.util.Iterator;

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
    }
    
    public void init(){
        entityTypes = Repository.Instance().getAllEntityTypes();
        entitys = Repository.Instance().getAllEntitys();
    }
    
    public Entity createEntity(EntityType entityType, Player player){
        Entity entity = Repository.Instance().createEntity(entityType, player);
        if(entity != null){
            entitys.add(entity);
        }
        return entity;
    }
    
    //TODO
    //public EntityType createEntityType(){
      //  
   // }
    
    public EntityType getEntityTypeById(int id){
        Iterator it = entityTypes.iterator();
        while(it.hasNext()){
            EntityType entityType = (EntityType) it.next();
            if(entityType.getId() == id){
                return entityType;
            }
        }
        return null;
    }
    
    public Entity getEntityById(int id){
        Iterator it = entitys.iterator();
        while(it.hasNext()){
            Entity entity = (Entity) it.next();
            if(entity.getEntityId() == id){
                return entity;
            }
        }
        return null;
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
