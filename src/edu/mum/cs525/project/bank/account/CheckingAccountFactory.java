package edu.mum.cs525.project.bank.account;

import edu.mum.cs525.framework.Interestable;

public class CheckingAccountFactory extends BankingAccountFactory {

	@Override
	public Interestable createInterestCalculator() {
		return new CheckingInterest();
	}
	
}
