package presentation.command.city;

import business.city.TCity;
import business.city.factory.ASCityFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ShowAllCity implements Command {

	@Override
	public LightContext execute(LightContext in) {
		Collection<TCity> ret = ASCityFactory.getInstance().generateASCity().showAll();
		return new LightContext(Event.SHOWALL_CITY, ret);
	}
}
