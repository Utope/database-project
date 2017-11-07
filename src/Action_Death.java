import java.util.Random;

public class Action_Death extends Action {

	Entity killed;
	Entity killedby;
	
	public Action_Death(Entity killed, Entity killedBy) {
		this.killed = killed;
		this.killedby = killedBy;
	}
	
	@Override
	public void execute() {
		Random rand = new Random(System.currentTimeMillis());
		
		if(rand.nextInt() % 10 > 7) {
			if(killed instanceof Monster) {
				this.createdActions.add(new Action_DropItem((Monster)killed));
			}

		}
	}

	@Override
	public String textView() {
		return killed.getName() + " was slain by " + killedby.getName();
	}

}
