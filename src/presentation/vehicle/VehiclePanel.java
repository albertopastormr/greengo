package presentation.vehicle;

import presentation.PanelTabs;
import presentation.client.ClientTableModel;
import presentation.util.TableModel;
import presentation.util.TablePanel;

import javax.swing.*;
import java.awt.*;

public class VehiclePanel extends JPanel {

	private final String[] columnId = {"Id", "Brand", "Estimated Duration", "Num km Travelled", "Occupied", "City", "Active", "Type"};
	private TableModel model;

	public VehiclePanel(PanelTabs panelTabs) {
		setLayout(new BorderLayout());
		//add(new ToolBarCerveza(panelTabs), BorderLayout.NORTH);
		model = new ClientTableModel(columnId);
		add(new TablePanel<>(model), BorderLayout.CENTER);
	}

	public TableModel getModel() {
		return model;
	}
}
