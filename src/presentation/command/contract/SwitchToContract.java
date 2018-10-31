package presentation.command.contract;

import presentation.command.Command;
import presentation.controller.LightContext;

public class SwitchToContract implements Command {
	@Override
	public LightContext execute(LightContext in) {
		return in;
	}
}
