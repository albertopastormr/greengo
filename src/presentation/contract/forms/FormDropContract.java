package presentation.contract.forms;

import business.contract.TContract;
import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormDropContract extends JDialog{

		private JTextField idText;

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
		JPanel ret = ViewHelpers.createFieldPanel(1);

		//ID
		JLabel idLabel = new JLabel("ID");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

		return ret;
	}

	private JPanel buttonsPanel(){

		//Buttons
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		JButton create = ViewHelpers.buttonsForms("DROP");

		create.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TContract contract = new TContract();
				try {
					/*This is related to JPA*/
					contract.setId(Util.parseNoNegativeInt(idText.getText()));
					dispose();
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

		buttonsPanel.add(create);
		buttonsPanel.add(cancel);

		return buttonsPanel;
	}

	public static void main(String[] args) {
		FormDropContract formDropContract = new FormDropContract();
		formDropContract.setVisible(true);
	}
}
