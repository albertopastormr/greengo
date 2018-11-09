package presentation.command.service;

import business.ASException;
import business.service.TService;
import business.service.factory.ASServiceFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class DropService implements Command {
	@Override
	public LightContext execute(LightContext in) throws ASException {
		Integer ret = ASServiceFactory.getInstance().generateASService().drop(((TService)in.getData()).getId());
		return new LightContext(Event.DROP_SERVICE, ret);
	}
}
