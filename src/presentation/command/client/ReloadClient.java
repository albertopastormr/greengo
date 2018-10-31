package presentation.command.client;

import presentation.command.Command;
import presentation.controller.LightContext;

public class ReloadClient implements Command {
	@Override
	public LightContext execute(LightContext in) {
		return in;
	}
}
