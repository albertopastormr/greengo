package presentation.rental.forms;

import business.rental.TRental;
import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormShowRental extends JDialog{

	/*Attributes*/
	private JTextField idText;

	public FormShowRental(){
		setTitle("Show rental");
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
				TRental rent = new TRental();
				try{
					rent.setId(Util.parseNoNegativeInt(idText.getText()));
					dispose();
					//invoke controller with the operation "Show Rental".
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(getRootPane(), e.getMessage(),
							"ERROR IN SHOW RENTAL", JOptionPane.ERROR_MESSAGE);
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
		FormShowRental f = new FormShowRental();
		f.setVisible(true);
	}
}
