package presentation.command.city;

import business.city.TCity;
import business.city.factory.ASCityFactory;
import business.contract.TContract;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class DropCity implements Command {

	@Override
	public LightContext execute(LightContext in) {
		Integer ret = ASCityFactory.getInstance().generateASCity().drop(((TCity)in.getData()).getId());
		return new LightContext(Event.DROP_CITY, ret);
	}
}
