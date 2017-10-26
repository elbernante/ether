package edu.mum.cs525.framework.command;

public interface InvokerCommand {

	public void setCommand(Command command);
	
	public void invokeCommand();
}

