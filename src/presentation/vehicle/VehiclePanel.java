package presentation.vehicle;

import presentation.PanelTabs;
import presentation.client.ClientTableModel;
import presentation.util.TableModel;
import presentation.util.TablePanel;

import javax.swing.*;
import java.awt.*;

public class VehiclePanel extends JPanel {

	private static final String[] columnId = {"Id", "Brand", "Estimated Duration", "Num km Travelled", "Occupied",
			"City", "Active", "Type", "Identifier"};
	private TableModel model;

	public VehiclePanel(PanelTabs panelTabs) {
		setLayout(new BorderLayout());
		add(new VehicleToolbar(panelTabs), BorderLayout.NORTH);
		model = new VehicleTableModel(columnId);
		add(new TablePanel<>(model), BorderLayout.CENTER);
	}

	public TableModel getModel() {
		return model;
	}

	public static String[] getColumnId() {
		return columnId;
	}
}
