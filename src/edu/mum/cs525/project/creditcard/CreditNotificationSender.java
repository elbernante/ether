package edu.mum.cs525.project.creditcard;

import edu.mum.cs525.framework.Account;
import edu.mum.cs525.framework.BusinessCustomer;
import edu.mum.cs525.framework.Customer;
import edu.mum.cs525.framework.PersonalCustomer;
import edu.mum.cs525.framework.Transaction;
import edu.mum.cs525.project.creditcard.account.CreditAccount;

public class CreditNotificationSender {
	
	// TODO: Delegate message sending to Email
	// TODO: Implement logic for criteria for sending email
	
	public void onDeposit(CreditAccount acc) {
		System.out.println("Dopsiting");
	}
	
	public void onWithdraw(Transaction tx, BusinessCustomer bc, Account acc) {
		System.out.println("Withdrawing Business");
		
		System.out.println("   BALANCE: " + acc.getBalance());
	}
	
	public void onWithdraw(PersonalCustomer pc) {
		System.out.println("Withdrawing Personal");
	}
	
	public void onWithdraw(Account acc, Customer pc, Transaction tx) {
		System.out.println("Withdrawing generic");
		System.out.println("   Withrawn:" + tx.getAmount());
		System.out.println("   New balance:" + acc.getBalance());
	}
}
