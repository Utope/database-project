package core;
import java.util.ArrayList;

import core.commands.Action_AttemptAttack;
import core.commands.Creates_EntityAttemptAttack;
import core.commands.ICommand;

public class Battle implements Creates_EntityAttemptAttack {
	
	private Entity entity1;
	private Entity entity2;
	private Entity attacker;
	private Entity defender;
	
	private ArrayList<ICommand> battleActions;
	
	Battle(Entity entity1, Entity entity2){
		this.entity1 = entity1;
		this.entity2 = entity2;
		this.attacker= entity1;
		this.defender = entity2;
		
		battleActions = new ArrayList<ICommand>();
		
	}
	
	public ArrayList<ICommand> getBattleActions(){
		return this.battleActions;
	}

	public boolean alive() {
		if(entity1.isAlive() && entity2.isAlive()) {
			return true;
		}else {
			return false;
		}
	}
	
	public void step() {
		
		if(attacker.isAlive()) {
			this.entityAttemptAttack(attacker, defender);
		}
		
		swapAttackingAndDefendingEntitys();
		
	}
	
	private void swapAttackingAndDefendingEntitys() {
		Entity temp = this.attacker;
		this.attacker = this.defender;
		this.defender = temp;
	}
	
	@Override
	public void entityAttemptAttack(Entity attacker, Entity defender) {
		Action_AttemptAttack actionAttemptAttack = new Action_AttemptAttack(attacker,defender,this);
		Game.getCommandHandler().add(actionAttemptAttack);
		this.battleActions.add(actionAttemptAttack);
	}
	
	
}
