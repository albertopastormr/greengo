package presentation.command.rental;

import business.rental.TRental;
import business.rental.as.ASRental;
import business.rental.factory.ASRentalFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

import java.util.Collection;

public class ShowAllRental implements Command {

	@Override
	public LightContext execute(LightContext in) {
		Collection<TRental> ret = ASRentalFactory.getInstance().generateASRental().showAll();
		return new LightContext(Event.SHOWALL_RENTAL, ret);
	}
}
