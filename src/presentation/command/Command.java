package presentation.command;

import presentation.controller.LightContext;

public interface Command {
	LightContext execute(LightContext in);
}
