package edu.mum.cs525.project.bank.ui;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import edu.mum.cs525.framework.ui.UITemplate;

public class BankUI extends UITemplate {

	public BankUI(JTable table, String frameTitle, String withdrawText, ActionListener listener) {
		super(table, frameTitle, withdrawText, listener);
	}

	@Override
	public Box sideBar(ActionListener listener) {

		Box box = Box.createVerticalBox();
		JButton newButton = new JButton("Get Report");
		newButton.addActionListener(listener);
		box.add(newButton);
		return box;
	}

	@Override
	public JPanel header(ActionListener listerner) {
		JPanel panel = new JPanel();

		JButton addPersonalAccount = new JButton("Add Personal Account");
		addPersonalAccount.addActionListener(listerner);
		panel.add(addPersonalAccount);

		JButton addBusinessAccount = new JButton("Add Company Account");
		addBusinessAccount.addActionListener(listerner);
		panel.add(addBusinessAccount);
		
		
		JButton addInterest = new JButton("Add Interest");
		addInterest.addActionListener(listerner);
		panel.add(addInterest);

		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		return panel;
	}

	public static void load(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		model.addColumn("AccountNr");
		model.addColumn("Name");
		model.addColumn("City");
		model.addColumn("Customer Type");
		model.addColumn("Account Type");
		model.addColumn("Amount");
		table.setModel(model);

		BankActionHandler a = new BankActionHandler(table);

		new BankUI(table, "Banking", "Withdraw", a);
	}

}
