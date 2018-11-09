package presentation.command.employee;

import business.ASException;
import business.employee.TEmployee;
import business.employee.factory.ASEmployeeFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class ShowEmployee implements Command {
	@Override
	public LightContext execute(LightContext in) throws ASException {
		TEmployee ret = ASEmployeeFactory.getInstance().generateASEmployee().show(((TEmployee)in.getData()).getId());
		return new LightContext(Event.SHOW_EMPLOYEE, ret);
	}
}
