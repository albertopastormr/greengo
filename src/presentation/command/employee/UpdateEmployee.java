package presentation.command.employee;

import business.employee.TEmployee;
import business.employee.factory.ASEmployeeFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class UpdateEmployee implements Command {
	@Override
	public LightContext execute(LightContext in) {
		Integer ret = ASEmployeeFactory.getInstance().generateASEmployee().update((TEmployee)in.getData());
		return new LightContext(Event.UPDATE_EMPLOYEE, ret);
	}
}
