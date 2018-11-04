package presentation.command.employee;

import business.ASException;
import business.employee.TEmployee;
import business.employee.factory.ASEmployeeFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

import java.util.Collection;

public class ShowAllEmployee implements Command {
	@Override
	public LightContext execute(LightContext in)  throws ASException, DAOException {
		Collection<TEmployee> ret = ASEmployeeFactory.getInstance().generateASEmployee().showAll();
		return new LightContext(Event.SHOWALL_EMPLOYEE, ret);

	}
}
