package edu.mum.cs525.framework.transaction;

public interface Transactionable {
	public Transaction execute(double amount);
}
