import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Battle {
	
	private Entity entity1;
	private Entity entity2;
	
	private Queue<Action> allBattleActions;
	private Queue<Action> battleStepActions;
	
	
	Battle(Entity entity1, Entity entity2){
		this.entity1 = entity1;
		this.entity2 = entity2;
		
		allBattleActions = new LinkedList<Action>();
		battleStepActions = new LinkedList<Action>();
		
	}
	
	public Queue<Action> getAllBattleActions() {
		return this.allBattleActions;
	}
	
	public Entity winner() {
		if(entity1.isAlive() && !entity2.isAlive()) {
			return entity1;
		}else if(entity2.isAlive() && !entity1.isAlive()) {
			return entity2;
		}else {
			return null;
		}
	}
	
	public boolean alive() {
		if(entity1.isAlive() && entity2.isAlive() || !battleStepActions.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	public void Step() {
		Action action1 = new Action_Attack(entity1, entity2);
		Action action2 = new Action_Attack(entity2, entity1);
		
		allBattleActions.add(action1);
		allBattleActions.add(action2);
		
		battleStepActions.add(action1);
		battleStepActions.add(action2);
		
		while(!battleStepActions.isEmpty()) {
			Action action = battleStepActions.poll();
			action.execute();
			
			for(Action newAction : action.createdActions) {
				battleStepActions.add(newAction);
				allBattleActions.add(newAction);
			}
		}
		
		
	}
}
