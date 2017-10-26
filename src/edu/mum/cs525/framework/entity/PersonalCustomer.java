package edu.mum.cs525.framework.entity;

import java.time.LocalDate;

public class PersonalCustomer extends Customer {
	private LocalDate dateOfBirth;
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@Override
	public String getCustomerTypeName() {
		return "PERSONAL";
	}
}
