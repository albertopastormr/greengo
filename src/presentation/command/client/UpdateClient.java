package presentation.command.client;

import business.ASException;
import business.client.TClient;
import business.client.factory.ASClientFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class UpdateClient implements Command {
	@Override
	public LightContext execute(LightContext in)  throws ASException, DAOException {
		Integer ret = ASClientFactory.getInstance().generateASClient().update(((TClient)in.getData()));
		return new LightContext(Event.UPDATE_CLIENT, ret);
	}
}
