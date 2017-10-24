package edu.mum.cs525.project.creditcard;

public class DeclineException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DeclineException(String msg) {
		super(msg);
	}
	
	@Override
	public String toString(){ 
		return ("DeclineException: " + this.getMessage());
	}
}
