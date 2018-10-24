package presentation.util;

import javax.swing.*;
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
		button.setBackground(new Color(119,171,89));

		return button;
	}
}
