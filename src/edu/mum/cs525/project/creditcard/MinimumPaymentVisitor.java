package edu.mum.cs525.project.creditcard;

import edu.mum.cs525.project.creditcard.account.BronzeAccount;
import edu.mum.cs525.project.creditcard.account.GoldAccount;
import edu.mum.cs525.project.creditcard.account.SilverAccount;

public class MinimumPaymentVisitor implements CreditAccountVisitor {

	private double balance;
	private double mininumPayment = 0.0;
	
	public MinimumPaymentVisitor(double balance) {
		this.balance = balance;
	}
	
	@Override
	public void visit(GoldAccount account) {
		mininumPayment = 0.1 * balance;
	}

	@Override
	public void visit(SilverAccount account) {
		mininumPayment = 0.12 * balance;
	}

	@Override
	public void visit(BronzeAccount account) {
		mininumPayment = 0.14 * balance;
	}
	
	public double getMininumPayment() {
		return mininumPayment;
	}

}
