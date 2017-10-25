package edu.mum.cs525.project.ccard.ui;

import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import edu.mum.cs525.framework.ui.UITemplate;

public class CreditUI extends UITemplate {

	public CreditUI(JTable table, String frameTitle, String withdrawText, ActionListener listener) {
		super(table, frameTitle, withdrawText, listener);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Box sideBar(ActionListener listener) {

		Box box = Box.createVerticalBox();
		return box;
	}

	@Override
	public JPanel header(ActionListener listerner) {
		// TODO Auto-generated method stub

		JPanel panel = new JPanel();

		JButton addAccount = new JButton("Add Credit-Card Account");
		addAccount.addActionListener(listerner);
		panel.add(addAccount);

		JButton generateMontlybills = new JButton("Generate Montly Bills");
		generateMontlybills.addActionListener(listerner);
		panel.add(generateMontlybills);

		return panel;
	}
	
	
	public static void main(String[] args) {
		
		DefaultTableModel model = new DefaultTableModel();
        JTable JTable1 = new JTable(model);
        model.addColumn("Name");
        model.addColumn("CC number");
        model.addColumn("Exp date");
        model.addColumn("Type");
        model.addColumn("Balance");
//        rowdata = new Object[7];
		
		CreditActionHandler listener = new CreditActionHandler(JTable1);
        
		CreditUI ui = new CreditUI(JTable1, "Credit System", "Charge", listener);
	}
	
	
	

}
