package edu.mum.cs525.project.bank.account;

import edu.mum.cs525.framework.transaction.Interestable;

public class SavingsInterest implements Interestable {

	@Override
	public double compute(double balance) {
		return 0.025 * (balance > 0 ? balance : 0);
	}

}
