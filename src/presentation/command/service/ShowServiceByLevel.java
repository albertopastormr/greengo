package presentation.command.service;

import business.service.TService;
import business.service.factory.ASServiceFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

import java.util.Collection;

public class ShowServiceByLevel implements Command {

	@Override
	public LightContext execute(LightContext in) {
		//TODO como pasar level del formulario por parametro
		//Collection<TService> ret = ASServiceFactory.getInstance().generateASService().showServicesFromLevel(((TService)in.getData()).getId());
		//  new LightContext(Event.SHOW_SERVICE_BY_LEVEL, ret);
		return null;
	}
}
