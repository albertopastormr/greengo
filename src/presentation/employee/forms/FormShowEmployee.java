package presentation.employee.forms;

import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormShowEmployee extends JDialog {
	/*Attributes*/
	private JTextField idText;

	public FormShowEmployee(){
		setTitle("Show employee");
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

		JButton show = ViewHelpers.buttonsForms("SHOW");

		show.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				try{
					/*This is related to JPA*/
					//invoke controller with the operation "Show Employee".
					dispose();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(getRootPane(), e.getMessage(),
							"ERROR IN SHOW EMLOYEE", JOptionPane.ERROR_MESSAGE);
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
		FormShowEmployee f = new FormShowEmployee();
		f.setVisible(true);
	}


}
