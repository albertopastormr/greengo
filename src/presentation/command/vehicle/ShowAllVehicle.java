package presentation.command.vehicle;

import business.vehicle.TVehicle;
import business.vehicle.factory.ASVehicleFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

import java.util.Collection;

public class ShowAllVehicle implements Command {
	@Override
	public LightContext execute(LightContext in) {
		Collection<TVehicle> ret = ASVehicleFactory.getInstance().generateASVehicle().showAll();
		return new LightContext(Event.SHOWALL_VEHICLE, ret);
	}
}
