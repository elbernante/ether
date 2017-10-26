package edu.mum.cs525.project.bank;

import edu.mum.cs525.framework.ApplicationContext;
import edu.mum.cs525.framework.account.AccountActivityMonitor;
import edu.mum.cs525.project.bank.account.CheckingAccountFactory;
import edu.mum.cs525.project.bank.account.SavingsAccountFactory;
import edu.mum.cs525.project.bank.ui.BankUI;


public class Bank {
	
	public static void main(String[] args) {
		// Configuration
		ApplicationContext.registerAccountFactory(new SavingsAccountFactory(),
												  new CheckingAccountFactory());
		ApplicationContext.setAccountService(BankAccountService.class);
		AccountActivityMonitor.register(NotificationSender.class);
		
		// Launch GUI
		BankUI.load(args);
	}

}
