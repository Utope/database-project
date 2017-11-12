package core;

public class Action_Attack extends Action {

	Entity attacker;
	Entity receiver;
	
	int damage;
	boolean finishingBlow;
	
	public Action_Attack(Entity attacker, Entity reciever, ActionHandler handler) {
		super(handler);
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
				this.handler.addAction(new Action_Death(receiver, attacker, handler));
			}
		}
		
	}

	@Override
	public String textView() {
		return attacker.getName() + " Dealt " + attacker.getDamage() + " Dmg to " + receiver.getName(); 
	}

}
