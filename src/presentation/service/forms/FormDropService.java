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

public class FormDropService extends JDialog{

	private JTextField idText;

	public FormDropService() {
		setTitle("Drop Service");
		setResizable(false);
		Util.addEscapeListener(this);
		initGUI();
	}

	private void initGUI(){
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

		JButton drop = new JButton("DELETE");
		drop.setForeground(Color.white);
		drop.setBackground(new Color(119,171,89));

		drop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TService service = new TService();
				try {
					service.setId(Util.parseNoNegativeInt(idText.getText()));
					dispose();
					// TODO***needed CONTROLLER***
				}catch (Exception ex){
					JOptionPane.showMessageDialog(getRootPane(), ex.getMessage(), "ERROR DROP SERVICE", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton cancel = new JButton("CANCEL");
		cancel.setForeground(Color.white);
		cancel.setBackground(new Color(119,171,89));

		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		buttonsPanel.add(drop);
		buttonsPanel.add(cancel);

		return buttonsPanel;
	}

	public static void main(String[] args){
		FormDropService formDropService = new FormDropService();
		formDropService.setVisible(true);
	}
}
