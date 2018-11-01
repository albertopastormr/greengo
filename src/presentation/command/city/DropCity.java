package presentation.command.city;

import business.city.TCity;
import business.city.factory.ASCityFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class DropCity implements Command {
	@Override
	public LightContext execute(LightContext in) {
		Integer ret = ASCityFactory.getInstance().generateASCity().drop((Integer)in.getData());
		return new LightContext(Event.DROP_CITY, ret);
	}
}
