package edu.mum.cs525.project.creditcard.account;

import edu.mum.cs525.framework.account.AbstractAccountFactory;
import edu.mum.cs525.framework.transaction.CreditAction;
import edu.mum.cs525.framework.transaction.DebitAction;
import edu.mum.cs525.framework.transaction.Transactionable;

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
