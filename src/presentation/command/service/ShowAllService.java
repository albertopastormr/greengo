package presentation.command.service;

import business.ASException;
import business.service.TService;
import business.service.as.ASService;
import business.service.factory.ASServiceFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

import java.util.Collection;

public class ShowAllService implements Command {
	@Override
	public LightContext execute(LightContext in) throws ASException {
		Collection<TService> ret = ASServiceFactory.getInstance().generateASService().showAll();
		return new LightContext(Event.SHOWALL_SERVICE,ret);
	}
}
