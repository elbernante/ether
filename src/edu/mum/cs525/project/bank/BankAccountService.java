package edu.mum.cs525.project.bank;

import edu.mum.cs525.framework.Account;
import edu.mum.cs525.framework.AccountService;
import edu.mum.cs525.framework.Customer;

public interface BankAccountService extends AccountService {
	public Account createSavingsAccount(String accountNumber, Customer customer);
	public Account createCheckingAccount(String accountNumber, Customer customer);
}

