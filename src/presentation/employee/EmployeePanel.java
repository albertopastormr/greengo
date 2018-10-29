package presentation.employee;

import presentation.PanelTabs;
import presentation.UI;
import presentation.client.ClientTableModel;
import presentation.util.TableModel;
import presentation.util.TablePanel;

import javax.swing.*;
import java.awt.*;

public class EmployeePanel extends JPanel {

	private final String[] columnId = {"Id", "Id card number", "Salary", "Active", "Id Main Office", "Type"};
	private TableModel model;

	public EmployeePanel(PanelTabs panelTabs) {
		setLayout(new BorderLayout());
		//add(new ToolBarCerveza(panelTabs), BorderLayout.NORTH);
		model = new ClientTableModel(columnId);
		add(new TablePanel<>(model), BorderLayout.CENTER);
	}

	public TableModel getModel() {
		return model;
	}
}
