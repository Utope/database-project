package core.commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CommandHandler {
	
	private ArrayList<ICommand> executedCommands;
	private Queue<ICommand> queuedCommands;
	
	public CommandHandler() {
		executedCommands = new ArrayList<ICommand>();
		queuedCommands = new LinkedList<ICommand>();
	}
	
	public void add(ICommand command) {
		queuedCommands.add(command);
	}
	
	public void execute() {
		ICommand command = null;
		while((command = queuedCommands.poll()) != null) {
			command.execute();
			executedCommands.add(command);
			
			if(command instanceof CommandOutput) {
				System.out.println(((CommandOutput)command).output());
			}
		}
	}
}
