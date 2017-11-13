package core.commands;

import core.Entity;
import core.Game;

public class Action_Attack extends ICommand implements EntityAction, CommandOutput, Creates_EntityDeath{

	Entity attacker;
	Entity receiver;
	
	int damage;
	boolean finishingBlow;
	
	public Action_Attack(Entity attacker, Entity reciever, ICommand_Creator source) {
		super(source);
		this.attacker = attacker;
		this.receiver = reciever;
	}
	
	@Override
	public void execute() {
		if(attacker.isAlive()) {
			damage = attacker.getDamage();
			receiver.setCurrentHealth(receiver.getCurrentHealth() - attacker.getDamage());
			if(!receiver.isAlive()) {
				this.finishingBlow = true;
				this.entityDeath(this.receiver, this.attacker);
			}
		}
		
	}

	@Override
	public String output() {
		return attacker.getName() + " Dealt " + attacker.getDamage() + " Dmg to " + receiver.getName(); 
	}

	@Override
	public void entityDeath(Entity killed, Entity killedBy) {
		Game.getCommandHandler().add(new Action_Death(killed, killedBy, this));
	}

}
