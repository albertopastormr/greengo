package presentation.service.forms;

import business.service.TService;
import presentation.util.Util;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormUpdateService extends  JDialog{

	private JTextField idText;
	private JTextField typeText;
	private JTextField addressText;
	private JTextField capacityText;
	private JTextField vehicles_attendedText;
	private JComboBox activeComboBox;

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
		JPanel ret = new JPanel(new GridLayout(6,2,0,7));
		Border border = ret.getBorder();
		Border margin = new EmptyBorder(10,10,10,10);
		ret.setBorder(new CompoundBorder(border, margin));

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

		//Active
		JLabel activeLabel = new JLabel("Active");
		ret.add(activeLabel);

		selectActive();
		ret.add(activeComboBox);

		return ret;
	}

	private JPanel buttonsPanel(){
		//Buttons
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		JButton create = new JButton("CREATE");
		create.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TService service = new TService();
				try {
					service.setId(Util.parseNoNegativeInt(idText.getText()));
					service.setType(Util.parseString(typeText.getText()));
					service.setAddress(Util.parseString(addressText.getText()));
					service.setCapacity(Util.parseNoNegativeInt(capacityText.getText()));
					service.setVehicles_attended(Util.parseNoNegativeInt(vehicles_attendedText.getText()));
					dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(getRootPane(), ex.getMessage(), "ERROR CREATE SERVICE", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton cancel = new JButton("CANCEL");
		create.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		buttonsPanel.add(create);
		buttonsPanel.add(cancel);

		return buttonsPanel;
	}

	private void selectActive() {
		activeComboBox = new JComboBox();
		activeComboBox.addItem("true");
		activeComboBox.addItem("false");
	}

	public static void main(String[] args) {

		FormUpdateService formUpdateService = new FormUpdateService();
		formUpdateService.setVisible(true);
	}
}
