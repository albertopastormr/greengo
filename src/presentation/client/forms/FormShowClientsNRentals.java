package presentation.client.forms;

import presentation.controller.AppController;
import presentation.controller.Event;
import presentation.controller.LightContext;
import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormShowClientsNRentals extends JDialog{

	private JTextField nrentalsText;

	public FormShowClientsNRentals() {
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
		JPanel ret = ViewHelpers.createFieldPanel(1);

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
					Integer rentals = Util.parseNoNegativeInt(nrentalsText.getText());
					dispose();
					AppController.getInstance().execute(new LightContext(Event.SHOW_CLIENTS_N_RENTAL_CLIENT, rentals));

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
		FormShowClientsNRentals formShowClientsNRentals = new FormShowClientsNRentals();
		formShowClientsNRentals.setVisible(true);
	}
}
