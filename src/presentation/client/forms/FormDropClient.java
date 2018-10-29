package presentation.client.forms;

import business.client.TClient;
import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormDropClient extends JDialog{

	private JTextField idText;

	public FormDropClient() {
		setTitle("Drop client");
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

	private JPanel fieldsPanel(){
		JPanel ret = ViewHelpers.createFieldPanel(1);

		//ID
		JLabel idLabel = new JLabel("ID");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

		return  ret;
	}

	private JPanel buttonsPanel(){

		//Buttons
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		JButton drop = ViewHelpers.buttonsForms("DROP");

		drop.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TClient client = new TClient();
				try {
					/*This is related to JPA*/
					client.setId(Util.parseNoNegativeInt(idText.getText()));
					dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(getRootPane(), ex.getMessage(), "ERROR DROP CLIENT", JOptionPane.ERROR_MESSAGE);
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
		FormDropClient formDropClient = new FormDropClient();
		formDropClient.setVisible(true);
	}
}
