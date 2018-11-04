package presentation.command.rental;

import business.ASException;
import business.rental.TRentalDetails;
import business.rental.factory.ASRentalFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

import java.util.Collection;

public class ShowAllRental implements Command {

	@Override
	public LightContext execute(LightContext in) throws ASException, DAOException {
		Collection<TRentalDetails> ret = ASRentalFactory.getInstance().generateASRental().showAll();
		return new LightContext(Event.SHOWALL_RENTAL, ret);
	}
}
