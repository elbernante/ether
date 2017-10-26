package edu.mum.cs525.project.bank;

import java.util.ArrayList;
import java.util.List;

import edu.mum.cs525.framework.Account;
import edu.mum.cs525.framework.AccountStatement;
import edu.mum.cs525.framework.ApplicationContext;
import edu.mum.cs525.project.bank.account.CheckingAccount;
import edu.mum.cs525.project.bank.account.SavingsAccount;

public class AccountsReportVisitor implements BankAccountVisitor {
	
	public static String generateReports() {
		AccountsReportVisitor visitor = new AccountsReportVisitor();
		for (Account acc : ApplicationContext.getAccountService().getAllAccounts()) {
			acc.accept(visitor);
		}
		return visitor.printStatements();
	}
	
	private List<AccountStatement> statements = new ArrayList<>();

	@Override
	public void visit(SavingsAccount account) {
		statements.add(new AccountStatement(account));
	}

	@Override
	public void visit(CheckingAccount account) {
		statements.add(new AccountStatement(account));
	}
	
	public String printStatements() {
		StringBuilder sb = new StringBuilder();
		sb.append("********* ACCOUNTS REPORT *********\n\n");
		for (AccountStatement s : statements) {
			sb.append(s.print() + "\n");
		}
		return sb.toString();
	}

}
