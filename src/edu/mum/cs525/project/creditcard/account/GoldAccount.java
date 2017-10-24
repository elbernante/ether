package edu.mum.cs525.project.creditcard.account;

public class GoldAccount extends CreditAccount {

	public GoldAccount() { 
		super(); 
	}
	
	@Override
	public double computeInterest(double balance) {
		return balance * 0.06;
	}

}
