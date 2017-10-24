package edu.mum.cs525.project.creditcard;

import edu.mum.cs525.framework.AccountService;
import edu.mum.cs525.framework.Customer;
import edu.mum.cs525.project.creditcard.account.BronzeAccount;
import edu.mum.cs525.project.creditcard.account.GoldAccount;
import edu.mum.cs525.project.creditcard.account.SilverAccount;

public interface CreditAccountService extends AccountService {
	public GoldAccount createGoldCreditAccount(String accountNumber, Customer customer);
	public SilverAccount createSilverCreditAccount(String accountNumber, Customer customer);
	public BronzeAccount createBronzeCreditAccount(String accountNumber, Customer customer);
}
