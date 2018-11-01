package presentation.command.contract;

import business.contract.TContract;
import business.contract.factory.ASContractFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

import java.util.Collection;

public class ShowAllContract implements Command {
	@Override
	public LightContext execute(LightContext in) {
		Collection<TContract> ret = ASContractFactory.getInstance().generateASContract().showAll();
		return new LightContext(Event.SHOWALL_CONTRACT, ret);
	}
}
