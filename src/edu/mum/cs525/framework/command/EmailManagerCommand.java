package edu.mum.cs525.framework.Command;

import edu.mum.cs525.framework.util.EmailManager;

public class EmailManagerCommand extends AbstractEmailCommand {
	
	public EmailManagerCommand(String emailContent) {
		super(emailContent);
	}

	@Override
	public void execute() {	
		EmailManager.getSingleton().printEmail(emailContent);
	}
}
