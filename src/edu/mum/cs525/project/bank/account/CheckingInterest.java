package edu.mum.cs525.project.bank.account;

import edu.mum.cs525.framework.Interestable;

public class CheckingInterest implements Interestable {

	@Override
	public double compute(double balance) {
		return 0.01 * (balance > 0 ? balance : 0);
	}

}
