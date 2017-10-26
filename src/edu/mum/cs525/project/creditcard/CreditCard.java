package edu.mum.cs525.project.creditcard;

import edu.mum.cs525.framework.Account;
import edu.mum.cs525.framework.AccountActivityMonitor;
import edu.mum.cs525.framework.ApplicationContext;
import edu.mum.cs525.framework.Customer;
import edu.mum.cs525.framework.DeclinedException;
import edu.mum.cs525.project.creditcard.account.BronzeAccountFactory;
import edu.mum.cs525.project.creditcard.account.GoldAccountFactory;
import edu.mum.cs525.project.creditcard.account.SilverAccountFactory;

public class CreditCard {
	
	public static void main(String[] args) {
		ApplicationContext.registerAccountFactory(new GoldAccountFactory(),
												  new SilverAccountFactory(),
												  new BronzeAccountFactory());
		ApplicationContext.setAccountService(CreditAccountService.class);
		AccountActivityMonitor.register(CreditNotificationSender.class);
		// TODO: EmailManger factory
		
		
		
		
		
		// Usage
		CreditAccountService service = (CreditAccountService) ApplicationContext.getAccountService();
		
		Customer cust = service.createBusinessCutomer();
		Account acc = service.createGoldAccount("222", cust);
		acc.deposit(450);
		try {
			acc.withdraw(20000034);
		}
		catch(DeclinedException de) {
			System.out.println("DeclineException: " + de.getMessage());
		}

		System.out.println(new CreditReportService().createReport(acc));
		// TODO: Send appContext to UI
	}

}
