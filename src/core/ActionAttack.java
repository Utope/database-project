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
public class ActionAttack extends Action {
    
    Entity attacker;
    Entity defender;
    
    public ActionAttack(int actionId, Entity entity, Action parentAction, String timestamp, Entity attacker, Entity defender) {
        super(actionId, entity, parentAction, timestamp);
        this.attacker = attacker;
        this.defender = defender;
    }

    @Override
    public void exectue() {
        
    }

}
