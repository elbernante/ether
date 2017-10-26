package edu.mum.cs525.project.creditcard.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.mum.cs525.framework.ApplicationContext;
import edu.mum.cs525.framework.command.CommandsManager;
import edu.mum.cs525.framework.command.DepositCommand;
import edu.mum.cs525.framework.command.WithdrawCommand;
import edu.mum.cs525.framework.entity.Account;
import edu.mum.cs525.framework.entity.Address;
import edu.mum.cs525.framework.entity.Customer;
import edu.mum.cs525.project.creditcard.CreditAccountService;

public class CreditActionHandler implements ActionListener {

	String clientName, street, city, zip, state, accountType, amountDeposit, expdate, ccnumber;
	boolean newaccount;
	private DefaultTableModel model;
	private JTable JTable1;
	private Object rowdata[];

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

		JDialog_AddCCAccount ccac = new JDialog_AddCCAccount(this);
		ccac.setBounds(450, 20, 300, 380);
		ccac.show();

		if (newaccount) {
			// add row to table
			rowdata[0] = clientName;
			rowdata[1] = ccnumber;
			rowdata[2] = expdate;
			rowdata[3] = accountType;
			rowdata[4] = "0";
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
				acc = service.createGoldAccount(ccnumber, cust);
			}
			else if ("Silver" == accountType)
			{
				acc = service.createSilverAccount(ccnumber, cust);
			}
			else if ("Bronze" == accountType)
			{
				acc = service.createBronzeAccount(ccnumber, cust);
			}
			else
			{
				System.out.println("error");
			}
			acc.setAccountNumber(ccnumber);
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

	void JButtonGenerateBill_actionPerformed(java.awt.event.ActionEvent event) {
		JDialogGenBill billFrm = new JDialogGenBill();
		billFrm.setBounds(450, 20, 400, 350);
		billFrm.show();

	}

	void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event) {
		// get selected name
		int selection = JTable1.getSelectionModel().getMinSelectionIndex();
		if (selection >= 0) {
			String name = (String) model.getValueAt(selection, 0);

			// Show the dialog for adding deposit amount for the current mane
			JDialog_Deposit dep = new JDialog_Deposit(this, name);
			dep.setBounds(430, 15, 275, 140);
			dep.show();

			// compute new amount
			long deposit = Long.parseLong(amountDeposit);
			String samount = (String) model.getValueAt(selection, 4);
			long currentamount = Long.parseLong(samount);
			long newamount = currentamount + deposit;
			model.setValueAt(String.valueOf(newamount), selection, 4);
			
			
			// do real deposit
			DepositCommand dc = new DepositCommand(ApplicationContext.getAccountService(), ccnumber, deposit);
			CommandsManager.getInstance().setCommand(dc);
			CommandsManager.getInstance().invokeCommand();
		}

	}

	void JButtonWithdraw_actionPerformed(java.awt.event.ActionEvent event) {
		// get selected name
		int selection = JTable1.getSelectionModel().getMinSelectionIndex();
		if (selection >= 0) {
			String name = (String) model.getValueAt(selection, 0);

			// Show the dialog for adding withdraw amount for the current mane
			JDialog_Withdraw wd = new JDialog_Withdraw(this, name);
			wd.setBounds(430, 15, 275, 140);
			wd.show();

			// compute new amount
			long deposit = Long.parseLong(amountDeposit);
			String samount = (String) model.getValueAt(selection, 4);
			long currentamount = Long.parseLong(samount);
			long newamount = currentamount - deposit;
			model.setValueAt(String.valueOf(newamount), selection, 4);
			if (newamount < 0) {
				JOptionPane.showMessageDialog(null,
						" " + name + " Your balance is negative: $" + String.valueOf(newamount) + " !",
						"Warning: negative balance", JOptionPane.WARNING_MESSAGE);
			}

			// do real deposit
			WithdrawCommand wc = new WithdrawCommand(ApplicationContext.getAccountService(), ccnumber, deposit);
			CommandsManager.getInstance().setCommand(wc);
			CommandsManager.getInstance().invokeCommand();
		}

	}	

}
