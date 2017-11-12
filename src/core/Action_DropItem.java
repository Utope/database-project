package core;
import java.util.ArrayList;

public class Action_DropItem extends Action {

	Entity entity;
	Loot loot;
	
	public Action_DropItem(Entity entity, Loot loot, ActionHandler handler) {
		super(handler);
		this.entity = entity;
		this.loot = loot;
	}
	
	@Override
	public void execute() {
		//Add to player items
	}

	@Override
	public String textView() {
		return entity.getName() + " has dropped " + loot.getItem().getName();
		
	}

}
