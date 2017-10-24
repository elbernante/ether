package edu.mum.cs525.framework;

import java.time.LocalDateTime;

public class Transaction {
	
	private double amount;
	private String description;
	private LocalDateTime date;
	
	public Transaction() { }
	
	public Transaction(double amount, String description) {
		this.amount = amount;
		this.description = description;
		this.date = LocalDateTime.now();
	}

	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"amount=" + amount +
				", description='" + description + '\'' +
				", date=" + date +
				'}';
	}
}
