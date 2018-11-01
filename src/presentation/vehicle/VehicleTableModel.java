package presentation.vehicle;

import business.vehicle.TVehicle;
import presentation.util.TableModel;

public class VehicleTableModel extends TableModel<TVehicle> {

	public VehicleTableModel(String[] columnIds) {
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
				s = list.get(indiceFil).getBrand();
				break;
			case 2:
				s = list.get(indiceFil).getEstimatedDuration();
				break;
			case 3:
				s = list.get(indiceFil).getNumKmTravelled();
				break;
			case 4:
				s = list.get(indiceFil).isOccupied();
				break;
			case 5:
				s = list.get(indiceFil).getCity();
				break;
			case 6:
				s = list.get(indiceFil).isActive();
				break;
			case 7:
				s = list.get(indiceFil).getType();
				break;
			default:
				assert (false);
		}
		return s;
	}
}
