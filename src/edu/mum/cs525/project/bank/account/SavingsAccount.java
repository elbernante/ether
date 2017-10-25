package edu.mum.cs525.project.bank.account;

import edu.mum.cs525.framework.Account;

public class SavingsAccount extends Account {

	@Override
	public double computeInterest(double balance) {
		// TODO Auto-generated method stub
		return balance * 0.025;
	}

}
