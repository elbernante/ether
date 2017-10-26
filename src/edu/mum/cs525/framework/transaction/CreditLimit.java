package edu.mum.cs525.framework.transaction;

public abstract class CreditLimit {
	private double max = Double.POSITIVE_INFINITY;
	private double min = Double.NEGATIVE_INFINITY;
	
	public CreditLimit() { }
	
	public CreditLimit(double min, double max) {
		this.min = min;
		this.max = max;
	}
	
	public boolean check(double value) {
		return value >= min && value <= max;
	}
}
