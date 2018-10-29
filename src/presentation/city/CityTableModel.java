package presentation.city;

import business.city.TCity;
import presentation.util.TableModel;

public class CityTableModel extends TableModel<TCity> {

	public CityTableModel(String[] columnIds) {
		super(columnIds);
	}

	@Override
	public Object getValueAt(int indiceFil, int indiceCol) {
		Object s = null;
		switch (indiceCol) {
			case 0:
				s = list.get(indiceFil).getId();
				break;
			case 1:
				s = list.get(indiceFil).getName();
				break;
			case 2:
				s = list.get(indiceFil).isActive();
				break;
			default:
				assert (false);
		}
		return s;
	}
}
