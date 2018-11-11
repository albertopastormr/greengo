package presentation.command.client;

import business.ASException;
import business.IncorrectInputException;
import business.client.TClient;
import business.client.factory.ASClientFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class CreateClient implements Command {
	@Override
	public LightContext execute(LightContext in) throws ASException, IncorrectInputException {
		Integer ret = ASClientFactory.getInstance().generateASClient().create((TClient)in.getData());
		return new LightContext(Event.CREATE_CLIENT, ret);
	}
}
