package edu.mum.cs525.project.creditcard.account;

import edu.mum.cs525.framework.transaction.CreditLimit;

public class GoldCreditLimit extends CreditLimit {
	
	public GoldCreditLimit() {
		super(Double.NEGATIVE_INFINITY, 300000);
	}

}
