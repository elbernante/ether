package edu.mum.cs525.framework.Command;

import edu.mum.cs525.framework.AccountService;



public abstract class AbstractAccountCommand implements Command {

	protected AccountService serviceA;
	
	public AbstractAccountCommand(AccountService serviceA) {
		super();
		this.serviceA = serviceA;
	}

	@Override
	public abstract void execute();

}
