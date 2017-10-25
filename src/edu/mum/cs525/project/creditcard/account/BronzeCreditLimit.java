package edu.mum.cs525.project.creditcard.account;

import edu.mum.cs525.framework.CreditLimit;

public class BronzeCreditLimit extends CreditLimit {
	
	public BronzeCreditLimit() {
		super(Double.NEGATIVE_INFINITY, 10000);
	}

}
