package edu.mum.cs525.project.creditcard.account;

import edu.mum.cs525.framework.CreditLimit;
import edu.mum.cs525.framework.Interestable;

public class GoldAccountFactory extends CreditAccountFactory {

	@Override
	public Interestable createInterestCalculator() {
		return new GoldInterest();
	}

	@Override
	public CreditLimit createCreditLimit() {
		return new GoldCreditLimit();
	}
	
}
