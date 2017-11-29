package core.commands;

import java.util.Random;
import core.Entity;
import core.Game;

public class Action_AttemptAttack extends ICommand implements EntityAction, Creates_EntityAttack {
	Entity attacker;
	Entity defender;
	
	public Action_AttemptAttack(Entity attacker, Entity defender, ICommand_Creator source) {
		super(source);
		this.attacker = attacker;
		this.defender = defender;
	}

	@Override
	public void execute() {
		// add a hit chance / attack speed / attack chance so the battle looks more realistic
		Random rand = new Random(System.currentTimeMillis());
		if((rand.nextInt() % 10) > 3) {
			entityAttack(attacker, defender);
		}

	}

	@Override
	public void entityAttack(Entity attacker, Entity defender) {
		Game.getCommandHandler().add(new Action_Attack(attacker,defender,this));
	}
}
