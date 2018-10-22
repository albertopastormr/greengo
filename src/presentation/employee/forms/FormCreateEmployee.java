package presentation.employee.forms;

import business.employee.TpermanentEmployee;
import business.employee.TtemporaryEmployee;
import presentation.util.Util;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormCreateEmployee extends JDialog {

	/*Attributes*/

	/*JTextField fields: */
	private JTextField idCardNumberText;
	private JTextField idMainOfficeText;
	private JTextField workedHoursText;
	private JTextField appotionmentText;
	private JTextField salaryText;

	/*JComboBox fields*/
	private JComboBox activeComboBox;
	private JComboBox typeComboBox;



	public FormCreateEmployee(){
		setTitle("Alta empleado");
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

		JPanel ret = new JPanel(new GridLayout(7, 2, 0, 7));
		Border border = ret.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		ret.setBorder(new CompoundBorder(border, margin));

		//Id card
		JLabel idCardLabel = new JLabel("DNI");
		ret.add(idCardLabel);

		idCardNumberText = new JTextField(10);
		ret.add(idCardNumberText);

		//Salary
		JLabel salaryLabel = new JLabel("Salary");
		ret.add(salaryLabel);

		salaryText = new JTextField(10);
		ret.add(salaryText);

		//Id Main Office
		JLabel idMainOfficeLabel = new JLabel("Id sede");
		ret.add(idMainOfficeLabel);

		idMainOfficeText = new JTextField(10);
		ret.add(idMainOfficeText);

		//Active
		JLabel activeLabel = new JLabel("Active");
		ret.add(activeLabel);

		selectActive();
		ret.add(activeComboBox);

		//Type
		JLabel typeLabel = new JLabel("Type");
		ret.add(typeLabel);

		selectType();

		ret.add(typeComboBox);

		//Worked hours
		JLabel workedHours = new JLabel("Worked hours");
		ret.add(workedHours);

		workedHoursText = new JTextField(10);
		ret.add(workedHoursText);

		//Apportionment
		JLabel apportionmentLabel = new JLabel("Apportionment");
		ret.add(apportionmentLabel);

		appotionmentText = new JTextField(10);
		appotionmentText.setEnabled(false);
		ret.add(appotionmentText);


		return ret;
	}


	private JPanel buttonsPanel() {
		JPanel ret = new JPanel(new FlowLayout());


		JButton createButton = new JButton("CREAR");
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					if (typeComboBox.getSelectedItem().equals("Temporary")) {
						TtemporaryEmployee emp = new TtemporaryEmployee();
						emp.setId_card_number(Util.parseString(idCardNumberText.getText()));
						emp.setActive(Util.parseActive(activeComboBox.getSelectedItem().toString()));
						emp.setWorked_hours(Util.parseNoNegativeInt(workedHoursText.getText()));
						emp.setSalary(Util.parseNoNegativeFloat(salaryText.getText()));
						emp.setType(Util.parseString(typeComboBox.getSelectedItem().toString()));

						//Invoke the controller and execute "Set salary" operation.

					}
					else{
						TpermanentEmployee emp = new TpermanentEmployee();
						emp.setId_card_number(Util.parseString(idCardNumberText.getText()));
						emp.setActive(Util.parseActive(activeComboBox.getSelectedItem().toString()));
						emp.setApportionment(Util.parseNoNegativeInt(appotionmentText.getText()));
						emp.setSalary(Util.parseNoNegativeFloat(salaryText.getText()));
						emp.setType(Util.parseString(typeComboBox.getSelectedItem().toString()));

						//Invoke the controller and execute "Set salary" operation.
					}

					//Invoke the controller and execute "Create Employee" operation.

				} catch(Exception e){
					JOptionPane.showMessageDialog(getRootPane(), e.getMessage(),
							"ERROR ALTA EMPLEADO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton cancelButton = new JButton("CANCELAR");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				dispose();
			}
		});

		ret.add(createButton);
		ret.add(cancelButton);

		return ret;
	}

	private void selectActive() {
		activeComboBox = new JComboBox();
		activeComboBox.addItem("True");
		activeComboBox.addItem("False");
	}

	private void selectType(){
		typeComboBox = new JComboBox();
		typeComboBox.addItem("Temporary");
		typeComboBox.addItem("Permanent");

		typeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if(typeComboBox.getSelectedItem().equals("Temporary")) {
					appotionmentText.setEnabled(false);
					workedHoursText.setEnabled(true);
				}
				else{
					workedHoursText.setEnabled(false);
					appotionmentText.setEnabled(true);
				}
			}
		});

	}





}
