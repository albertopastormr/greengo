package presentation.command.service;

import business.ASException;
import business.service.TService;
import business.service.factory.ASServiceFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class CreateService implements Command {

	@Override
	public LightContext execute(LightContext in) throws ASException {
		Integer ret = ASServiceFactory.getInstance().generateASService().create((TService)in.getData());
		return new LightContext(Event.CREATE_SERVICE,ret);
	}
}
