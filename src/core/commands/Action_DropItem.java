package core.commands;

import core.Entity;
import core.Game;
import core.Item;

public class Action_DropItem extends ICommand implements EntityAction, CommandOutput {

	Entity entity;
	Item item;
	
	public Action_DropItem(Entity entity, Item item, ICommand_Creator source) {
		super(source);
		this.entity = entity;
		this.item = item;
	}
	
	@Override
	public void execute() {
		Game.getPlayer().getInventory().add(item);
	}

	@Override
	public String output() {
		return entity.getName() + " has dropped " + item.getName();
		
	}

}
