package edu.mum.cs525.project.bank.account;

import edu.mum.cs525.framework.AbstractAccountFactory;
import edu.mum.cs525.framework.CreditAction;
import edu.mum.cs525.framework.CreditLimit;
import edu.mum.cs525.framework.DebitAction;
import edu.mum.cs525.framework.Transactionable;

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
