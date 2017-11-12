package core;
import java.util.ArrayList;
import java.util.Random;

public class Action_Death extends Action {

	Entity killed;
	Entity killedby;
	
	public Action_Death(Entity killed, Entity killedBy, ActionHandler handler) {
		super(handler);
		this.killed = killed;
		this.killedby = killedBy;
	}
	
	@Override
	public void execute() {
		Random rand = new Random(System.currentTimeMillis());
		
		if(rand.nextInt() % 10 > 7) {
			if(killed instanceof Monster) {
				Monster monster = (Monster)killed;
				ArrayList<Loot> loots = monster.getLoots();
				
				//May not be gettin by reference here
				for(Loot loot : loots) {
					if(loot.getDropChance() > rand.nextFloat()) {
						this.handler.addAction(new Action_DropItem(monster, loot, handler));
					}
				}
			}else if(killed instanceof Hero) {
				// add new hero to player
			}

		}
	}

	@Override
	public String textView() {
		return killed.getName() + " was slain by " + killedby.getName();
	}

}
