package edu.mum.cs525.project.creditcard.account;

import edu.mum.cs525.framework.Account;
import edu.mum.cs525.framework.Transaction;
import edu.mum.cs525.project.creditcard.DeclineException;

public abstract class CreditAccount extends Account implements CreditInterest {
	private CreditInterest creditInterest;
	private double creditLimit;
	
	public CreditAccount() {
		creditLimit = 1000;
	}

	public void setCreditInterest(CreditInterest creditInterest) {
		this.creditInterest = creditInterest;
	}
	
	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Override
	public Transaction deposit(double amount, String description) {
		 return addTransaction(-1 * amount, description);
	}
	
	@Override
	public Transaction deposit(double amount) {
		return deposit(-1 * amount, "Deposit");
	}
	
	@Override
	public Transaction withdraw(double amount, String description) {
		if(this.getBalance() + amount > this.getCreditLimit()) {
			throw new DeclineException("Exceed the credit limit!");
		}
		return addTransaction(amount, description);
	}
	
	@Override
	public Transaction withdraw(double amount) {
		return withdraw(amount, "Withdraw");
	}
	
	@Override
	public double computeInterest(double balance) {
		return creditInterest.computeInterest(balance);
	}

    public abstract double computeMinimumPayment(double balance);

	public CreditInterest getCreditInterest() {
		return creditInterest;
	}
}
