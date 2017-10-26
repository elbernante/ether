package edu.mum.cs525.project.creditcard.reports;

import edu.mum.cs525.project.creditcard.account.BronzeAccount;
import edu.mum.cs525.project.creditcard.account.GoldAccount;
import edu.mum.cs525.project.creditcard.account.SilverAccount;

public interface CreditAccountVisitor {
	public void visit(GoldAccount account);
	public void visit(SilverAccount account);
	public void visit(BronzeAccount account);
}
