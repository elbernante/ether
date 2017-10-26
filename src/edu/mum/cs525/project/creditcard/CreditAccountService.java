package edu.mum.cs525.project.creditcard;

import edu.mum.cs525.framework.account.AccountService;
import edu.mum.cs525.framework.entity.Customer;
import edu.mum.cs525.project.creditcard.account.BronzeAccount;
import edu.mum.cs525.project.creditcard.account.GoldAccount;
import edu.mum.cs525.project.creditcard.account.SilverAccount;

public interface CreditAccountService extends AccountService {
	public GoldAccount createGoldAccount(String accountNumber, Customer customer);
	public SilverAccount createSilverAccount(String accountNumber, Customer customer);
	public BronzeAccount createBronzeAccount(String accountNumber, Customer customer);
}
