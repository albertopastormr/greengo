package presentation.contract;

import business.contract.TContract;
import presentation.util.TableModel;

public class ContractTableModel extends TableModel<TContract> {

	public ContractTableModel(String[] columnIds) {
		super(columnIds);
	}

	@Override
	public Object getValueAt(int indiceFil, int indiceCol) {
		Object s = null;
		switch (indiceCol) {
			case 0:
<<<<<<< HEAD
				s = list.get(indiceFil).getIdMainOffice();
				break;
			case 1:
=======
>>>>>>> 51e69a3f83b6aaa9cd11a65524429b04e446dd72
				s = list.get(indiceFil).getServiceLevel();
				break;
			case 1:
				s = list.get(indiceFil).getIdMainOffice();
				break;
			case 2:
				s = list.get(indiceFil).getIdService();
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
