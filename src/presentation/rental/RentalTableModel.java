package presentation.rental;

import business.rental.TRental;
import presentation.util.TableModel;

public class RentalTableModel extends TableModel<TRental> {

	public RentalTableModel(String[] columnIds) {
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
				s = list.get(indiceFil).getIdVehicle();
				break;
			case 2:
				s = list.get(indiceFil).getIdClient();
				break;
			case 3:
				s = list.get(indiceFil).isActive();
				break;
			case 4:
				s = list.get(indiceFil).getNumKmRented();
				break;
			case 5:
				s = list.get(indiceFil).getDateFrom();
			break;
			case 6:
				s = list.get(indiceFil).getDateTo();
				break;
			default:
				assert (false);
		}
		return s;
	}
}
