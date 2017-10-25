package edu.mum.cs525.project.creditcard.account;

import edu.mum.cs525.framework.Interestable;

public class GoldInterest implements Interestable {

	@Override
	public double compute(double balance) {
		return 0.06 * (balance > 0 ? balance : 0);
	}

}
