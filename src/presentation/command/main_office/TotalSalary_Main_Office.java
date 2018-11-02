package presentation.command.main_office;

import business.employee.TEmployee;
import business.mainoffice.TMainOffice;
import business.mainoffice.factory.ASMain_OfficeFactory;
import presentation.command.Command;
import presentation.controller.LightContext;

public class TotalSalary_Main_Office implements Command {
	@Override
	public LightContext execute(LightContext in) {
		//TODO revisar
		float ret = ASMain_OfficeFactory.getInstance().generateASMain_Office().showSalary(((TEmployee)in.getData()).getId());
		return null;
	}
}
