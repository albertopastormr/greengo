package presentation.command.rental;

import business.ASException;
import business.rental.TRental;
import business.rental.factory.ASRentalFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class UpdateRental implements Command {

	@Override
	public LightContext execute(LightContext in) throws ASException {
		Integer ret = ASRentalFactory.getInstance().generateASRental().update((TRental)in.getData());
		return new LightContext(Event.UPDATE_RENTAL, ret);
	}
}
