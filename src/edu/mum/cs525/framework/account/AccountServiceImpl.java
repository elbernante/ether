package edu.mum.cs525.framework.account;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mum.cs525.framework.entity.Account;
import edu.mum.cs525.framework.entity.BusinessCustomer;
import edu.mum.cs525.framework.entity.Customer;
import edu.mum.cs525.framework.entity.PersonalCustomer;

public class AccountServiceImpl implements AccountService {
	
	private Map<String, Account> accounts = new HashMap<>();
	
	public AccountServiceImpl() {
		init();
	}
	
	public Account createAccount(AbstractAccountFactory factory, Class<?> clazz, String accountNumber, Customer customer) {
		Account account = null;
		try {
			account = (Account) clazz.getConstructor(AbstractAccountFactory.class).newInstance(factory);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e.getMessage());
		}

		account = AccountProxy.newProxy(account);
		account.setCustomer(customer);
		account.setAccountNumber(accountNumber);
		accounts.put(accountNumber, account);
		return account;
	}
	
	public void init() { /* hook */}
	
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

	@Override
	public List<Account> getAccounts(Customer customer) {
		List<Account> accounts = new ArrayList<>();
		for (Account account: getAllAccounts()) {
//			if (account.getCustomer() == customer) {
				accounts.add(account);
//			}
		}
		return accounts;
	}
}
