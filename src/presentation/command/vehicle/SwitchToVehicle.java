package presentation.command.vehicle;

import presentation.command.Command;
import presentation.controller.LightContext;

public class SwitchToVehicle implements Command {
	@Override
	public LightContext execute(LightContext in) {
		return in;
	}
}
