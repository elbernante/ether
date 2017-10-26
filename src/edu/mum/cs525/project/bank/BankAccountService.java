package edu.mum.cs525.project.bank;

import edu.mum.cs525.framework.account.AccountService;
import edu.mum.cs525.framework.entity.Customer;
import edu.mum.cs525.project.bank.account.CheckingAccount;
import edu.mum.cs525.project.bank.account.SavingsAccount;

public interface BankAccountService extends AccountService {
	public SavingsAccount createSavingsAccount(String accountNumber, Customer customer);
	public CheckingAccount createCheckingAccount(String accountNumber, Customer customer);
}

