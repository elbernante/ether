package edu.mum.cs525.project.creditcard.account;

import edu.mum.cs525.framework.account.AbstractAccountFactory;
import edu.mum.cs525.project.creditcard.reports.CreditAccountVisitor;

public class BronzeAccount extends CreditAccount {

	public BronzeAccount(AbstractAccountFactory factory) {
		super(factory);
	}
	
	@Override
	public void accept(Object visitor) {
		((CreditAccountVisitor) visitor).visit(this);
	}
}
