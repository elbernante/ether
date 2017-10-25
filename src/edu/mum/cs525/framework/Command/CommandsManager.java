package edu.mum.cs525.framework.Command;


public class CommandsManager implements InvokerCommand {

	Command command;
	private static CommandsManager instance;
	
	private CommandsManager(){
	}
	
	// it is singleten
	public static CommandsManager getInstance(){
		if(instance == null)
			instance = new CommandsManager();
		return instance;
	}

	public void setCommand(Command command) {
		this.command = command;
	}
	
	public void invokeCommand(){
		command.execute();
	}
	
}
