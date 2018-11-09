package presentation.command.main_office;

import business.ASException;
import business.mainoffice.TMainOffice;
import business.mainoffice.factory.ASMain_OfficeFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

import java.util.Collection;

public class ShowAllMain_Office implements Command {
	@Override
	public LightContext execute(LightContext in) throws ASException {
		Collection<TMainOffice> ret = ASMain_OfficeFactory.getInstance().generateASMain_Office().showAll();
		return new LightContext(Event.SHOWALL_MAIN_OFFICE, ret);
	}
}
