package presentation.command.service;

import presentation.command.Command;
import presentation.controller.LightContext;

public class SwitchToService implements Command {
	@Override
	public LightContext execute(LightContext in) {
		return in;
	}
}
