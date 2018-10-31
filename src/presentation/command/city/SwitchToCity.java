package presentation.command.city;

import presentation.command.Command;
import presentation.controller.LightContext;

public class SwitchToCity implements Command {
	@Override
	public LightContext execute(LightContext in) {
		return in;
	}
}
