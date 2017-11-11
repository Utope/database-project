import java.util.ArrayList;

public class Action_DropItem extends Action {

	Entity entity;
	ArrayList<Loot> droppedLoot;
	
	public Action_DropItem(Entity entity) {
		this.entity = entity;
		droppedLoot = new ArrayList<Loot>();
	}
	
	@Override
	public void execute() {
		if(entity instanceof Monster) {
			droppedLoot = ((Monster)entity).getLoots();
		}
	}

	@Override
	public String textView() {
		
		String itemNames = "[";
		
		for(Loot loot : droppedLoot) {
			itemNames = itemNames + loot.getItem().getName() + ',';
		}
		itemNames = itemNames + "]";
		
		return entity.getName() + " has dropped an item(s)!" + itemNames;
		
	}

}
