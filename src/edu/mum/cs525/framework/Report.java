package edu.mum.cs525.framework;

import java.util.List;

public class Report {
    private Customer customer;
    private List<Account> accounts;

    public Report(Customer customer, List<Account> accounts) {
        this.customer = customer;
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Account Report for customer ").append(customer);
        for (Account account: accounts) {
            sb.append("\nTransactions of account ").append(account.getAccountNumber());
            for (Transaction transaction: account.transactions) {
                sb.append("\n").append(transaction.toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
