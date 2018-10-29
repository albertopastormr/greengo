package presentation.rental;

import presentation.PanelTabs;
import presentation.UI;
import presentation.client.ClientTableModel;
import presentation.util.TableModel;
import presentation.util.TablePanel;

import javax.swing.*;
import java.awt.*;

public class RentalPanel extends JPanel {

	private final String[] columnId = {"Id", "Id vehicle", "Id Client", "Active", "Num km Rented", "Date From", "Date To"};
	private TableModel model;

	public RentalPanel(PanelTabs panelTabs) {
		setLayout(new BorderLayout());
		//add(new ToolBarCerveza(panelTabs), BorderLayout.NORTH);
		model = new ClientTableModel(columnId);
		add(new TablePanel<>(model), BorderLayout.CENTER);
	}

	public TableModel getModel() {
		return model;

	}
}
