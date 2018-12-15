package presentation.employee;

import business.employee.TEmployee;
import business.employee.TPermanentEmployee;
import business.employee.TTemporaryEmployee;
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
				s = list.get(indiceFil).isActive();
				break;
			case 4:
				s = list.get(indiceFil).getIdMainOffice();
				break;
			case 5:
				s = list.get(indiceFil).getType();
				break;
            case 6:
                if(list.get(indiceFil).getType().equals("Temporary")){
                    s = ((TTemporaryEmployee)list.get(indiceFil)).getNumWorkedHours();
                }
                else s = ((TPermanentEmployee)list.get(indiceFil)).getApportionment();

			default:
				assert (false);
		}
		return s;
	}
}
