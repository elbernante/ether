package edu.mum.cs525.project.creditcard.account;

public class SilverAccount extends CreditAccount {

	public SilverAccount() {
		super();
	}

	@Override
	public double computeInterest(double balance) {
		return balance * 0.08;
	}

}
