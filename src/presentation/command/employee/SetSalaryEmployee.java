package presentation.command.employee;

import business.ASException;
import business.employee.TEmployee;
import business.employee.factory.ASEmployeeFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class SetSalaryEmployee implements Command {
	@Override
	public LightContext execute(LightContext in)  throws ASException, DAOException {
		Integer ret = ASEmployeeFactory.getInstance().generateASEmployee().setSalary(((TEmployee)in.getData()).getId(),
				((TEmployee)in.getData()).getSalary());
		return new LightContext(Event.SET_SALARY_EMPLOYEE, ret);
	}
}
