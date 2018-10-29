package presentation.client;

import business.client.TClient;
import presentation.util.TableModel;

public class ClientTableModel extends TableModel<TClient>{

	public ClientTableModel(String[] columnIds) {
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
				s = list.get(indiceFil).getIdCardNumber();
				break;
			case 2:
				s = list.get(indiceFil).getNumRentals();
				break;
			case 3:
				s = list.get(indiceFil).isActive();
				break;
			default:
				assert (false);
		}
		return s;
	}
}
