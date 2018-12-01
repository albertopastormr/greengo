package presentation.mainOffice;

import presentation.PanelTabs;
import presentation.util.TableModel;
import presentation.util.TablePanel;

import javax.swing.*;
import java.awt.*;

public class MainOfficePanel extends JPanel {

	private final String[] columnId = {"Id", "City", "Address", "Active"};
	private TableModel model;

	public MainOfficePanel(PanelTabs panelTabs) {
		setLayout(new BorderLayout());
		add(new MainOfficeToolbar(panelTabs), BorderLayout.NORTH);
		model = new MainOfficeTableModel(columnId);
		add(new TablePanel<>(model), BorderLayout.CENTER);
	}

	public TableModel getModel() {
		return model;
	}
}
