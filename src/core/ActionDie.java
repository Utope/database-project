/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author robert
 */
public class ActionDie extends Action{
    
    public Entity killedby;
    public ArrayList<ItemInstance> droppedItemInstances;
    int actionDieId;
    
    public ActionDie(Entity entity, Action parentAction, String timestamp, Battle battle, Entity killedby) {
        super(entity, parentAction, timestamp, battle);
        this.killedby = killedby;
    }
    
    public int geActionDieId(){
        return this.actionDieId;
    }
    
    public Entity getKilledBy(){
        return this.killedby;
    }
    
    public void setActionDieId(int id){
        this.actionDieId = id;
    }

    @Override
    public void exectue() {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        this.droppedItemInstances = ItemManager.Instance().getEntityInventory(this.entity);
        for(ItemInstance itemInstance : droppedItemInstances){
            this.battle.addAction(new ActionDropItem(
                    this.entity, 
                    this, 
                    timestamp,
                    itemInstance,
                    this.battle
            ));
        }
    }

    @Override
    public String getLog() {
       String log = "[" + this.timestamp + "]";
       log+= this.entity.getName() + " was killed by " + this.killedby.getName() + "!\n";
       return log;
    }
    
    
    
}
