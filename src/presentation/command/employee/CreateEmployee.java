package presentation.command.employee;

import business.employee.TEmployee;
import business.employee.as.ASEmployee;
import business.employee.factory.ASEmployeeFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class CreateEmployee implements Command {
	@Override
	public LightContext execute(LightContext in) {
		Integer ret = ASEmployeeFactory.getInstance().generateASEmployee().create((TEmployee)in.getData());
		return new LightContext(Event.CREATE_EMPLOYEE, ret);
	}
}
