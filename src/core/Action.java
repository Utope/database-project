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
    int actionId;
    Entity entity; // related entity means spawned by or parent spawned by
    //Battle battle;
    Action parentAction;
    String timestamp;

    public Action(int actionId, Entity entity, Action parentAction, String timestamp) {
        this.actionId = actionId;
        this.entity = entity;
        this.parentAction = parentAction;
        this.timestamp = timestamp;
    }

    public int getActionId() {
        return actionId;
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
   
}
