package core.commands;
import java.util.ArrayList;
import java.util.Random;
import core.Entity;
import core.Game;
import core.Hero;
import core.Item;
import core.Loot;
import core.Monster;

public class Action_Death extends ICommand implements EntityAction, CommandOutput, Creates_EntityDropItem {

	Entity killed;
	Entity killedby;
	
	public Action_Death(Entity killed, Entity killedBy, ICommand_Creator source) {
		super(source);
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
						entityDropItem(killed, loot.getItem());
					}
				}
			}else if(killed instanceof Hero) {
				// add new hero to player
			}

		}
	}

	@Override
	public String output() {
		return killed.getName() + " was slain by " + killedby.getName();
	}

	@Override
	public void entityDropItem(Entity entity, Item item) {
		Game.getCommandHandler().add(new Action_DropItem(entity,item,this));
		
	}

}
