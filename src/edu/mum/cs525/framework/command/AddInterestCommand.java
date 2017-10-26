package edu.mum.cs525.framework.command;

import edu.mum.cs525.framework.account.AccountService;

public class AddInterestCommand extends AbstractAccountCommand {
	public AddInterestCommand(AccountService serviceA) {
		super(serviceA);
	}

	@Override
	public void execute() {
		   //here call the service layer to save
	       serviceA.addInterest();  //update the UI if the save success 
	       							// maybe we need to modify the UI later
	       
	}

}
