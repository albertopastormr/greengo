package presentation.util;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ViewHelpers {

	public static JComboBox selectActive() {

		JComboBox activeComboBox = new JComboBox();
		activeComboBox.addItem("true");
		activeComboBox.addItem("false");

		return activeComboBox;
	}

	public static JButton buttonsForms(String tittle){
		JButton button = new JButton(tittle);
		button.setForeground(Color.white);
		button.setBackground(new Color(0, 204, 102));

		return button;
	}

	public static JPanel createFieldPanel(int column){
		JPanel panel = new JPanel(new GridLayout(column, 2, 0, 7));
		Border border = panel.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		panel.setBorder(new CompoundBorder(border, margin));

		return  panel;
	}
}
