package presentation.employee;

import business.employee.TEmployee;
import presentation.util.TableModel;

public class EmployeeTableModel extends TableModel<TEmployee> {

	public EmployeeTableModel(String[] columnIds) {
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
				s = list.get(indiceFil).getSalary();
				break;
			case 3:
				s = list.get(indiceFil).getActive();
				break;
			case 4:
				s = list.get(indiceFil).getIdMainOffice();
				break;
			case 5:
				s = list.get(indiceFil).getType();
				break;
			default:
				assert (false);
		}
		return s;
	}
}
