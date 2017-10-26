package edu.mum.cs525.project.creditcard.account;

import edu.mum.cs525.framework.transaction.CreditLimit;
import edu.mum.cs525.framework.transaction.Interestable;

public class GoldAccountFactory extends CreditAccountFactory {

	@Override
	public Interestable createInterestCalculator() {
		return new GoldInterest();
	}

	@Override
	public CreditLimit createCreditLimit() {
		return new GoldCreditLimit();
	}
	
	@Override
	public String getType() {
		return "GOLD";
	}
	
}
