package edu.mum.cs525.project.bank.ui;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import edu.mum.cs525.project.bank.reports.AccountsReportVisitor;


public class JDialog_GetReport extends JDialog
{

	public JDialog_GetReport()
	{
		setTitle("Report");
		setModal(true);

		getContentPane().setLayout(null);
		setSize(800, 600);
		setVisible(false);
		String reports = AccountsReportVisitor.generateReports();
		System.out.println(reports);
		jTextArea.setText(reports);
		jTextArea.setBounds(0, 0, 800, 600);
		getContentPane().add(jTextArea);
	}

	JTextArea jTextArea = new JTextArea();
}