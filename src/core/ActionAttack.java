/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author robert
 */
public class ActionAttack extends Action {
    
    Entity defender;
    
    boolean complete;
    int damageDone;
    boolean succesful;
    int actionAttackId;
    
    public ActionAttack(Entity entity, Action parentAction, String timestamp, Entity defender, Battle battle) {
        super(entity, parentAction, timestamp, battle);
        this.defender = defender;
        this.succesful = false;
    }
    
    public int getDamageDone(){
        return this.damageDone;
    }
    
    public boolean getSuccesful(){
        return this.succesful;
    }
    
    public int getActionAttackId(){
        return this.actionAttackId;
    }
    
    public void setActionAttackId(int id){
        this.actionAttackId = id;
    }

    @Override
    public void exectue() {
        Random rand = new Random();
        if (entity.getHit() > (rand.nextInt() %11)){
            this.succesful = true;
            int damage = entity.getAttack() - defender.getDefense();
            
            if(damage <= 0){
                damage = 1;
            }
            this.damageDone = damage;
            defender.takeDamage(damage);
            if(defender.getCurrentHealth() <= 0){
                battle.addAction(new ActionDie( 
                        this.defender,this,
                        new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()), 
                        this.battle, 
                        this.entity));
            }
            this.complete = true;
        }
    }

    @Override
    public String getLog() {
        String log = "[" + this.timestamp + "]";
        
        String entityString = this.entity.getName() + "(" + this.entity.getEntityType().getName() + ")";
        String defenderString = this.defender.getName() + "(" + this.defender.getEntityType().getName() + ")";
        
        if(this.succesful){
            log += entityString + " did " + this.damageDone + " damage to " + defenderString;
        }else{
            log += entityString + " missed " + defenderString;
        }
        
        log+="\n";
        
        return log;
    }
    
    

}
