package presentation.util;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public abstract class TableModel<T> extends DefaultTableModel {

	protected String[] columnIds;
	protected List<T> list;

	public TableModel(String[] columnIds) {
		list = null;
		this.columnIds = columnIds;
	}

	@Override
	public String getColumnName(int col) {
		return columnIds[col];
	}

	@Override
	public int getColumnCount() {
		return columnIds.length;
	}

	@Override
	public int getRowCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public boolean isCellEditable(int row, int colum) {
		return false;
	}

	public void setList(List<T> list) {
		this.list = list;
		fireTableStructureChanged();
	}
}
