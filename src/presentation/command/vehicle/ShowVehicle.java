package presentation.command.vehicle;

import business.ASException;
import business.IncorrectInputException;
import business.vehicle.TVehicle;
import business.vehicle.TVehicleDetails;
import business.vehicle.as.ASVehicle;
import business.vehicle.factory.ASVehicleFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class ShowVehicle implements Command {

	@Override
	public LightContext execute(LightContext in) throws ASException, IncorrectInputException {
		TVehicleDetails ret = ASVehicleFactory.getInstance().generateASVehicle().show(((TVehicle)in.getData()).getId());
		return new LightContext(Event.SHOW_VEHICLE, ret);
	}
}
