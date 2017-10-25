package edu.mum.cs525.project.creditcard.account;

import edu.mum.cs525.framework.CreditLimit;
import edu.mum.cs525.framework.Interestable;

public class SilverAccountFactory extends CreditAccountFactory {

	@Override
	public Interestable createInterestCalculator() {
		return new SilverInterest();
	}

	@Override
	public CreditLimit createCreditLimit() {
		return new SilverCreditLimit();
	}
	
	@Override
	public String getType() {
		return "SILVER";
	}

}
