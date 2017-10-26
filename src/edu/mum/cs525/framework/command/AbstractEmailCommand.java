package edu.mum.cs525.framework.command;

public abstract class AbstractEmailCommand implements Command{
	
	protected String emailContent;
	
	public AbstractEmailCommand(String emailContent) {
		super();
		this.emailContent = emailContent;
	}

	@Override
	public abstract void execute();
}
