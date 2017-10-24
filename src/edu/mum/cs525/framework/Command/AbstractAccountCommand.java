package edu.mum.cs525.framework.Command;

import edu.mum.cs525.framework.AccountServiceImpl;



public abstract class AbstractAccountCommand implements Command {

	protected AccountServiceImpl serviceA;
	
	public AbstractAccountCommand(AccountServiceImpl serviceA) {
		super();
		this.serviceA = serviceA;
	}

	@Override
	public abstract void execute();

}
