package edu.mum.cs525.project.creditcard.account;

import edu.mum.cs525.framework.CreditLimit;

public class SilverCreditLimit extends CreditLimit {

	public SilverCreditLimit() {
		super(Double.NEGATIVE_INFINITY, 70000);
	}
}
