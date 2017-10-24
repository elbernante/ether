package edu.mum.cs525.framework;

public interface AccountService {
	
	public default void init() { /* hook */ };
	
	public Account createAccount(Class<? extends Account> clazz, String accountNumber, Customer customer);
	
	public Account getAccount(String accountNumber);
	
	public Iterable<Account> getAllAccounts();
	
	public PersonalCustomer createPersonalCustomer();
	
	public BusinessCustomer createBusinessCutomer();
	
	public void deposit(String accountNumber, double amount);
	
	public void withdraw(String accountNumber, double amount);
	
	public void addInterest();

}
