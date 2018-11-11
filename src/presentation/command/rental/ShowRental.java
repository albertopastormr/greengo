package presentation.command.rental;

import business.ASException;
import business.IncorrectInputException;
import business.rental.TRental;
import business.rental.TRentalDetails;
import business.rental.factory.ASRentalFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class ShowRental implements Command {

	@Override
	public LightContext execute(LightContext in) throws ASException, IncorrectInputException {
		TRentalDetails ret = ASRentalFactory.getInstance().generateASRental().show(((TRental)in.getData()).getId());
		return new LightContext(Event.SHOW_RENTAL, ret);
	}
}
