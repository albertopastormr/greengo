package presentation.service;

import presentation.PanelTabs;
import presentation.UI;
import presentation.client.ClientTableModel;
import presentation.util.TableModel;
import presentation.util.TablePanel;

import javax.swing.*;
import java.awt.*;

public class ServicePanel extends JPanel {

	private static final String[] columnId = {"Id", "Capacity", "Active", "Type", "Address", "Num Vehicles Attended"};
	private TableModel model;

	public ServicePanel(PanelTabs panelTabs) {
		setLayout(new BorderLayout());
		add(new ServiceToolbar(panelTabs), BorderLayout.NORTH);
		model = new ServiceTableModel(columnId);
		add(new TablePanel<>(model), BorderLayout.CENTER);
	}

	public TableModel getModel() {
		return model;
	}

	public static String[] getColumnId() {
		return columnId;
	}

}
