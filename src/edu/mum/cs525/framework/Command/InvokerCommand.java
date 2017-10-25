package edu.mum.cs525.framework.Command;

public interface InvokerCommand {

	public void setCommand(Command command);
	
	public void invokeCommand();
}

