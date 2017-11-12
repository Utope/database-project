package core;
import java.util.ArrayList;

public abstract class Action {
	public ActionHandler handler;

	public Action(ActionHandler handler) {
		this.handler = handler;
	}
	
	public abstract void execute();
	public abstract String textView();
}
