package edu.mum.cs525.project.bank;

import edu.mum.cs525.framework.Account;
import edu.mum.cs525.framework.AccountActivityMonitor;
import edu.mum.cs525.framework.ApplicationContext;
import edu.mum.cs525.framework.Customer;
import edu.mum.cs525.project.bank.ui.BankUI;
import edu.mum.cs525.project.bank.account.CheckingAccountFactory;
import edu.mum.cs525.project.bank.account.SavingsAccountFactory;


public class Bank {
	
	public static void main(String[] args) {
		// Configuration
		ApplicationContext.registerAccountFactory(new SavingsAccountFactory(),
												  new CheckingAccountFactory());
		ApplicationContext.setAccountService(BankAccountService.class);
		AccountActivityMonitor.register(NotificationSender.class);
		// TODO: EmailManger factory
		
//				BankUI.main(args);

		
//		
		// Usage
		BankAccountService service = (BankAccountService) ApplicationContext.getAccountService();
//		
		Customer cust = service.createBusinessCutomer();
		cust.setEmail("business@email.com");
		Account acc = service.createCheckingAccount("111", cust);
		acc.deposit(450);
		acc.withdraw(100);
		
		Account acc2 = service.createSavingsAccount("123", cust);
		System.out.println(acc2.deposit(50).getAmount());
//		
//		// TODO: Send appContext to UI
	}

}
