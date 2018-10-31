package presentation.command.employee;

import presentation.command.Command;
import presentation.controller.LightContext;

public class SwitchToEmployee implements Command {
	@Override
	public LightContext execute(LightContext in) {
		return in;
	}
}
