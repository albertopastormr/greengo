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

public class FormCreateClient extends JDialog{

	private JTextField dniText;
	private JComboBox activeComboBox;

	public FormCreateClient() {
		setTitle("Create client");
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
		JPanel ret = ViewHelpers.createFieldPanel(2);

		//DNI
		JLabel dniLabel = new JLabel("DNI");
		ret.add(dniLabel);

		dniText = new JTextField(10);
		ret.add(dniText);

		//Active
		JLabel activeLabel = new JLabel("Active");
		ret.add(activeLabel);

		activeComboBox = ViewHelpers.selectActive();
		ret.add(activeComboBox);

		return  ret;
	}

	private JPanel buttonsPanel(){

		//Buttons
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		JButton create = ViewHelpers.buttonsForms("CREATE");

		create.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TClient client = new TClient();
				try {
					/*This is related to JPA*/
					client.setIdCardNumber(Util.parseString(dniText.getText()));
					client.setNumRentals(0);
					client.setActive(Util.parseActive(activeComboBox.getSelectedItem().toString()));
					AppController.getInstance().execute(new LightContext(Event.CREATE_CLIENT, client));
					dispose();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(getRootPane(), ex.getMessage(), "ERROR CREATE CLIENT", JOptionPane.ERROR_MESSAGE);
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

		buttonsPanel.add(create);
		buttonsPanel.add(cancel);

		return buttonsPanel;
	}

	public static void main(String[] args) {
		FormCreateClient formCreateClient = new FormCreateClient();
		formCreateClient.setVisible(true);
	}
}
