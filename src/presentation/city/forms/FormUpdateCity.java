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

public class FormUpdateCity extends JDialog {
	/*Attributes*/
	/*JTextField fields*/
	private JTextField nameText;
	private JTextField idText;

	/*JComboBox fields*/
	private JComboBox activeComboBox;

	public FormUpdateCity(){
		setTitle("Update city");
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
		JPanel ret = new JPanel(new GridLayout(3, 2, 0, 7));
		Border border = ret.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		ret.setBorder(new CompoundBorder(border, margin));

		//Id
		JLabel idLabel = new JLabel("Id");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

		//Name
		JLabel nameLabel = new JLabel("Name");
		ret.add(nameLabel);

		nameText = new JTextField(10);
		ret.add(nameText);

		//Active
		JLabel activeLabel = new JLabel("Active");
		ret.add(activeLabel);

		activeComboBox = ViewHelpers.selectActive();
		ret.add(activeComboBox);

		return ret;
	}

	private JPanel buttonsPanel(){
		JPanel ret = new JPanel(new FlowLayout());

		JButton create = ViewHelpers.buttonsForms("UPDATE");
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				TCity city = new TCity();
				try{
					city.setId(Util.parseNoNegativeInt(idText.getText()));
					city.setName(Util.parseString(nameText.getText()));
					city.setActive(Util.parseActive(activeComboBox.getSelectedItem().toString()));

					//Invoke the controller with the operation "Update City"
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(getRootPane(), e.getMessage(),
							"ERROR IN UPDATE CITY", JOptionPane.ERROR_MESSAGE);
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

	public static void main(String[] Args){
		FormUpdateCity f = new FormUpdateCity();
		f.setVisible(true);
	}

}
