package presentation.command.city;

import business.city.TCity;
import business.city.as.ASCity;
import business.city.factory.ASCityFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class ShowCity implements Command {
	@Override
	public LightContext execute(LightContext in) {
		TCity ret = ASCityFactory.getInstance().generateASCity().show(((TCity)in.getData()).getId());
		return new LightContext(Event.SHOW_CITY, ret);
	}
}
