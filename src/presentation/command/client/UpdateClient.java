package presentation.command.client;

import business.client.TClient;
import business.client.factory.ASClientFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class UpdateClient implements Command {
	@Override
	public LightContext execute(LightContext in) {
		Integer ret = ASClientFactory.getInstance().generateASClient().update(((TClient)in.getData()));
		return new LightContext(Event.UPDATE_CLIENT, ret);
	}
}
