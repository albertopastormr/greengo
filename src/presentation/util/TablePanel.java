package presentation.util;

import javax.swing.*;
import java.awt.*;

public class TablePanel<T> extends JPanel {
	private TableModel<T> model;

	public TablePanel(TableModel<T> model) {
		setLayout(new GridLayout(1, 1));
		this.model = model;
		JTable tabla = new JTable(model);
		add(new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
	}
}
