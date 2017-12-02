/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author robert
 */
public class ActionDropItem extends Action {

    ItemInstance itemInstnace;
    int actionDropItemId;
    
    public ActionDropItem(Entity entity, Action parentAction, String timestamp, ItemInstance itemInstance, Battle battle) {
        super(entity, parentAction, timestamp, battle);
        this.itemInstnace = itemInstance;
    }
    
    public ItemInstance getItemInstance(){
        return this.itemInstnace;
    }
    
    public int getActionDropItemId(){
        return this.actionDropItemId;
    }
    
    public void setActionDropItemId(int id){
        this.actionDropItemId = id;
    }

    @Override
    public void exectue() {
        ItemManager.Instance().setItemInstanceOwner(itemInstnace, this.battle.playerEntity.getPlayer());
    }

    @Override
    public String getLog() {
        String log = "[" + this.timestamp + "]";
        log+= this.entity.getName() + " dropped " + this.itemInstnace.getItem().getName();
        return log;
    }
    
    
}
