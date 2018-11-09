package presentation.command.vehicle;

import business.ASException;
import business.vehicle.TVehicle;
import business.vehicle.factory.ASVehicleFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class DropVehicle implements Command {
	@Override
	public LightContext execute(LightContext in) throws ASException {
		Integer ret = ASVehicleFactory.getInstance().generateASVehicle().drop(((TVehicle)in.getData()).getId());
		return new LightContext(Event.DROP_VEHICLE, ret);
	}
}
