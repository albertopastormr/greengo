package presentation.service;

import business.service.TService;
import presentation.util.TableModel;

public class ServiceTableModel extends TableModel<TService> {

	public ServiceTableModel(String[] columnIds) {
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
				s = list.get(indiceFil).getCapacity();
				break;
			case 2:
				s = list.get(indiceFil).isActive();
				break;
			case 3:
				s = list.get(indiceFil).getType();
				break;
			case 4:
				s = list.get(indiceFil).getAddress();
				break;
			case 5:
				s = list.get(indiceFil).getNumVehiclesAttended();
				break;
			default:
				assert (false);
		}
		return s;
	}
}
