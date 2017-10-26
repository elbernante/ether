package edu.mum.cs525.project.creditcard.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.mum.cs525.framework.ApplicationContext;
import edu.mum.cs525.framework.Command.CommandsManager;
import edu.mum.cs525.framework.Command.DepositCommand;
import edu.mum.cs525.framework.Command.WithdrawCommand;
import edu.mum.cs525.framework.entity.Account;
import edu.mum.cs525.framework.exceptions.DeclinedException;
import edu.mum.cs525.project.creditcard.CreditAccountService;
import edu.mum.cs525.project.creditcard.account.CreditAccount;

public class CreditActionHandler implements ActionListener {

	String clientName, street, city, zip, state, accountType, amountDeposit, expdate, ccnumber;
	boolean newaccount;
	private DefaultTableModel model;
	private JTable JTable1;
	private Object rowdata[];
	
	private CreditAccountService accountService = (CreditAccountService) ApplicationContext.getAccountService();

	public CreditActionHandler(JTable table) {
		this.JTable1 = table;
		this.model = (DefaultTableModel) table.getModel();
		rowdata = new Object[7];
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		String object = ((JButton) event.getSource()).getText();
		if (object.equalsIgnoreCase("Exit"))
			JButtonExit_actionPerformed(event);
		else if (object.equalsIgnoreCase("Add Credit-Card Account"))
			JButtonNewCCAC_actionPerformed(event);
		else if (object.equalsIgnoreCase("Generate Montly Bills"))
			JButtonGenerateBill_actionPerformed(event);
		else if (object.equalsIgnoreCase("Deposit"))
			JButtonDeposit_actionPerformed(event);
		else if (object.equalsIgnoreCase("Charge"))
			JButtonWithdraw_actionPerformed(event);
	}

	// When the Exit button is pressed this code gets executed
	// this will exit from the system
	void JButtonExit_actionPerformed(java.awt.event.ActionEvent event) {
		System.exit(0);
	}

	void JButtonNewCCAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 * JDialog_AddPAcc type object is for adding personal information
		 * construct a JDialog_AddPAcc type object set the boundaries and show
		 * it
		 */

		JDialog_AddCCAccount ccac = new JDialog_AddCCAccount(account -> {
			insertAccountToTable(account);
		});
		ccac.setBounds(450, 20, 300, 380);
		ccac.show();
	}

	void JButtonGenerateBill_actionPerformed(java.awt.event.ActionEvent event) {
		JDialogGenBill billFrm = new JDialogGenBill();
		billFrm.setBounds(450, 20, 400, 350);
		billFrm.show();
	}

	void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event) {
		// get selected name
		int selection = JTable1.getSelectionModel().getMinSelectionIndex();
		if (selection >= 0) {
			String ccNumber = (String) model.getValueAt(selection, 1);

			// Show the dialog for adding deposit amount for the current mane
			JDialog_Deposit dep = new JDialog_Deposit(ccNumber, paymentAmount -> {
				CreditAccount acc = (CreditAccount) accountService.getAccount(ccNumber);
				if (null != acc) {
					DepositCommand dc = new DepositCommand(accountService, acc.getAccountNumber(), paymentAmount);
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
			String ccNumber = (String) model.getValueAt(selection, 1);

			// Show the dialog for adding withdraw amount for the current mane
			JDialog_Withdraw wd = new JDialog_Withdraw(ccNumber, purchaseAmount -> {
				CreditAccount acc = (CreditAccount) accountService.getAccount(ccNumber);
				if (null != acc) {
					WithdrawCommand wc = new WithdrawCommand(accountService, acc.getAccountNumber(), purchaseAmount);
					CommandsManager.getInstance().setCommand(wc);
					try {
						CommandsManager.getInstance().invokeCommand();
						updateValueAtRow(selection, acc);
					} catch (DeclinedException e) {
						JOptionPane.showMessageDialog(null, 
								"New purchase of $" + purchaseAmount + " exceeds the credit limit of $" + acc.getCreditLimit(), 
								"Credit Limit Exceeded! ", 
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			wd.setBounds(430, 15, 275, 140);
			wd.show();
		}

	}
	
	private void clearTable() {
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}
	
	private void reloadAccounts() {
		clearTable();
		for (Account acc : accountService.getAllAccounts()) {
			insertAccountToTable((CreditAccount)acc);
		}
	}
	
	private void insertAccountToTable(CreditAccount acc) {
		model.addRow(accountToObjectArray(acc));
	}
	
	private void updateValueAtRow(int rowNum, CreditAccount acc) {
		Object[] cell = accountToObjectArray(acc);
		for (int i = 0; i < cell.length; i++) {
			model.setValueAt(cell[i], rowNum, i);
		}
	}
	
	private Object[] accountToObjectArray(CreditAccount acc) {
		Object [] cell = new Object[5];
		cell[0] = acc.getCustomer().getName();
		cell[1] = acc.getAccountNumber();
		cell[2] = acc.getExpiryDate();
		cell[3] = acc.getAccountType();
		cell[4] = acc.getBalance();
		return cell;
	}

}
