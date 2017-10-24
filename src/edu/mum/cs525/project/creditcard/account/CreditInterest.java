package edu.mum.cs525.project.creditcard.account;

public interface CreditInterest {
	public default double computeInterest(double balance) {
		return 0;
	}
}
