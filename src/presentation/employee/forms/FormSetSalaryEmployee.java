package presentation.employee.forms;

import business.employee.TEmployee;
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
		JPanel ret = ViewHelpers.createFieldPanel(1);

		//ID
		JLabel idLabel = new JLabel("Id");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

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

					//invoke controller with the operation "Set Salary Employee".
					dispose();
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
