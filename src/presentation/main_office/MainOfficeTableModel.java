package presentation.main_office;

import business.mainoffice.TMainOffice;
import presentation.util.TableModel;

public class MainOfficeTableModel  extends TableModel<TMainOffice> {

	public MainOfficeTableModel(String[] columnIds) {
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
				s = list.get(indiceFil).getCity();
				break;
			case 2:
				s = list.get(indiceFil).getAddress();
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
