package presentation.command.employee;

import business.ASException;
import business.employee.TEmployee;
import business.employee.as.ASEmployee;
import business.employee.factory.ASEmployeeFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class CreateEmployee implements Command {
	@Override
	public LightContext execute(LightContext in) throws ASException, DAOException {
		Integer ret = ASEmployeeFactory.getInstance().generateASEmployee().create((TEmployee)in.getData());
		return new LightContext(Event.CREATE_EMPLOYEE, ret);
	}
}
