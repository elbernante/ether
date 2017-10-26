package edu.mum.cs525.project.creditcard.account;

import edu.mum.cs525.framework.AbstractAccountFactory;
import edu.mum.cs525.framework.Account;
import edu.mum.cs525.project.creditcard.CreditAccountVisitor;

public class SilverAccount extends Account {

	public SilverAccount(AbstractAccountFactory factory) {
		super(factory);
	}

	public void accept(CreditAccountVisitor visitor) {
		visitor.visit(this);
	}
}
