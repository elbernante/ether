package edu.mum.cs525.framework.entity;

public class BusinessCustomer extends Customer {
	private int numberOfEmployees;
	
	public int getNumberOfEmployees() {
		return numberOfEmployees;
	}
	
	public void setNumberOfEmployees(int numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}
	
	@Override
	public String getCustomerTypeName() {
		return "BUSINESS";
	}
}
