package edu.mum.cs525.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AccountServiceImpl implements AccountService {
	
	private Map<String, Account> accounts = new HashMap<>();
	
	public AccountServiceImpl() {
		init();
	}
	
	public void init() { /* hook */}
	
	public Account createAccount(Class<? extends Account> clazz, String accountNumber, Customer customer) {
		Account account = Util.instantiate(clazz);
		account = AccountProxy.newProxy(account);
		account.setCustomer(customer);
		account.setAccountNumber(accountNumber);
		accounts.put(accountNumber, account);
		return account;
	}
	
	public Account getAccount(String accountNumber) {
		return accounts.get(accountNumber);
	}
	
	public Iterable<Account> getAllAccounts() {
		return new ArrayList<>(accounts.values());
	}
	
	public PersonalCustomer createPersonalCustomer() {
		return new PersonalCustomer();
	}
	
	public BusinessCustomer createBusinessCutomer() {
		return new BusinessCustomer();
	}
	
	public void deposit(String accountNumber, double amount) {
		Account account = getAccount(accountNumber);
		if (null != account) {
			account.deposit(amount);
		}
	}
	
	public void withdraw(String accountNumber, double amount) {
		Account account = getAccount(accountNumber);
		if (null != account) {
			account.withdraw(amount);
		}
	}
	
	public void addInterest() {
		for (Account account : getAllAccounts()) {
			account.addInterest();
		}
	}
}
