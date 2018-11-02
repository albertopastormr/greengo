package presentation.employee.forms;

import business.employee.TEmployee;
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

public class FormSetSalaryEmployee extends JDialog {

	/*Attributes*/
	private JTextField idText;
	private JTextField salaryText;

	public FormSetSalaryEmployee(){
		setTitle("Set salary");
		setResizable(false);
		Util.addEscapeListener(this);
		initGUI();
	}

	private void initGUI(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		mainPanel.add(fieldsPanel());
		mainPanel.add(buttonsPanel());

		add(mainPanel);
		pack();
		setLocationRelativeTo(null);
	}

	private JPanel fieldsPanel(){
		JPanel ret = ViewHelpers.createFieldPanel(2);

		//ID
		JLabel idLabel = new JLabel("Id");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

		//Salary
		JLabel salaryLabel = new JLabel("Salary");
		ret.add(salaryLabel);

		salaryText = new JTextField(10);
		ret.add(salaryText);

		return ret;
	}

	private JPanel buttonsPanel(){
		JPanel ret = new JPanel(new FlowLayout());

		JButton show = ViewHelpers.buttonsForms("SET");

		show.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				TEmployee emp = new TEmployee();
				try{
					/*This is related to JPA*/
					emp.setId(Util.parseNoNegativeInt(idText.getText()));
					emp.setSalary(Util.parseNoNegativeFloat(salaryText.getText()));
					dispose();
					AppController.getInstance().execute(new LightContext(Event.SET_SALARY_EMPLOYEE, emp));
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(getRootPane(), e.getMessage(),
							"ERROR IN SET SALARY EMPLOYEE", JOptionPane.ERROR_MESSAGE);
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

		ret.add(show);
		ret.add(cancel);

		return ret;
	}

	public static void main(String[] Args){
		FormSetSalaryEmployee f = new FormSetSalaryEmployee();
		f.setVisible(true);
	}

}
