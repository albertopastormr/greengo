package presentation.command.city;

import business.ASException;
import business.city.TCity;
import business.city.factory.ASCityFactory;
import business.contract.TContract;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class DropCity implements Command {

	@Override
	public LightContext execute(LightContext in)  throws ASException {
		Integer ret = ASCityFactory.getInstance().generateASCity().drop(((TCity)in.getData()).getId());
		return new LightContext(Event.DROP_CITY, ret);
	}
}
