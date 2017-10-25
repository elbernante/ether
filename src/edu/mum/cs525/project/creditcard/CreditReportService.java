package edu.mum.cs525.project.creditcard;

import edu.mum.cs525.framework.Transaction;
import edu.mum.cs525.project.creditcard.account.CreditAccount;

public class CreditReportService {
    public CreditAccountReport createReport(CreditAccount account) {
        double previousBalance = account.getLastMonthBalance();
        double totalPayments = 0, totalCredits = 0;

        for (Transaction transaction: account.getLastMonthTransactions()) {
            if (transaction.getAmount() > 0) {
                totalCredits += transaction.getAmount();
            } else {
                totalPayments += transaction.getAmount();
            }
        }

        double newBalance = previousBalance + totalCredits + totalPayments + account.computeInterest(previousBalance + totalCredits);
        double totalDue = account.computeMinimumPayment(newBalance);
        return new CreditAccountReport(previousBalance, totalPayments, totalCredits, newBalance, totalDue);
    }
}
