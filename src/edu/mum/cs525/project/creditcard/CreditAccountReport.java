package edu.mum.cs525.project.creditcard;

public class CreditAccountReport {
    private double previousBalance;
    private double totalCharges;
    private double totalCredits;
    private double newBalance;
    private double totalDue;

    public CreditAccountReport(double previousBalance, double totalCharges, double totalCredits, double newBalance, double totalDue) {
        this.previousBalance = previousBalance;
        this.totalCharges = totalCharges;
        this.totalCredits = totalCredits;
        this.newBalance = newBalance;
        this.totalDue = totalDue;
    }

    @Override
    public String toString() {
        return "CreditAccountReport{" +
                "previousBalance=" + previousBalance +
                ", totalCharges=" + totalCharges +
                ", totalCredits=" + totalCredits +
                ", newBalance=" + newBalance +
                ", totalDue=" + totalDue +
                '}';
    }
}
