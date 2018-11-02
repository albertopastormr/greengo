package presentation.command.rental;

import business.rental.TRental;
import business.rental.factory.ASRentalFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class UpdateRental implements Command {

	@Override
	public LightContext execute(LightContext in) {
		Integer ret = ASRentalFactory.getInstance().generateASRental().update((TRental)in.getData());
		return new LightContext(Event.UPDATE_RENTAL, ret);
	}
}
