/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import database.Repository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author robert
 */
public class Battle {
    public Entity playerEntity;
    public Entity computerEntity;
    
    ArrayList<Action> completedActions;
    Queue<Action> queuedActions;
    ArrayList<Action> stepActions;
    
    public Battle(Entity playerEntity, Entity computerEntity){
        this.playerEntity = playerEntity;
        this.computerEntity = computerEntity;
        completedActions = new ArrayList<>();
        queuedActions = new LinkedList<>();
        stepActions = new ArrayList<>();
    }
    
    public boolean isBattleOver(){
        System.out.println(computerEntity);
        System.out.println(playerEntity);
        return computerEntity.getCurrentHealth() <= 0 || playerEntity.getCurrentHealth() <= 0;
    }
    
    public ArrayList<Action> getCompletedActions(){
        return this.completedActions;
    }
    
    public void battleStep(){
        queuedActions.add(new ActionAttack(playerEntity, null, new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),this.computerEntity,this));
        queuedActions.add(new ActionAttack(computerEntity, null, new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()), this.playerEntity, this));
    }
    
    public void addAction(Action action){
        queuedActions.add(action);
    }
    
    public ArrayList<Action> getStepActions(){
        return this.stepActions;
    }
    
    public void pollQueuedActions(){
        Action action = null;
        
        stepActions.clear();
        
        while((action = queuedActions.poll()) != null) {
            action.exectue();
            stepActions.add(action);
            this.completedActions.add(action);
        }
    }
    
    public void saveBattle(Action action){
        
    }
    
    public static void main(String[] args){
        ItemManager.Instance().init();
        PlayerManager.Instance().init();
        EntityManager.Instance().init();
        
        
        Player player = PlayerManager.Instance().findPlayerByUsername("test");
        
        if(player == null){
            player = PlayerManager.Instance().createPlayer("test", "test");
            
            EntityManager.Instance().createEntity(
                    EntityManager.Instance().getRandomEntityType(), 
                    player
            );
        }
        
        Entity entity = EntityManager.Instance().getCurrentPlayerEntity(player);
        
        System.out.println(entity);

        
        Entity entity2 = EntityManager.Instance().createEntity(
               EntityManager.Instance().findEntityTypeByName("Skeleton"), 
                null
        );
        
        Battle battle = new Battle(EntityManager.Instance().getCurrentPlayerEntity(player), entity2);
        
        while(!battle.isBattleOver()){
            battle.battleStep();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Battle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for(Action action : battle.completedActions){
            System.out.println(action.getLog());
        }
        
        Repository.Instance().saveBattle(battle);
    }
    
}
