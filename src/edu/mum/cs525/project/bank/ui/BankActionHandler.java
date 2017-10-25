package edu.mum.cs525.project.bank.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
