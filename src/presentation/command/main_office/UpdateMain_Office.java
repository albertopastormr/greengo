package presentation.command.main_office;

import business.ASException;
import business.IncorrectInputException;
import business.mainoffice.TMainOffice;
import business.mainoffice.factory.ASMainOfficeFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class UpdateMain_Office implements Command {
	@Override
<<<<<<< HEAD
	public LightContext execute(LightContext in) throws ASException {
		Integer ret = ASMainOfficeFactory.getInstance().generateASMain_Office().update((TMainOffice) in.getData());
=======
	public LightContext execute(LightContext in) throws ASException, IncorrectInputException {
		Integer ret = ASMain_OfficeFactory.getInstance().generateASMain_Office().update((TMainOffice) in.getData());
>>>>>>> c872c5b46c12723453f4ea2e2ede5fa4828fda17
		return new LightContext(Event.UPDATE_MAIN_OFFICE, ret);
	}
}
