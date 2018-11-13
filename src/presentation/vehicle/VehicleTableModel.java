package presentation.vehicle;

import business.vehicle.TBicycleVehicle;
import business.vehicle.TCarVehicle;
import business.vehicle.TVehicle;
import business.vehicle.TVehicleDetails;
import presentation.util.TableModel;

public class VehicleTableModel extends TableModel<TVehicleDetails> {

	public VehicleTableModel(String[] columnIds) {
		super(columnIds);
	}

	@Override
	public Object getValueAt(int indiceFil, int indiceCol) {
		Object s = null;
		switch (indiceCol) {
			case 0:
				s = list.get(indiceFil).getVehicle().getId();
				break;
			case 1:
				s = list.get(indiceFil).getVehicle().getBrand();
				break;
			case 2:
				s = list.get(indiceFil).getVehicle().getEstimatedDuration();
				break;
			case 3:
				s = list.get(indiceFil).getVehicle().getNumKmTravelled();
				break;
			case 4:
				s = list.get(indiceFil).getVehicle().isOccupied();
				break;
			case 5:
				s = list.get(indiceFil).getVehicle().getCity();
				break;
			case 6:
				s = list.get(indiceFil).getVehicle().isActive();
				break;
			case 7:
				s = list.get(indiceFil).getVehicle().getType();
				break;
			case 8:
				if(list.get(indiceFil).getVehicle().getType().equals("Car")){
					s = ((TCarVehicle)list.get(indiceFil).getVehicle()).getPlate();
				}
				else s = ((TBicycleVehicle)list.get(indiceFil).getVehicle()).getSerialNumber();

			default:
				assert (false);
		}
		return s;
	}
}
