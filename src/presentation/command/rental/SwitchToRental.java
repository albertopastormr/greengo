package presentation.command.rental;

import presentation.command.Command;
import presentation.controller.LightContext;

public class SwitchToRental implements Command {
	@Override
	public LightContext execute(LightContext in) {
		return in;
	}
}
