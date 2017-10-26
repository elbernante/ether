package edu.mum.cs525.project.creditcard.reports;

import edu.mum.cs525.framework.entity.Account;
import edu.mum.cs525.framework.transaction.Transaction;

public class CreditReportService {
    public CreditAccountReport createReport(Account account) {
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
        MinimumPaymentVisitor mpv = new MinimumPaymentVisitor(newBalance);
        account.accept(mpv);
        double totalDue = mpv.getMininumPayment();
        return new CreditAccountReport(previousBalance, totalPayments, totalCredits, newBalance, totalDue);
    }
}
