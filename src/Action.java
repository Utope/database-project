import java.util.ArrayList;

public abstract class Action {
	public ArrayList<Action> createdActions;
	
	public Action() {
		createdActions = new ArrayList<Action>();
	}
	
	public abstract void execute();
	public abstract String textView();
}
