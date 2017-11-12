package core;
import java.util.ArrayList;

public class Battle implements ActionHandler {
	
	private Entity entity1;
	private Entity entity2;
	private Entity actingEntity;
	
	private int battleStep;
	private int currentAction;
	private ArrayList<Action> battleActions;
	private ArrayList<Action> battleStepActions;
	
	Battle(Entity entity1, Entity entity2){
		this.entity1 = entity1;
		this.entity2 = entity2;
		
		battleActions = new ArrayList<Action>();
		battleStepActions = new ArrayList<Action>();
		currentAction = 0;
		battleStep = 0;
		
	}
	
	public ArrayList<Action> getBattleActions(){
		return this.battleActions;
	}
	
	public ArrayList<Action> getBattleStepActions(){
		return this.battleStepActions;
	}

	public boolean alive() {
		if(entity1.isAlive() && entity2.isAlive() || this.currentAction < this.battleActions.size()) {
			return true;
		}else {
			return false;
		}
	}
	
	public void Step() {
		
		
		this.battleStepActions.clear();
		
		if(this.actingEntity == null) {
			this.actingEntity = this.entity1;
		}else if(this.actingEntity == entity1){
			if(actingEntity.isAlive()) {
				this.addAction(new Action_AttemptAttack(this.entity1, this.entity2, this));
				this.executeActions();
				actingEntity = this.entity2;
			}
		}else if(this.actingEntity == entity2) {
			if(entity2.isAlive()) {
				this.addAction(new Action_AttemptAttack(this.entity2, this.entity1, this));
				this.executeActions();
				actingEntity = this.entity1;
			}
		}
		
		++this.battleStep;		
		
	}
	
	public int getBattleStep() {
		return this.battleStep;
	}

	@Override
	public void addAction(Action action) {
		battleStepActions.add(action);
		battleActions.add(action);
		
	}
	
	public void executeActions() {
		for(; this.currentAction < battleActions.size(); this.currentAction++) {
			Action action = battleActions.get(currentAction);
			action.execute();
		}
	}
	
	
}
