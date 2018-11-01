package presentation.main_office.forms;

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

public class FormUpdateMainOffice extends JDialog{

	private JTextField idText;
	private JTextField cityText;
	private JTextField addressText;
	private JComboBox activeCombobox;

	public FormUpdateMainOffice(){
		setTitle("Update main office");
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

	private JPanel fieldsPanel(){

		JPanel ret = ViewHelpers.createFieldPanel(4);

		//ID
		JLabel idLabel = new JLabel("ID");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

		//City
		JLabel cityLabel = new JLabel("City");
		ret.add(cityLabel);

		cityText = new JTextField(10);
		ret.add(cityText);

		//Address
		JLabel addressLabel = new JLabel("Address");
		ret.add(addressLabel);

		addressText = new JTextField(10);
		ret.add(addressText);

		//Active
		JLabel activeLabel = new JLabel("Active");
		ret.add(activeLabel);

		activeCombobox = ViewHelpers.selectActive();
		ret.add(activeCombobox);

		return ret;
	}

	private JPanel buttonsPanel(){

		//Buttons
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		JButton update = ViewHelpers.buttonsForms("UPDATE");

		update.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TMainOffice mainOffice = new TMainOffice();
				try {
					/*This is related to JPA*/
					mainOffice.setId(Util.parseNoNegativeInt(idText.getText()));
					mainOffice.setCity(Util.parseString(cityText.getText()));
					mainOffice.setAdress(Util.parseString(addressText.getText()));
					mainOffice.setActive(Util.parseActive(activeCombobox.getSelectedItem().toString()));
					dispose();
					AppController.getInstance().execute(new LightContext(Event.UPDATE_MAIN_OFFICE, mainOffice));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(getRootPane(), ex.getMessage(), "ERROR UPDATE MAIN OFFICE", JOptionPane.ERROR_MESSAGE);
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
		FormUpdateMainOffice formUpdateMainOffice = new FormUpdateMainOffice();
		formUpdateMainOffice.setVisible(true);
	}
}
