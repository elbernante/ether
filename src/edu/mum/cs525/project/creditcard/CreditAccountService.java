package edu.mum.cs525.project.creditcard;

import edu.mum.cs525.framework.Account;
import edu.mum.cs525.framework.AccountService;
import edu.mum.cs525.framework.Customer;

public interface CreditAccountService extends AccountService {
	public Account createGoldAccount(String accountNumber, Customer customer);
	public Account createSilverAccount(String accountNumber, Customer customer);
	public Account createBronzeAccount(String accountNumber, Customer customer);
}
