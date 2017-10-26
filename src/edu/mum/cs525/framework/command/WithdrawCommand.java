package edu.mum.cs525.framework.command;

import edu.mum.cs525.framework.account.AccountService;

public class WithdrawCommand extends AbstractAccountCommand {

	private String accountNumber;
	private double ammount;
	
	public WithdrawCommand(AccountService serviceA, String accountNumber, double ammount) {
		super(serviceA);
		this.accountNumber = accountNumber;
		this.ammount = ammount;
	}

	@Override
	public void execute() {
		serviceA.withdraw(accountNumber, ammount);
	}

}
