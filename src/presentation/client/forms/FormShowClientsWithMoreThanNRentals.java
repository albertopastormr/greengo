package presentation.client.forms;

import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormShowClientsWithMoreThanNRentals extends JDialog{

	private JTextField nrentalsText;

	public FormShowClientsWithMoreThanNRentals() {
		setTitle("Show clients with more than N rentals");
		setResizable(false);
		Util.addEscapeListener(this);
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.white);
		mainPanel.add(fieldsPanel());
		mainPanel.add(buttonsPanel());

		add(mainPanel);
		pack();
		setLocationRelativeTo(null);
	}

	private JPanel fieldsPanel() {
		JPanel ret = new JPanel(new GridLayout(1,2,0,7));
		Border border = ret.getBorder();
		Border margin = new EmptyBorder(10,10,10,10);
		ret.setBorder(new CompoundBorder(border, margin));

		//NRentals
		JLabel nrentalsLabel = new JLabel("NºRentals");
		ret.add(nrentalsLabel);

		nrentalsText = new JTextField(10);
		ret.add(nrentalsText);

		return  ret;
	}

	private JPanel buttonsPanel(){

		//Buttons
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		JButton showN = ViewHelpers.buttonsForms("SHOW");

		showN.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					/*This is related to JPA*/

					//Invoke the controller and execute "Set salary" operation.

					dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(getRootPane(), ex.getMessage(), "ERROR SHOW CLIENTS WITH MORE THAN N RENTALS", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton cancel = ViewHelpers.buttonsForms("CANCEL");

		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		buttonsPanel.add(showN);
		buttonsPanel.add(cancel);

		return buttonsPanel;
	}

	public static void main(String[] args) {
		FormShowClientsWithMoreThanNRentals formShowClientsWithMoreThanNRentals = new FormShowClientsWithMoreThanNRentals();
		formShowClientsWithMoreThanNRentals.setVisible(true);
	}
}
