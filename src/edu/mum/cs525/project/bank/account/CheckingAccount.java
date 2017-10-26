package edu.mum.cs525.project.bank.account;

import edu.mum.cs525.framework.account.AbstractAccountFactory;
import edu.mum.cs525.framework.entity.Account;
import edu.mum.cs525.project.bank.reports.BankAccountVisitor;

public class CheckingAccount extends Account {

	public CheckingAccount(AbstractAccountFactory factory) {
		super(factory);
	}

	@Override
	public void accept(Object visitor) {
		((BankAccountVisitor)visitor).visit(this);
	}
}
