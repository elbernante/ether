package edu.mum.cs525.project.bank.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.mum.cs525.framework.Account;
import edu.mum.cs525.framework.ApplicationContext;
import edu.mum.cs525.framework.DeclinedException;
import edu.mum.cs525.framework.Command.AddInterestCommand;
import edu.mum.cs525.framework.Command.CommandsManager;
import edu.mum.cs525.framework.Command.DepositCommand;
import edu.mum.cs525.framework.Command.WithdrawCommand;
import edu.mum.cs525.project.bank.BankAccountService;

public class BankActionHandler implements ActionListener {

	String accountnr, clientName, street, city, zip, state, accountType, clientType, amountDeposit, email;
	boolean newaccount;
	private DefaultTableModel model;
	private JTable JTable1;
	// BankFrm myframe;
	private Object[] rowdata;
	// private String[] rowdata;
	
	private BankAccountService accountService = (BankAccountService) ApplicationContext.getAccountService();

	public BankActionHandler( JTable JTable1) {
		this.model = (DefaultTableModel) JTable1.getModel();
		this.JTable1 = JTable1;
//		rowdata = new Object[8];
		reloadAccounts();
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

		JDialog_AddPAcc pac = new JDialog_AddPAcc(acc -> {
			insertAccountToTable(acc);
		});
		
		pac.setBounds(450, 20, 300, 330);
		pac.show();
	}

	void JButtonCompAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 * construct a JDialog_AddCompAcc type object set the boundaries and
		 * show it
		 */

		JDialog_AddCompAcc pac = new JDialog_AddCompAcc(acc -> {
			insertAccountToTable(acc);
		});
		pac.setBounds(450, 20, 300, 330);
		pac.show();
	}

	void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event) {
		// get selected name
		int selection = JTable1.getSelectionModel().getMinSelectionIndex();
		if (selection >= 0) {
			String accnr = (String) model.getValueAt(selection, 0);

			// Show the dialog for adding deposit amount for the current mane
			JDialog_Deposit dep = new JDialog_Deposit(accnr, deposit -> {
				Account acc = accountService.getAccount(accnr);
				if (null != acc) {
					DepositCommand dc = new DepositCommand(accountService, acc.getAccountNumber(), deposit);
					CommandsManager.getInstance().setCommand(dc);
					CommandsManager.getInstance().invokeCommand();
					updateValueAtRow(selection, acc);
				}
			});
			
			dep.setBounds(430, 15, 275, 140);
			dep.show();
		}
	}

	void JButtonWithdraw_actionPerformed(java.awt.event.ActionEvent event) {
		// get selected name
		int selection = JTable1.getSelectionModel().getMinSelectionIndex();
		if (selection >= 0) {
			String accnr = (String) model.getValueAt(selection, 0);

			// Show the dialog for adding withdraw amount for the current mane
			JDialog_Withdraw wd = new JDialog_Withdraw(accnr, withdrawAmount -> {
				Account acc = accountService.getAccount(accnr);
				if (null != acc) {
					WithdrawCommand wc = new WithdrawCommand(accountService, acc.getAccountNumber(), withdrawAmount);
					CommandsManager.getInstance().setCommand(wc);
					try {
						CommandsManager.getInstance().invokeCommand();
						updateValueAtRow(selection, acc);
					} catch (DeclinedException e) {
						JOptionPane.showMessageDialog(null, 
								"$" + withdrawAmount + " exceeds the current balance of $" + acc.getBalance(), 
								"Insufficient Funds! ", 
								JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			wd.setBounds(430, 15, 275, 140);
			wd.show();
		}
	}

	void JButtonGetReport_actionPerformed(java.awt.event.ActionEvent event) {
		System.out.println("Goooood");
		JDialog_GetReport wd = new JDialog_GetReport(this);
		wd.setBounds(430, 15, 275, 140);
		wd.show();
	}

	void JButtonAddinterest_actionPerformed(java.awt.event.ActionEvent event) {
		
		AddInterestCommand ac = new AddInterestCommand(accountService);
		CommandsManager.getInstance().setCommand(ac);
		CommandsManager.getInstance().invokeCommand();
		
		reloadAccounts();
		
		JOptionPane.showMessageDialog(null,
				"Interests added to all accounts.",
				"Interest",
				JOptionPane.INFORMATION_MESSAGE);

	}
	
	private void clearTable() {
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}
	
	private void reloadAccounts() {
		clearTable();
		for (Account acc : accountService.getAllAccounts()) {
			insertAccountToTable(acc);
		}
	}
	
	private void insertAccountToTable(Account acc) {
		model.addRow(accountToObjectArray(acc));
	}
	
	private void updateValueAtRow(int rowNum, Account acc) {
		Object[] cell = accountToObjectArray(acc);
		for (int i = 0; i < cell.length; i++) {
			model.setValueAt(cell[i], rowNum, i);
		}
	}
	
	private Object[] accountToObjectArray(Account acc) {
		Object [] cell = new Object[6];
		cell[0] = acc.getAccountNumber();
		cell[1] = acc.getCustomer().getName();
		cell[2] = acc.getCustomer().getAddress().getCity();
		cell[3] = acc.getCustomer().getCustomerTypeName();
		cell[4] = acc.getAccountType();
		cell[5] = acc.getBalance();
		return cell;
	}
	

}
