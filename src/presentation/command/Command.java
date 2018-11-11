package presentation.command;

import business.ASException;
import business.IncorrectInputException;
import integration.DAOException;
import presentation.controller.LightContext;

public interface Command {
	LightContext execute(LightContext in) throws ASException, DAOException, IncorrectInputException;
}
