package presentation.client.forms;

import business.client.TClient;
import presentation.controller.AppController;
import presentation.controller.Event;
import presentation.controller.LightContext;
import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormUpdateClient extends JDialog{

	private JTextField idText;
	private JTextField dniText;
	private JTextField numRentalsText;
	private JComboBox activeComboBox;

	public FormUpdateClient() {
		setTitle("Update client");
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
		JPanel ret = ViewHelpers.createFieldPanel(3);

		//ID
		JLabel idLabel = new JLabel("ID");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

		//DNI
		JLabel dniLabel = new JLabel("DNI");
		ret.add(dniLabel);

		dniText = new JTextField(10);
		ret.add(dniText);

		//Num rentals
		JLabel numRentals = new JLabel("Num rentals");
		ret.add(numRentals);

		numRentalsText = new JTextField(10);
		ret.add(numRentalsText);

		return ret;
	}

	private JPanel buttonsPanel(){

		//Buttons
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		JButton update = ViewHelpers.buttonsForms("UPDATE");

		update.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TClient client = new TClient();
				try {
					/*This is related to JPA*/
					client.setId(Util.parseNoNegativeInt(idText.getText()));
					client.setIdCardNumber(Util.parseString(dniText.getText()));
					client.setNumRentals(Util.parseNoNegativeInt(numRentalsText.getText()));
					client.setActive(true);
					dispose();
					AppController.getInstance().execute(new LightContext(Event.UPDATE_CLIENT, client));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(getRootPane(), ex.getMessage(), "ERROR UPDATE CLIENT", JOptionPane.ERROR_MESSAGE);
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

		buttonsPanel.add(update);
		buttonsPanel.add(cancel);

		return buttonsPanel;
	}

	public static void main(String[] args) {
		FormUpdateClient formUpdateClient = new FormUpdateClient();
		formUpdateClient.setVisible(true);
	}
}
