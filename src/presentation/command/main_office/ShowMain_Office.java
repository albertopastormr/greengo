package presentation.command.main_office;

import business.mainoffice.TMainOffice;
import business.mainoffice.factory.ASMain_OfficeFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class ShowMain_Office implements Command {
	@Override
	public LightContext execute(LightContext in) {
		TMainOffice ret = ASMain_OfficeFactory.getInstance().generateASMain_Office().show(((TMainOffice)in.getData()).getId());
		return  new LightContext(Event.SHOW_MAIN_OFFICE, ret);
	}
}
