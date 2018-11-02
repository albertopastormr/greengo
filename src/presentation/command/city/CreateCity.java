package presentation.command.city;

import business.city.TCity;
import business.city.factory.ASCityFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class CreateCity implements Command {

	@Override
	public LightContext execute(LightContext in) {
		Integer ret = ASCityFactory.getInstance().generateASCity().create((TCity)in.getData());
		return new LightContext(Event.CREATE_CITY, ret);
	}
}
