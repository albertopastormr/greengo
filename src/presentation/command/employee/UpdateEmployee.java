package presentation.command.employee;

import business.ASException;
import business.employee.TEmployee;
import business.employee.factory.ASEmployeeFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class UpdateEmployee implements Command {
	@Override
	public LightContext execute(LightContext in)  throws ASException, DAOException {
		Integer ret = ASEmployeeFactory.getInstance().generateASEmployee().update((TEmployee)in.getData());
		return new LightContext(Event.UPDATE_EMPLOYEE, ret);
	}
}
