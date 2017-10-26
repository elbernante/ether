package edu.mum.cs525.project.bank.reports;

import edu.mum.cs525.project.bank.account.CheckingAccount;
import edu.mum.cs525.project.bank.account.SavingsAccount;

public interface BankAccountVisitor {
	public void visit(SavingsAccount account);
	public void visit(CheckingAccount account);
}
