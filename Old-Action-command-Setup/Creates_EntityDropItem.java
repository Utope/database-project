package core.commands;

import core.Entity;
import core.Item;

public interface Creates_EntityDropItem extends ICommand_Creator {
	void entityDropItem(Entity entity, Item item);
}
