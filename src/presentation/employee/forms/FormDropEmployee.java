package presentation.employee.forms;

import business.employee.TEmployee;
import presentation.util.Util;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormDropEmployee extends JDialog {

	/*Attributes*/

	private JTextField idText;

	public FormDropEmployee(){
		setTitle("Baja empleado");
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
		JPanel ret = new JPanel(new GridLayout(1, 2, 0, 7 ));
		Border border = ret.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		ret.setBorder(new CompoundBorder(border, margin));

		//id
		JLabel idLabel = new JLabel("Id");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

		return ret;
	}

	private JPanel buttonsPanel() {
		JPanel ret = new JPanel(new FlowLayout());

		JButton delete = new JButton("ELIMINAR");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				TEmployee emp = new TEmployee();
				try{
					emp.setId(Util.parseNoNegativeInt(idText.getText()));
					dispose();
					//Invoke the controller with the operation "Drop Employee"
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(getRootPane(), e.getMessage(), "ERROR BAJA EMPLEADO",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton cancel = new JButton("CANCELAR");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				dispose();
			}
		});

		ret.add(delete);
		ret.add(cancel);

		return ret;
	}

	public static void main(String[] Args){
		FormDropEmployee f = new FormDropEmployee();
		f.setVisible(true);
	}
}
