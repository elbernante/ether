package edu.mum.cs525.framework.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

public abstract class UITemplate {

	private JTable table;
	private String title;
	private String withdrawText;
	private ActionListener listener;

	public UITemplate(JTable table, String frameTitle, String withdrawText, ActionListener listener) {
		this.table = table;
		this.title = frameTitle;
		this.withdrawText = withdrawText;
		this.listener = listener;
		createUI();
	}

	public JFrame createUI() {

		JFrame frame = new JFrame();

		// Header Panel
		JPanel headerPanel = new JPanel();
		headerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		headerPanel.add(header(listener));

		frame.add(headerPanel, BorderLayout.NORTH);

		// Table Scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		frame.add(scrollPane);

		// Sidebar panel
		Box box = Box.createVerticalBox();

		// Deposit
		JButton depositButton = new JButton("Deposit");
		depositButton.addActionListener(listener);
		box.add(depositButton);

		// Withdraw
		JButton withDraw = new JButton(withdrawText);
		withDraw.addActionListener(listener);
		box.add(withDraw);
		box.add(sideBar(listener));

		JButton exit = new JButton("Exit");
		exit.addActionListener(listener);

		box.add(exit);
		frame.add(box, BorderLayout.EAST);

		frame.setTitle(title);
		frame.setSize(575, 310);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		return frame;
	}

	public abstract Box sideBar(ActionListener listener);

	public abstract JPanel header(ActionListener listerner);

}
