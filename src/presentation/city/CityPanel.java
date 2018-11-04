package presentation.city;

import business.city.TCity;
import presentation.PanelTabs;
import presentation.client.ClientTableModel;
import presentation.util.TableModel;
import presentation.util.TablePanel;

import javax.swing.*;
import java.awt.*;

public class CityPanel extends JPanel {

	private final String[] columnId = {"Id", "Name", "Active"};
	private TableModel model;

	public CityPanel(PanelTabs panelTabs) {
		setLayout(new BorderLayout());
		add(new CityToolbar(panelTabs), BorderLayout.NORTH);
		model = new CityTableModel(columnId);
		add(new TablePanel<TCity>(model), BorderLayout.CENTER);
	}

	public TableModel getModel() {
		return model;
	}
}
