package presentation.command.contract;

import business.contract.TContract;
import business.contract.factory.ASContractFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class ShowContract implements Command {
	@Override
	public LightContext execute(LightContext in) {
		TContract ret = ASContractFactory.getInstance().generateASContract().show((Integer)in.getData());
		return new LightContext(Event.SHOW_CONTRACT, ret);
	}
}
