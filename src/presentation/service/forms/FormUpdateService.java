package presentation.service.forms;

import business.service.TService;
import presentation.controller.AppController;
import presentation.controller.Event;
import presentation.controller.LightContext;
import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormUpdateService extends  JDialog{

	private JTextField idText;
	private JTextField typeText;
	private JTextField addressText;
	private JTextField capacityText;
	private JTextField vehicles_attendedText;

	public FormUpdateService() {
		setTitle("Update service");
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
		JPanel ret = ViewHelpers.createFieldPanel(5);

		//ID
		JLabel idLabel = new JLabel("ID");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

		//Type
		JLabel typeLabel = new JLabel("Type");
		ret.add(typeLabel);

		typeText = new JTextField(10);
		ret.add(typeText);

		//Address
		JLabel addressLabel = new JLabel("Adress");
		ret.add(addressLabel);

		addressText = new JTextField(10);
		ret.add(addressText);

		//Capacity
		JLabel capacityLabel = new JLabel("Capacity");
		ret.add(capacityLabel);

		capacityText = new JTextField(10);
		ret.add(capacityText);

		//Vehicles_attended
		JLabel vehicles_attendedLabel = new JLabel("Vehicles attended");
		ret.add(vehicles_attendedLabel);

		vehicles_attendedText = new JTextField(10);
		ret.add(vehicles_attendedText);

		return ret;
	}

	private JPanel buttonsPanel(){
		//Buttons
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		JButton update = ViewHelpers.buttonsForms("UPDATE");

		update.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TService service = new TService();
				try {
					/*This is related to JPA*/
					service.setId(Util.parseNoNegativeInt(idText.getText()));
					service.setType(Util.parseString(typeText.getText()));
					service.setAddress(Util.parseString(addressText.getText()));
					service.setCapacity(Util.parseNoNegativeInt(capacityText.getText()));
					service.setNumVehiclesAttended(Util.parseNoNegativeInt(vehicles_attendedText.getText()));
					service.setActive(true);
					dispose();
					AppController.getInstance().execute(new LightContext(Event.UPDATE_SERVICE, service));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(getRootPane(), ex.getMessage(), "ERROR UPDATE SERVICE", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton cancel = ViewHelpers.buttonsForms("CANCEL");

		update.addActionListener(new ActionListener(){

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
		FormUpdateService formUpdateService = new FormUpdateService();
		formUpdateService.setVisible(true);
	}
}
