import java.util.ArrayList;

public class Battle {
	
	private Entity entity1;
	private Entity entity2;
	
	private int currentAction;
	private ArrayList<Action> battleActions;
	
	Battle(Entity entity1, Entity entity2){
		this.entity1 = entity1;
		this.entity2 = entity2;
		
		battleActions = new ArrayList<Action>();
		currentAction = 0;
		
	}
	
	public ArrayList<Action> getBattleActions(){
		return this.battleActions;
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
		if(entity1.isAlive() && entity2.isAlive() || currentAction < battleActions.size()) {
			return true;
		}else {
			return false;
		}
	}
	
	public void Step() {
		this.battleActions.add(new Action_Attack(entity1, entity2));
		
		
		for(; this.currentAction < battleActions.size(); this.currentAction++) {
			Action action = battleActions.get(currentAction);
			action.execute();
			
			for(Action newAction : action.createdActions) {
				battleActions.add(newAction);
			}
		}
		
		if(entity2.isAlive()) {
			this.battleActions.add(new Action_Attack(entity2, entity1));
			
			for(; this.currentAction < battleActions.size(); this.currentAction++) {
				Action action = battleActions.get(currentAction);
				action.execute();
				
				for(Action newAction : action.createdActions) {
					battleActions.add(newAction);
				}
			}
		}
		
		
		
	}
}
