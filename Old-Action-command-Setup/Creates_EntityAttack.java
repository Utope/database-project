package core.commands;

import core.Entity;

public interface Creates_EntityAttack extends ICommand_Creator {
	void entityAttack(Entity attacker, Entity defender);
}
