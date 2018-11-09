package presentation.command.city;

import business.ASException;
import business.city.TCity;
import business.city.factory.ASCityFactory;
import business.client.TClient;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

import java.util.Collection;

public class ShowClientsByCity implements Command {
	@Override
	public LightContext execute(LightContext in)  throws ASException {
		Collection<TClient> ret = ASCityFactory.getInstance().generateASCity().showClientsByCity(((TCity)in.getData()).getId());
		return new LightContext(Event.SHOW_CLIENTS_FROM_CITY, ret);
	}
}
