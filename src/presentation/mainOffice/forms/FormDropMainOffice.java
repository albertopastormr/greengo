package presentation.mainOffice.forms;

import business.mainoffice.TMainOffice;
import presentation.controller.AppController;
import presentation.controller.Event;
import presentation.controller.LightContext;
import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormDropMainOffice extends JDialog{

	private JTextField idText;

	public FormDropMainOffice(){
		setTitle("Drop main office");
		setResizable(false);
		Util.addEscapeListener(this);
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

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

	private JPanel buttonsPanel() {

		//Buttons
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		JButton drop = ViewHelpers.buttonsForms("DROP");

		drop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TMainOffice mainOffice = new TMainOffice();
				try {
					/*This is related to JPA*/
					mainOffice.setId(Util.parseNoNegativeInt(idText.getText()));
					dispose();
					AppController.getInstance().execute(new LightContext(Event.DROP_MAIN_OFFICE, mainOffice));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(getRootPane(), ex.getMessage(), "ERROR DROP MAIN OFFICE", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton cancel = ViewHelpers.buttonsForms("CANCEL");

		cancel.addActionListener(new ActionListener() {

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
		FormDropMainOffice formDropMainOffice = new FormDropMainOffice();
		formDropMainOffice.setVisible(true);
	}
}
