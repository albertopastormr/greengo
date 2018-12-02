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

public class FormUpdateContract extends JDialog {

	private JTextField idText;
	private JTextField serviceLevelText;

	public FormUpdateContract() {
		setTitle("Update contract");
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
		JPanel ret = ViewHelpers.createFieldPanel(2);

		//ID
		JLabel idLabel = new JLabel("ID");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

		//service Level
		JLabel serviceLevelLabel = new JLabel("service Level");
		ret.add(serviceLevelLabel);

		serviceLevelText = new JTextField(10);
		ret.add(serviceLevelText);


		return ret;
	}

	private JPanel buttonsPanel(){

		//Buttons
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		JButton update = ViewHelpers.buttonsForms("UPDATE");

		update.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TContract contract = new TContract();
				try {
					/*This is related to JPA*/
					contract.setId(Util.parseNoNegativeInt(idText.getText()));
					contract.setServiceLevel(Util.parseNoNegativeInt(serviceLevelText.getText()));
					dispose();
					AppController.getInstance().execute(new LightContext(Event.UPDATE_CONTRACT, contract));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(getRootPane(), ex.getMessage(), "ERROR UPDATE CONTRACT", JOptionPane.ERROR_MESSAGE);
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
		FormUpdateContract formUpdateContract = new FormUpdateContract();
		formUpdateContract.setVisible(true);
	}
}