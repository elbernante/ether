package edu.mum.cs525.framework.account;

import java.util.List;

import edu.mum.cs525.framework.entity.Account;
import edu.mum.cs525.framework.entity.BusinessCustomer;
import edu.mum.cs525.framework.entity.Customer;
import edu.mum.cs525.framework.entity.PersonalCustomer;

public interface AccountService {
	
	public default void init() { /* hook */ };
	
	public Account createAccount(AbstractAccountFactory factory, Class<?> clazz, String accountNumber, Customer customer);
	
	public Account getAccount(String accountNumber);
	
	public Iterable<Account> getAllAccounts();
	
	public PersonalCustomer createPersonalCustomer();
	
	public BusinessCustomer createBusinessCutomer();
	
	public void deposit(String accountNumber, double amount);
	
	public void withdraw(String accountNumber, double amount);
	
	public void addInterest();

	public List<Account> getAccounts(Customer customer);

}
