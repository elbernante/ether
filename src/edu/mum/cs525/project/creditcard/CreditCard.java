package edu.mum.cs525.project.creditcard;

import edu.mum.cs525.framework.Account;
import edu.mum.cs525.framework.AccountActivityMonitor;
import edu.mum.cs525.framework.ApplicationContext;
import edu.mum.cs525.framework.Customer;
import edu.mum.cs525.project.creditcard.account.CreditAccount;

public class CreditCard {
	
	public static void main(String[] args) {
		// Configuration
		ApplicationContext.setAccountService(CreditAccountService.class);
		AccountActivityMonitor.register(CreditNotificationSender.class);
		// TODO: EmailManger factory
		
		
		
		
		
		// Usage
		CreditAccountService service = (CreditAccountService) ApplicationContext.getAccountService();
		
		Customer cust = service.createBusinessCutomer();
		Account acc = service.createGoldCreditAccount("222", cust);
		acc.deposit(450);
		try {
			acc.withdraw(2000);
		}
		catch(DeclineException de) {
			System.out.println("DeclineException: " + de.getMessage());
		}

		System.out.println(new CreditReportService().createReport((CreditAccount) acc));
		// TODO: Send appContext to UI
	}

}
