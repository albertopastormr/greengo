package presentation.command.main_office;

import business.ASException;
import business.mainoffice.TMainOffice;
import business.mainoffice.factory.ASMain_OfficeFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class CreateMain_Office implements Command {
	@Override
	public LightContext execute(LightContext in) throws ASException, DAOException {
		Integer ret = ASMain_OfficeFactory.getInstance().generateASMain_Office().create((TMainOffice)in.getData());
		return new LightContext(Event.CREATE_MAIN_OFFICE, ret);
	}
}
