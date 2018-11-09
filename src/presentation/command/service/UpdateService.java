package presentation.command.service;

import business.ASException;
import business.service.TService;
import business.service.factory.ASServiceFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class UpdateService implements Command {
	@Override
	public LightContext execute(LightContext in) throws ASException {
		Integer ret = ASServiceFactory.getInstance().generateASService().update((TService)in.getData());
		return new LightContext(Event.UPDATE_SERVICE, ret);
	}
}
