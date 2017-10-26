package edu.mum.cs525.framework.account;

import edu.mum.cs525.framework.transaction.CreditLimit;
import edu.mum.cs525.framework.transaction.Interestable;
import edu.mum.cs525.framework.transaction.Transactionable;

public interface AbstractAccountFactory {
	public Transactionable createDepositAction();
	public Transactionable createWithdrawAction();
	public Interestable createInterestCalculator();
	public CreditLimit createCreditLimit();
	public String getType();
}
