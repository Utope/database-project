/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author robert
 */
public class ActionHandler {
    private static ActionHandler actionHandler = new ActionHandler();
    
    public static ActionHandler Instance(){
        return ActionHandler.actionHandler;
    }
    
    ArrayList<Action> completedActions;
    Queue<Action> queuedActions;
    
    private ActionHandler(){
        completedActions = new ArrayList<>();
        queuedActions = new LinkedList<>();
    }
    
    public void addAction(Action action){
        queuedActions.add(action);
    }
    
    public void pollQueuedActions(){
        Action action = null;
        
        while((action = queuedActions.poll()) != null) {
            action.exectue();
        }
    }
    

    
    
    
}
