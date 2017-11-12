package core;

import java.util.Random;

public class Action_AttemptAttack extends Action {
	Entity attacker;
	Entity defender;
	
	public Action_AttemptAttack(Entity attacker, Entity defender, ActionHandler handler) {
		super(handler);
		this.attacker = attacker;
		this.defender = defender;
	}

	@Override
	public void execute() {
		// add a hit chance / attack speed / attack chance so the battle looks more realistic
		Random rand = new Random(System.currentTimeMillis());
		if((rand.nextInt() % 2) == 1) {
			handler.addAction(new Action_Attack(attacker, defender, handler));
		}

	}

	@Override
	public String textView() {
		// TODO Auto-generated method stub
		return null;
	}
}
