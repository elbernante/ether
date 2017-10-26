package edu.mum.cs525.framework;

public class AccountStatement {
	
	private Account account;
	public AccountStatement(Account account) {
		this.account = account;
	}
	
	public String print() {
		StringBuilder sb = new StringBuilder();
		sb.append("========================================\n");
		sb.append("========= STATEMENT OF ACCOUNT =========\n");
		sb.append("========================================\n");
		sb.append(" Account #   : " + account.getAccountNumber() + "\n");
		sb.append(" Name        : " + account.getCustomer().getName() + "\n");
		sb.append(" Account type: " + account.getAccountType() + "\n");
		sb.append("----------------------------------------\n");
		sb.append("TRANSACTION HISTORY:\n");
		if (account.getTransactions().size() == 0) {
			sb.append("\n        --- No Transactions --   \n\n");
		} else {
			String format = " %-25s  %10s %s\n";
			sb.append(String.format(format, "DATE", "AMOUNT", "DESCRIPTION"));
			for (Transaction tx : account.getTransactions()) {
				sb.append(String.format(format, tx.getDate(), tx.getAmount(), tx.getDescription()));
			}
		}
		sb.append("========================================\n");
		
		return sb.toString();
	}
}
