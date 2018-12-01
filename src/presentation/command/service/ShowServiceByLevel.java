package presentation.command.service;

import business.ASException;
import business.IncorrectInputException;
import business.service.TService;
import business.service.factory.ASServiceFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

import java.util.Collection;

public class ShowServiceByLevel implements Command {

	@Override
	public LightContext execute(LightContext in) throws ASException, IncorrectInputException {
		Collection<TService> ret = ASServiceFactory.getInstance().generateASService().showServicesFromLevel((Integer)in.getData());
		return new LightContext(Event.SHOW_SERVICE_BY_LEVEL, ret);
	}
}
