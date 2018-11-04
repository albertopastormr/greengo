package presentation.command.client;

import business.ASException;
import business.client.TClient;
import business.client.factory.ASClientFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

import java.util.Collection;

public class ShowAllClient implements Command {
	@Override
	public LightContext execute(LightContext in)  throws ASException, DAOException {
		Collection<TClient> ret = ASClientFactory.getInstance().generateASClient().showAll();
		return new LightContext(Event.SHOWALL_CLIENT, ret);
	}
}
