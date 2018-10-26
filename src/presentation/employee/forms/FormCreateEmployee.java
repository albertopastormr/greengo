package presentation.employee.forms;

import business.employee.TPermanentEmployee;
import business.employee.TTemporaryEmployee;
import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.View;
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
		setTitle("Create employee");
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

		JPanel ret = ViewHelpers.createFieldPanel(7);

		//Id card
		JLabel idCardLabel = new JLabel("Id number");
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

		activeComboBox = ViewHelpers.selectActive();
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


		JButton create = ViewHelpers.buttonsForms("CREATE");

		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					if (typeComboBox.getSelectedItem().equals("Temporary")) {
						/*This is related to JPA*/
						TTemporaryEmployee emp = new TTemporaryEmployee();
						emp.setId_card_number(Util.parseString(idCardNumberText.getText()));
						emp.setActive(Util.parseActive(activeComboBox.getSelectedItem().toString()));
						emp.setWorked_hours(Util.parseNoNegativeInt(workedHoursText.getText()));
						emp.setSalary(Util.parseNoNegativeFloat(salaryText.getText()));
						emp.setType(Util.parseString(typeComboBox.getSelectedItem().toString()));
					}
					else{
						/*This is related to JPA*/
						TPermanentEmployee emp = new TPermanentEmployee();
						emp.setId_card_number(Util.parseString(idCardNumberText.getText()));
						emp.setActive(Util.parseActive(activeComboBox.getSelectedItem().toString()));
						emp.setApportionment(Util.parseNoNegativeInt(appotionmentText.getText()));
						emp.setSalary(Util.parseNoNegativeFloat(salaryText.getText()));
						emp.setType(Util.parseString(typeComboBox.getSelectedItem().toString()));
					}
					dispose();
					//Invoke the controller and execute "Create Employee" operation.

				} catch(Exception e){
					JOptionPane.showMessageDialog(getRootPane(), e.getMessage(),
							"ERROR IN CREATE EMPLOYEE", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton cancel = ViewHelpers.buttonsForms("CANCEL");

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				dispose();
			}
		});

		ret.add(create);
		ret.add(cancel);

		return ret;
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

	public static void main(String[] Args){
		FormCreateEmployee f = new FormCreateEmployee();
		f.setVisible(true);
	}



}
