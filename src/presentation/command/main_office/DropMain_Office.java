package presentation.command.main_office;

import business.mainoffice.factory.ASMain_OfficeFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class DropMain_Office implements Command {
	@Override
	public LightContext execute(LightContext in) {
		Integer ret = ASMain_OfficeFactory.getInstance().generateASMain_Office().drop((Integer)in.getData());
		return new LightContext(Event.DROP_MAIN_OFFICE, ret);
	}
}
