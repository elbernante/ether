package edu.mum.cs525.project.creditcard.account;

import edu.mum.cs525.framework.AbstractAccountFactory;
import edu.mum.cs525.framework.Account;
import edu.mum.cs525.project.creditcard.CreditAccountVisitor;

public class GoldAccount extends Account {

	public GoldAccount(AbstractAccountFactory factory) {
		super(factory);
	}
	
	@Override
	public void accept(Object visitor) {
		((CreditAccountVisitor) visitor).visit(this);
	}
}
