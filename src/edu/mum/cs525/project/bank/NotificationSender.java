package edu.mum.cs525.project.bank;

import edu.mum.cs525.framework.command.CommandsManager;
import edu.mum.cs525.framework.command.EmailManagerCommand;
import edu.mum.cs525.framework.entity.Account;
import edu.mum.cs525.framework.entity.BusinessCustomer;
import edu.mum.cs525.framework.entity.PersonalCustomer;
import edu.mum.cs525.framework.transaction.Transaction;

public class NotificationSender {
	
	public void onDeposit(Account acc, BusinessCustomer bc, Transaction tx) {
		sendEmail(bc.getEmail(), "$" + tx.getAmount() + " was credited to business " + acc.getAccountType()
		+ " account " + acc.getAccountNumber() + " on " + tx.getDate());
	}
	
	public void onWithdraw(Account acc, BusinessCustomer bc, Transaction tx) {
		sendEmail(bc.getEmail(), "$" + Math.abs(tx.getAmount()) + " was withrawn from business " + acc.getAccountType()
			+ " account " + acc.getAccountNumber() + " on " + tx.getDate());
	}
	
	public void onDeposit(Account acc, PersonalCustomer pc, Transaction tx) {
		if (tx.getAmount() > 500) {
			sendEmail(pc.getEmail(), "Large Transaction Detected! $" + tx.getAmount() + 
					" was credited to account " + acc.getAccountNumber() + " on " + tx.getDate());	
		}
	}
	
	public void onWithdraw(Account acc, PersonalCustomer pc, Transaction tx) {
		if (tx.getAmount() < -500) {
			sendEmail(pc.getEmail(), "Large Transaction Detected! $" + Math.abs(tx.getAmount()) + 
					" was withdrawan from account " + acc.getAccountNumber() + " on " + tx.getDate());	
		}
	}
	
	public void onOverdraft(Account acc, Transaction tx){
		sendEmail(acc.getCustomer().getEmail(), "Insufficient funds! A withdrawal of $" + Math.abs(tx.getAmount()) +
				" was attempted from account " + acc.getAccountNumber() + " on " + tx.getDate());
	}

	private void sendEmail(String emailAddress, String message) {
		String emailMessage = "Sending email to: " + emailAddress + "\n" + "Message: " + message;
		EmailManagerCommand emc = new EmailManagerCommand(emailMessage);
	    CommandsManager.getInstance().setCommand(emc);
	    CommandsManager.getInstance().invokeCommand();
	}
}
