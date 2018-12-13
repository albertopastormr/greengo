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

public class FormDropContract extends JDialog{

		private JTextField idMainOfficeText;
		private JTextField idServiceText;

		public FormDropContract() {
			setTitle("Drop contract");
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
		JLabel idMainOfficeLabel = new JLabel("MainOffice");
		ret.add(idMainOfficeLabel);

		idMainOfficeText = new JTextField(10);
		ret.add(idMainOfficeText);


		//ID
		JLabel idServiceLabel = new JLabel("Service");
		ret.add(idServiceLabel);

		idServiceText = new JTextField(10);
		ret.add(idServiceText);
		return ret;
	}

	private JPanel buttonsPanel(){

		//Buttons
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		JButton drop = ViewHelpers.buttonsForms("DROP");

		drop.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TContract contract = new TContract();
				try {
					/*This is related to JPA*/
					dispose();
					contract.setIdMainOffice(Util.parseNoNegativeInt(idMainOfficeText.getText()));
					contract.setIdService(Util.parseNoNegativeInt(idServiceText.getText()));

					AppController.getInstance().execute(new LightContext(Event.DROP_CONTRACT, contract));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(getRootPane(), ex.getMessage(), "ERROR DROP CONTRACT", JOptionPane.ERROR_MESSAGE);
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

		buttonsPanel.add(drop);
		buttonsPanel.add(cancel);

		return buttonsPanel;
	}

	public static void main(String[] args) {
		FormDropContract formDropContract = new FormDropContract();
		formDropContract.setVisible(true);
	}
}
