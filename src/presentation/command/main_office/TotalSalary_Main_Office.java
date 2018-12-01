package presentation.command.main_office;

import business.ASException;
import business.IncorrectInputException;
import business.employee.TEmployee;
import business.mainoffice.factory.ASMainOfficeFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class TotalSalary_Main_Office implements Command {
	@Override
<<<<<<< HEAD
	public LightContext execute(LightContext in)  throws ASException {
		Float ret = ASMainOfficeFactory.getInstance().generateASMain_Office().showSalary(((TEmployee)in.getData()).getId());
=======
	public LightContext execute(LightContext in) throws ASException, IncorrectInputException {
		Float ret = ASMain_OfficeFactory.getInstance().generateASMain_Office().showSalary(((TMainOffice)in.getData()).getId());
>>>>>>> c872c5b46c12723453f4ea2e2ede5fa4828fda17
		return new LightContext(Event.TOTAL_SALARY_MAIN_OFFICE, ret);
	}
}
