package edu.mum.cs525.project.bank.account;

import edu.mum.cs525.framework.account.AbstractAccountFactory;
import edu.mum.cs525.framework.transaction.CreditAction;
import edu.mum.cs525.framework.transaction.CreditLimit;
import edu.mum.cs525.framework.transaction.DebitAction;
import edu.mum.cs525.framework.transaction.Transactionable;

public abstract class BankingAccountFactory implements AbstractAccountFactory {
	@Override
	public Transactionable createDepositAction() {
		return new CreditAction("DEPOSIT");
	}

	@Override
	public Transactionable createWithdrawAction() {
		return new DebitAction("WITHDRAW");
	}
	
	@Override
	public CreditLimit createCreditLimit() {
		return new BankingCreditLimit();
	}
}
