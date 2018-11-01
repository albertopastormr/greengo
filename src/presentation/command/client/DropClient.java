package presentation.command.client;

import business.client.TClient;
import business.client.factory.ASClientFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class DropClient implements Command {
	@Override
	public LightContext execute(LightContext in) {
		Integer ret = ASClientFactory.getInstance().generateASClient().drop(((TClient)in.getData()).getId());
		return new LightContext(Event.DROP_CLIENT, ret);
	}
}
