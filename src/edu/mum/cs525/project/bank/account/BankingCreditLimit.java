package edu.mum.cs525.project.bank.account;

import edu.mum.cs525.framework.transaction.CreditLimit;

public class BankingCreditLimit extends CreditLimit {
	public BankingCreditLimit() {
		super(0.0, Double.POSITIVE_INFINITY);
	}
}
