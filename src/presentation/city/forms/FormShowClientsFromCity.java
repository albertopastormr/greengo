package presentation.city.forms;

import business.city.TCity;
import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormShowClientsFromCity extends JDialog {
	/*Attributes*/
	private JTextField idText;

	public FormShowClientsFromCity(){
		setTitle("Show clients from city");
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
		JPanel ret = new JPanel(new GridLayout(1, 2, 0, 7));
		Border border = ret.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		ret.setBorder(new CompoundBorder(border, margin));

		//Name
		JLabel idLabel = new JLabel("Id");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

		return ret;
	}

	private JPanel buttonsPanel(){
		JPanel ret = new JPanel(new FlowLayout());

		JButton drop = ViewHelpers.buttonsForms("SHOW");
		drop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				TCity city = new TCity();
				try{
					city.setId(Util.parseNoNegativeInt(idText.getText()));

					//Invoke the controller with the operation "Show Client from City"
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(getRootPane(), e.getMessage(),
							"ERROR IN SHOW CLIENTS FROM CITY", JOptionPane.ERROR_MESSAGE);
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
		FormShowClientsFromCity f = new FormShowClientsFromCity();
		f.setVisible(true);
	}
}
