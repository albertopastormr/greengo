package presentation.command.client;

import business.ASException;
import business.client.TClient;
import business.client.factory.ASClientFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class ShowClient implements Command {
	@Override
	public LightContext execute(LightContext in) throws ASException {
		TClient ret = ASClientFactory.getInstance().generateASClient().show(((TClient)in.getData()).getId());
		return new LightContext(Event.SHOW_CLIENT, ret);
	}
}
