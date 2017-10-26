package edu.mum.cs525.framework.transaction;

public class CreditAction implements Transactionable {

	private String description;
	
	public CreditAction(String description) {
		this.description = description;
	}
	
	@Override
	public Transaction execute(double amount) {
		return new Transaction(amount, description);
	}

}
