package core.commands;
import core.Entity;

public interface Creates_EntityAttemptAttack extends ICommand_Creator {
	void entityAttemptAttack(Entity attacker, Entity defender);
}
