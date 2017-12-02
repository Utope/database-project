/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.Queue;

/**
 *
 * @author robert
 */
public abstract class Action{
    Entity entity; // related entity means spawned by or parent spawned by
    //Battle battle;
    Action parentAction;
    String timestamp;
    Battle battle;
    int actionId;

    public Action(Entity entity, Action parentAction, String timestamp, Battle battle) {
        this.entity = entity;
        this.parentAction = parentAction;
        this.timestamp = timestamp;
        this.battle = battle;
    }
    
    public int getId(){
        return this.actionId;
    }
    
    public void setId(int id){
        this.actionId = id;
    }
    
    public Entity getEntity() {
        return entity;
    }

    public Action getParentAction() {
        return parentAction;
    }

    public String getTimestamp() {
        return timestamp;
    }
    
    public abstract void exectue();
    public abstract String getLog();
   
}
