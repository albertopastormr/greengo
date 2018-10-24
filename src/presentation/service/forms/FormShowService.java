package presentation.service.forms;

import business.service.TService;
import presentation.util.Util;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormShowService extends JDialog{

	private JTextField idText;

	public FormShowService() {
		setTitle("Show service");
		setResizable(false);
		Util.addEscapeListener(this);
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

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

		//ID
		JLabel idLabel = new JLabel("ID");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

		return  ret;
	}

	private JPanel buttonsPanel() {
		//Buttons
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		JButton show = new JButton("SHOW");
		show.setBounds(0, 0, 25, 20);
		show.setForeground(Color.white);
		show.setBackground(new Color(119,171,89));

		show.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					/*This is related to JPA*/

					//Invoke the controller and execute "Set salary" operation.

					dispose();
				}catch (Exception ex){
					JOptionPane.showMessageDialog(getRootPane(), ex.getMessage(), "ERROR DROP SERVICE", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton cancel = new JButton("CANCEL");
		cancel.setBounds(0, 0, 25, 20);
		cancel.setForeground(Color.white);
		cancel.setBackground(new Color(119,171,89));

		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		buttonsPanel.add(show);
		buttonsPanel.add(cancel);

		return buttonsPanel;
	}

	public static void main(String[] args){
		FormShowService formShowService = new FormShowService();
		formShowService.setVisible(true);
	}
}
