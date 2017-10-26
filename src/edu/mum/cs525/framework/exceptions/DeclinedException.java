package edu.mum.cs525.framework.exceptions;

public class DeclinedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DeclinedException(String msg) {
		super(msg);
	}
	
	@Override
	public String toString(){ 
		return ("DeclineException: " + this.getMessage());
	}
}
