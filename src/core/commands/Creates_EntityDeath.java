package core.commands;
import core.Entity;

public interface Creates_EntityDeath extends ICommand_Creator {
	void entityDeath(Entity killed, Entity killedBy);
}
