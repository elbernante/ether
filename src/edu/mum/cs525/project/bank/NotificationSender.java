package edu.mum.cs525.project.bank;

import edu.mum.cs525.framework.Account;
import edu.mum.cs525.framework.BusinessCustomer;
import edu.mum.cs525.framework.Transaction;
import edu.mum.cs525.framework.Command.CommandsManager;
import edu.mum.cs525.framework.Command.EmailManagerCommand;

public class NotificationSender {
	
	public void onDeposit(Account acc, BusinessCustomer bc, Transaction tx) {
		sendEmail(bc.getEmail(), "$" + tx.getAmount() + " was credited to business " + acc.getAccountType()
		+ " account " + acc.getAccountNumber() + " on " + tx.getDate());
	}
	
	public void onWithdraw(Account acc, BusinessCustomer bc, Transaction tx) {
		sendEmail(bc.getEmail(), "$" + Math.abs(tx.getAmount()) + " was withrawn from business " + acc.getAccountType()
			+ " account " + acc.getAccountNumber() + " on " + tx.getDate());
	}
	
	private void sendEmail(String emailAddress, String message) {
		String emailMessage = "Sending email to: " + emailAddress + "\n" + "Message: " + message;
		EmailManagerCommand emc = new EmailManagerCommand(emailMessage);
	    CommandsManager.getInstance().setCommand(emc);
	    CommandsManager.getInstance().invokeCommand();
	}
}
