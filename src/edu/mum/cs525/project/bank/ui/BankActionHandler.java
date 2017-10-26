package edu.mum.cs525.project.bank.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.mum.cs525.framework.Account;
import edu.mum.cs525.framework.Address;
import edu.mum.cs525.framework.ApplicationContext;
import edu.mum.cs525.framework.Customer;
import edu.mum.cs525.framework.Command.CommandsManager;
import edu.mum.cs525.framework.Command.DepositCommand;
import edu.mum.cs525.framework.Command.WithdrawCommand;
import edu.mum.cs525.project.creditcard.CreditAccountService;

public class BankActionHandler implements ActionListener {

	String accountnr, clientName, street, city, zip, state, accountType, clientType, amountDeposit;
	boolean newaccount;
	private DefaultTableModel model;
	private JTable JTable1;
	// BankFrm myframe;
	private Object[] rowdata;
	// private String[] rowdata;

	public BankActionHandler( JTable JTable1) {
		this.model = (DefaultTableModel) JTable1.getModel();
		this.JTable1 = JTable1;
		rowdata = new Object[8];
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String object = ((JButton)e.getSource()).getText();
		if (object .equals("Exit"))
			JButtonExit_actionPerformed(e);
		else if (object.equals("Add Personal Account"))
			JButtonPerAC_actionPerformed(e);
		else if (object.equals("Add Company Account"))
			JButtonCompAC_actionPerformed(e);
		else if (object.equals("Deposit"))
			JButtonDeposit_actionPerformed(e);
		else if (object.equals("Withdraw"))
			JButtonWithdraw_actionPerformed(e);
		else if (object.equalsIgnoreCase("Get Report"))
			JButtonGetReport_actionPerformed(e);
		else if (object.equals("Add Interest"))
			JButtonAddinterest_actionPerformed(e);
	}

	// When the Exit button is pressed this code gets executed
	// this will exit from the system
	void JButtonExit_actionPerformed(java.awt.event.ActionEvent event) {
		System.exit(0);
	}

	void JButtonPerAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 * JDialog_AddPAcc type object is for adding personal information
		 * construct a JDialog_AddPAcc type object set the boundaries and show
		 * it
		 */

		JDialog_AddPAcc pac = new JDialog_AddPAcc(this);
		pac.setBounds(450, 20, 300, 330);
		pac.show();

		if (newaccount) {
			// add row to table
			rowdata[0] = accountnr;
			rowdata[1] = clientName;
			rowdata[2] = city;
			rowdata[3] = "P";
			rowdata[4] = accountType;
			rowdata[5] = "0";
			model.addRow(rowdata);
			JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
			newaccount = false;
			
			System.out.println("accountType " + accountType);
			// add new credit account:
			CreditAccountService service = (CreditAccountService) ApplicationContext.getAccountService();
			Customer cust = service.createPersonalCustomer();
			Account acc = null;
			if ("Gold" == accountType)
			{
				acc = service.createGoldAccount(accountnr, cust);
			}
			else if ("Silver" == accountType)
			{
				acc = service.createSilverAccount(accountnr, cust);
			}
			else if ("Bronze" == accountType)
			{
				acc = service.createBronzeAccount(accountnr, cust);
			}
			else
			{
				System.out.println("error");
			}
			acc.setAccountNumber(accountnr);
			acc.setBalance(0);
			Address address = new Address();
			address.setCity(city);
			address.setState(state);
			address.setZip(zip);
			address.setStreet(street);
			cust.setAddress(address);
			cust.setName(clientName);;
			//cust.setEmail(email);
			acc.setCustomer(cust);
		}
		
		

	}

	void JButtonCompAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 * construct a JDialog_AddCompAcc type object set the boundaries and
		 * show it
		 */

		JDialog_AddCompAcc pac = new JDialog_AddCompAcc(this);
		pac.setBounds(450, 20, 300, 330);
		pac.show();

		if (newaccount) {
			// add row to table
			rowdata[0] = accountnr;
			rowdata[1] = clientName;
			rowdata[2] = city;
			rowdata[3] = "C";
			rowdata[4] = accountType;
			rowdata[5] = "0";
			model.addRow(rowdata);
			JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
			newaccount = false;
		}

	}

	void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event) {
		// get selected name
		int selection = JTable1.getSelectionModel().getMinSelectionIndex();
		if (selection >= 0) {
			String accnr = (String) model.getValueAt(selection, 0);

			// Show the dialog for adding deposit amount for the current mane
			JDialog_Deposit dep = new JDialog_Deposit(accnr, this);
			dep.setBounds(430, 15, 275, 140);
			dep.show();

			// compute new amount
			long deposit = Long.parseLong(amountDeposit);
			String samount = (String) model.getValueAt(selection, 5);
			long currentamount = Long.parseLong(samount);
			long newamount = currentamount + deposit;
			model.setValueAt(String.valueOf(newamount), selection, 5);
			
			// do real deposit
			DepositCommand dc = new DepositCommand(ApplicationContext.getAccountService(), accountnr, deposit);
			CommandsManager.getInstance().setCommand(dc);
			CommandsManager.getInstance().invokeCommand();
		}

	}

	void JButtonWithdraw_actionPerformed(java.awt.event.ActionEvent event) {
		// get selected name
		int selection = JTable1.getSelectionModel().getMinSelectionIndex();
		if (selection >= 0) {
			String accnr = (String) model.getValueAt(selection, 0);

			// Show the dialog for adding withdraw amount for the current mane
			JDialog_Withdraw wd = new JDialog_Withdraw(accnr,this);
			wd.setBounds(430, 15, 275, 140);
			wd.show();

			// compute new amount
			long deposit = Long.parseLong(amountDeposit);
			String samount = (String) model.getValueAt(selection, 5);
			long currentamount = Long.parseLong(samount);
			long newamount = currentamount - deposit;
			model.setValueAt(String.valueOf(newamount), selection, 5);
			if (newamount < 0) {
				JOptionPane.showMessageDialog(null,
						" Account " + accnr + " : balance is negative: $" + String.valueOf(newamount) + " !",
						"Warning: negative balance", JOptionPane.WARNING_MESSAGE);
			}
			
			WithdrawCommand wc = new WithdrawCommand(ApplicationContext.getAccountService(), accountnr, deposit);
			CommandsManager.getInstance().setCommand(wc);
			CommandsManager.getInstance().invokeCommand();
		}
	}

	void JButtonGetReport_actionPerformed(java.awt.event.ActionEvent event) {
		System.out.println("Goooood");
		JDialog_GetReport wd = new JDialog_GetReport(this);
		wd.setBounds(430, 15, 275, 140);
		wd.show();
	}

	void JButtonAddinterest_actionPerformed(java.awt.event.ActionEvent event) {
		JOptionPane.showMessageDialog(null, "Add interest to all accounts", "Add interest to all accounts",
				JOptionPane.WARNING_MESSAGE);

	}

}
