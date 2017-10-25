package edu.mum.cs525.project.creditcard.account;

public class BronzeAccount extends CreditAccount {

	public BronzeAccount() {
		super();
	}

	@Override
	public double computeInterest(double balance) {
		return balance * 0.1;
	}

	@Override
	public double computeMinimumPayment(double balance) {
		return balance * 0.14;
	}

}
