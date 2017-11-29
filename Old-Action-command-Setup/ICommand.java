package core.commands;

public abstract class ICommand {
	
	ICommand_Creator source;
	
	public ICommand(ICommand_Creator source) {
		this.source = source;
	}
	
	public abstract void execute();
	
	public ICommand_Creator getParent() {
		return this.source;
	}
}
