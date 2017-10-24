package edu.mum.cs525.framework;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
	public static final String WITHDRAW = "withdraw";
	public static final String DEPOSIT = "deposit";
	public static final String ADD_INTEREST = "addInterest";
	
	private double balance;
	private Customer customer;
	private String accountNumber;
	
	protected List<Transaction> transactions = new ArrayList<>();
	
	public Account() {
		init();
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public void trigger(String eventName, Object...args) {
		AccountActivityMonitor.broadcast(eventName, this, args);
	}
	
	public void updateBalance(double amount) {
		balance += amount;
	}
	
	public void init() { /* hook */ }
	
	protected Transaction addTransaction(double amount, String description) {
		Transaction tx = new Transaction(amount, description);
		transactions.add(tx);
		updateBalance(tx.getAmount());
		return tx;
	}
	
	public Transaction deposit(double amount, String description) {
		 return addTransaction(amount, description);
	}
	
	public Transaction deposit(double amount) {
		return deposit(amount, "Deposit");
	}
	
	public Transaction withdraw(double amount, String description) {
		return addTransaction(-amount, description);
	};
	
	public Transaction withdraw(double amount) {
		return withdraw(amount, "Withdraw");
	}
	
	public Transaction addInterest(String description) {
		double interest = computeInterest(getBalance());
		return addTransaction(interest, description);
	}
	
	public Transaction addInterest() {
		return addInterest("Add Interest");
	}
	
	public abstract double computeInterest(double balance);
}
