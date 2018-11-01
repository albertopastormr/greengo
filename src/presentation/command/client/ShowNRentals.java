package presentation.command.client;

import business.client.TClient;
import business.client.factory.ASClientFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

import java.util.Collection;

public class ShowNRentals implements Command {
	@Override
	public LightContext execute(LightContext in) {
		Collection<TClient> ret = ASClientFactory.getInstance().generateASClient().showAllWithMoreThanNRentals((Integer)in.getData());
		return new LightContext(Event.SHOW_CLIENTS_N_RENTAL_CLIENT, ret);
	}
}
