package edu.mum.cs525.project.creditcard.account;

import java.time.LocalDate;

import edu.mum.cs525.framework.account.AbstractAccountFactory;
import edu.mum.cs525.framework.entity.Account;

public abstract class CreditAccount extends Account {

	private LocalDate expiryDate = LocalDate.now();
	
	public CreditAccount(AbstractAccountFactory factory) {
		super(factory);
	}
	
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	
}
