package presentation.command.main_office;

import business.ASException;
import business.IncorrectInputException;
import business.employee.TEmployee;
import business.mainoffice.TMainOffice;
import business.mainoffice.factory.ASMain_OfficeFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class TotalSalary_Main_Office implements Command {
	@Override
	public LightContext execute(LightContext in) throws ASException, IncorrectInputException {
		Float ret = ASMain_OfficeFactory.getInstance().generateASMain_Office().showSalary(((TMainOffice)in.getData()).getId());
		return new LightContext(Event.TOTAL_SALARY_MAIN_OFFICE, ret);
	}
}
