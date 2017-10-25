package edu.mum.cs525.framework;

public interface ReportService {
    default Report createReport(Customer customer){
        return null;
    };
}
