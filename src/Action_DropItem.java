
public class Action_DropItem extends Action {

	Entity entity;
	
	public Action_DropItem(Entity entity) {
		this.entity = entity;
	}
	
	@Override
	public void execute() {
		
	}

	@Override
	public String textView() {
		return entity.getName() + " has dropped an item!";
		
	}

}
