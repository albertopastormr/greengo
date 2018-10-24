package presentation.client.forms;

import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormCreateClient extends JDialog{

	private JTextField dniText;
	private JComboBox activeComboBox;

	public FormCreateClient() {
		setTitle("Create client");
		setResizable(false);

		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

		Util.addEscapeListener(this);
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.white);
		mainPanel.add(fieldsPanel());
		mainPanel.add(buttonsPanel());

		add(mainPanel);
		pack();
		setLocationRelativeTo(null);
	}

	private JPanel fieldsPanel() {
		JPanel ret = new JPanel(new GridLayout(3,2,0,7));
		Border border = ret.getBorder();
		Border margin = new EmptyBorder(10,10,10,10);
		ret.setBorder(new CompoundBorder(border, margin));

		//DNI
		JLabel typeLabel = new JLabel("Type");
		ret.add(typeLabel);

		dniText = new JTextField(10);
		ret.add(dniText);

		//Active
		JLabel activeLabel = new JLabel("Active");
		ret.add(activeLabel);

		activeComboBox = ViewHelpers.selectActive();
		ret.add(activeComboBox);

		return  ret;
	}


	private JPanel buttonsPanel(){

		return null;
	}
}
