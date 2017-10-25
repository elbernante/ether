package edu.mum.cs525.project.creditcard.account;

import edu.mum.cs525.framework.AbstractAccountFactory;
import edu.mum.cs525.framework.CreditAction;
import edu.mum.cs525.framework.DebitAction;
import edu.mum.cs525.framework.Transactionable;

public abstract class CreditAccountFactory implements AbstractAccountFactory  {

	@Override
	public Transactionable createDepositAction() {
		return new DebitAction("PAYMENT");
	}

	@Override
	public Transactionable createWithdrawAction() {
		return new CreditAction("PURCHASE");
	}

}
