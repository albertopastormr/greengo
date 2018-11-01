package presentation.contract.forms;

import business.contract.TContract;
import presentation.controller.AppController;
import presentation.controller.Event;
import presentation.controller.LightContext;
import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormCreateContract extends JDialog {

	private JTextField serviceLevelText;
	private JTextField idMainOfficeText;
	private JTextField idServiceText;
	private JComboBox activeComboBox;

	public FormCreateContract() {
		setTitle("Create contract");
		setResizable(false);
		Util.addEscapeListener(this);
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.white);
		mainPanel.add(fieldsPanel());
		mainPanel.add(buttonsPanel());

		add(mainPanel);
		pack();
		setLocationRelativeTo(null);
	}

	private JPanel fieldsPanel() {
		JPanel ret = ViewHelpers.createFieldPanel(4);

		//Service Level
		JLabel serviceLevelLabel = new JLabel("Service Level");
		ret.add(serviceLevelLabel);

		serviceLevelText = new JTextField(10);
		ret.add(serviceLevelText);

		//ID Main Office
		JLabel idMainOfficeLabel = new JLabel("ID Main Office");
		ret.add(idMainOfficeLabel);

		idMainOfficeText = new JTextField(10);
		ret.add(idMainOfficeText);

		//ID Service
		JLabel idServiceLabel = new JLabel("ID Service");
		ret.add(idServiceLabel);

		idServiceText = new JTextField(10);
		ret.add(idServiceText);

		//Active
		JLabel activeLabel = new JLabel("Active");
		ret.add(activeLabel);

		activeComboBox = ViewHelpers.selectActive();
		ret.add(activeComboBox);

		return ret;
	}

	private JPanel buttonsPanel(){

		//Buttons
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		JButton create = ViewHelpers.buttonsForms("CREATE");

		create.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TContract contract = new TContract();
				try {
					/*This is related to JPA*/
					contract.setServiceLevel(Util.parseNoNegativeInt(serviceLevelText.getText()));
					contract.setIdMainOffice(Util.parseNoNegativeInt(idMainOfficeText.getText()));
					contract.setIdService(Util.parseNoNegativeInt(idMainOfficeText.getText()));
					contract.setActive(Util.parseActive(activeComboBox.getSelectedItem().toString()));
					dispose();
					AppController.getInstance().execute(new LightContext(Event.CREATE_CONTRACT, contract));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(getRootPane(), ex.getMessage(), "ERROR CREATE CONTRACT", JOptionPane.ERROR_MESSAGE);
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
		FormCreateContract formCreateContract = new FormCreateContract();
		formCreateContract.setVisible(true);
	}
}