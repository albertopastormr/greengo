package presentation.command.client;

import business.client.TClient;
import business.client.factory.ASClientFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class ShowClient implements Command {
	@Override
	public LightContext execute(LightContext in) {
		TClient ret = ASClientFactory.getInstance().generateASClient().show(((TClient)in.getData()).getId());
		return new LightContext(Event.SHOW_CLIENT, ret);
	}
}
