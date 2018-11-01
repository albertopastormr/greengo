package presentation.client;

import presentation.PanelTabs;
import presentation.util.TableModel;
import presentation.util.TablePanel;

import javax.swing.*;
import java.awt.*;

public class ClientPanel extends JPanel {

	private final String[] columnId = {"Id", "Id card number", "Rentals number", "Active"};
	private TableModel model;

	public ClientPanel(PanelTabs panelTabs) {
		setLayout(new BorderLayout());
		add(new ClientToolbar(panelTabs), BorderLayout.NORTH);
		model = new ClientTableModel(columnId);
		add(new TablePanel<>(model), BorderLayout.CENTER);
	}

	public TableModel getModel() {
		return model;
	}
}
