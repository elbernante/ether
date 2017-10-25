package edu.mum.cs525.project.bank.ui;

import edu.mum.cs525.framework.ApplicationContext;
import edu.mum.cs525.framework.Report;
import edu.mum.cs525.framework.ReportService;

import javax.swing.*;
import java.awt.*;


public class JDialog_GetReport extends JDialog
{

//    private BankFrm parentframe;
    BankActionHandler parentframe;

	public JDialog_GetReport(BankActionHandler parent)
	{
//		super(parent);
		parentframe=parent;

		setTitle("Report");
		setModal(true);

		getContentPane().setLayout(null);
		setSize(800, 600);
		setVisible(false);
		Report report = ApplicationContext.getReportService().createReport(null);
		jTextArea.setText(report.toString());
		jTextArea.setBounds(0, 0, 800, 600);
		getContentPane().add(jTextArea);

//		JButton_OK.setText("OK");
//		JButton_OK.setActionCommand("OK");
//		getContentPane().add(JButton_OK);
//		JButton_OK.setBounds(48, 84, 84, 24);
//
//		JButton_Calcel.setText("Cancel");
//		JButton_Calcel.setActionCommand("Cancel");
//		getContentPane().add(JButton_Calcel);
//		JButton_Calcel.setBounds(156, 84, 84, 24);
//
//
//		SymAction lSymAction = new SymAction();
//		JButton_OK.addActionListener(lSymAction);
//		JButton_Calcel.addActionListener(lSymAction);

	}




	JLabel JLabel1 = new JLabel();
	JLabel JLabel2 = new JLabel();
	JTextField JTextField_NAME = new JTextField();
	JTextField JTextField_AMT = new JTextField();
	JButton JButton_OK = new JButton();
	JButton JButton_Calcel = new JButton();
	JTextArea jTextArea = new JTextArea();

	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == JButton_OK)
				JButtonOK_actionPerformed(event);
			else if (object == JButton_Calcel)
				JButtonCalcel_actionPerformed(event);
		}
	}

	void JButtonOK_actionPerformed(java.awt.event.ActionEvent event)
	{
        parentframe.amountDeposit=JTextField_AMT.getText();
		dispose();
	}

	void JButtonCalcel_actionPerformed(java.awt.event.ActionEvent event)
	{
		dispose();
	}
}