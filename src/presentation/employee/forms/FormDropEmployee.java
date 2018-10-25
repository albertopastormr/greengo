package presentation.employee.forms;

import business.employee.TEmployee;
import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormDropEmployee extends JDialog {

	/*Attributes*/

	private JTextField idText;

	public FormDropEmployee(){
		setTitle("Drop employee");
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

		//id
		JLabel idLabel = new JLabel("Id");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

		return ret;
	}

	private JPanel buttonsPanel() {
		JPanel ret = new JPanel(new FlowLayout());

		JButton drop = ViewHelpers.buttonsForms("DROP");

		drop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				TEmployee emp = new TEmployee();
				try{
					/*This is related to JPA*/
					emp.setId(Util.parseNoNegativeInt(idText.getText()));

					dispose();
					//Invoke the controller with the operation "Drop Employee"
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(getRootPane(), e.getMessage(), "ERROR IN DROP EMPLOYEE",
							JOptionPane.ERROR_MESSAGE);
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

		ret.add(drop);
		ret.add(cancel);

		return ret;
	}

	public static void main(String[] Args){
		FormDropEmployee f = new FormDropEmployee();
		f.setVisible(true);
	}
}
