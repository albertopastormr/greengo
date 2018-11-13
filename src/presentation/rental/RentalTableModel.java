package presentation.rental;

import business.rental.TRental;
import business.rental.TRentalDetails;
import presentation.util.TableModel;

public class RentalTableModel extends TableModel<TRentalDetails> {

	public RentalTableModel(String[] columnIds) {
		super(columnIds);
	}

	@Override
	public Object getValueAt(int indiceFil, int indiceCol) {
		Object s = null;
		switch (indiceCol) {
			case 0:
				s = list.get(indiceFil).getRental().getId();
				break;
			case 1:
				s = list.get(indiceFil).getRental().getIdVehicle();
				break;
			case 2:
				s = list.get(indiceFil).getRental().getIdClient();
				break;
			case 3:
				s = list.get(indiceFil).getRental().isActive();
				break;
			case 4:
				s = list.get(indiceFil).getRental().getNumKmRented();
				break;
			case 5:
				s = list.get(indiceFil).getRental().getDateFrom();
			break;
			case 6:
				s = list.get(indiceFil).getRental().getDateTo();
				break;
			default:
				assert (false);
		}
		return s;
	}
}
