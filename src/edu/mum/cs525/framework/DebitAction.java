package edu.mum.cs525.framework;

public class DebitAction implements Transactionable {
	
	private String description;
	
	public DebitAction(String description) {
		this.description = description;
	}
	
	@Override
	public Transaction execute(double amount) {
		return new Transaction(-amount, description);
	}

}
