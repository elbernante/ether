package edu.mum.cs525.framework;

public class ReportServiceImpl implements ReportService {
    @Override
    public Report createReport(Customer customer) {
        return new Report(customer, ApplicationContext.getAccountService().getAccounts(customer));
    }
}
