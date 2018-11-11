package presentation.command.vehicle;

import business.ASException;
import business.IncorrectInputException;
import business.vehicle.TVehicle;
import business.vehicle.factory.ASVehicleFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class UpdateVehicle implements Command {

	@Override
	public LightContext execute(LightContext in) throws ASException, IncorrectInputException {
		Integer ret = ASVehicleFactory.getInstance().generateASVehicle().update((TVehicle)in.getData());
		return new LightContext(Event.UPDATE_VEHICLE, ret);
	}
}
