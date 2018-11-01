package presentation.command.contract;

import business.contract.TContract;
import business.contract.factory.ASContractFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class UpdateContract implements Command {
	@Override
	public LightContext execute(LightContext in) {
		Integer ret = ASContractFactory.getInstance().generateASContract().update((TContract)in.getData());
		return new LightContext(Event.UPDATE_CONTRACT, ret);
	}
}
