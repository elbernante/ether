package edu.mum.cs525.framework;

public class Transaction {
	
	private double amount;
	private String description;
	
	// TODO: Date, fromAccount, toAccount, etc
	
	public Transaction(double amount, String description) {
		this.amount = amount;
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}
	
	public String getDescription() {
		return description;
	}
}
