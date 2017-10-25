package edu.mum.cs525.framework;

public interface AbstractAccountFactory {
	public Transactionable createDepositAction();
	public Transactionable createWithdrawAction();
	public Interestable createInterestCalculator();
	public CreditLimit createCreditLimit();
}
