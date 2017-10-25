package edu.mum.cs525.project.creditcard;

import edu.mum.cs525.framework.Account;

public class MinimumPaymentCalculator {
	
	public static double compute(Account account, double balance) {
		switch (account.getAccountType()) {
		case "GOLD"		: return computeGoldPayment(balance);
		case "SILVER"	: return computeSilverPayment(balance);
		case "BRONZE"	: return computeBronzePayment(balance);
		default			: throw new RuntimeException("Unsuported account type");
		}
	}
	
	private static double computeGoldPayment(double balance) {
		return 0.1 * balance;
	}
	
	private static double computeSilverPayment(double balance) {
		return 0.12 * balance;
	}
	
	private static double computeBronzePayment(double balance) {
		return 0.14 * balance;
	}

}
