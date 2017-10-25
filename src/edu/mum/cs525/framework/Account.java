package edu.mum.cs525.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account {
	public static final String WITHDRAW = "withdraw";
	public static final String DEPOSIT = "deposit";
	public static final String ADD_INTEREST = "addInterest";
	
	private double balance;
	private Customer customer;
	private String accountNumber;
	
	private Transactionable depositAction;
	private Transactionable withdrawAction;
	private Interestable interestCalculator;
	private CreditLimit creditLimit;
	
	protected List<Transaction> transactions = new ArrayList<>();
	
	public Account() { }
	
	public Account(AbstractAccountFactory factory) {
		Objects.requireNonNull(factory);
		this.depositAction = factory.createDepositAction();
		this.withdrawAction = factory.createWithdrawAction();
		this.interestCalculator = factory.createInterestCalculator();
		this.creditLimit = factory.createCreditLimit();
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
	
	public Transaction updateBalance(Transaction transaction, boolean checkLimit) {
		double newBalance = balance + transaction.getAmount();
		
		if (checkLimit && !creditLimit.check(newBalance)) {
			throw new DeclinedException("Transaction declined: " + transaction.getDescription() +
					" Amount: " + transaction.getAmount());
		}
		
		balance = newBalance;
		transactions.add(transaction);
		return (Transaction) UndomifiableProxy.createProxy(transaction);
	}
	
	public void init() { /* hook */ }
	
	public Transaction deposit(double amount) {
		Transaction tx = depositAction.execute(amount);
		 return updateBalance(tx, true);
	}
	
	public Transaction withdraw(double amount) {
		Transaction tx = withdrawAction.execute(amount);
		return updateBalance(tx, true);
	};
	
	public double computeInterest() {
		return interestCalculator.compute(getBalance());
	}
	
	public Transaction addInterest(String description) {
		double interest = computeInterest();
		if (interest < 0) {
			return null;
		}
		Transaction tx = new Transaction(interest, description);
		return updateBalance(tx, false);
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public List<Transaction> getLastMonthTransactions() {
		List<Transaction> lastMonthTransactions = new ArrayList<>();
		int n = transactions.size();
		for (int i = n - 1; i >= n / 2; i--) {
			lastMonthTransactions.add(transactions.get(i));
		}
		return lastMonthTransactions;
	}

	public double getLastMonthBalance() {
		double lastBalance = balance;
		for (Transaction transaction: getLastMonthTransactions()) {
			lastBalance -= transaction.getAmount();
		}
		return lastBalance;
	}

	public Transaction addInterest() {
		return addInterest("Add Interest");
	}
}
