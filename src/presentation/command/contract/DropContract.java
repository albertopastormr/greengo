package presentation.command.contract;

import business.contract.TContract;
import business.contract.factory.ASContractFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class DropContract implements Command {
	@Override
	public LightContext execute(LightContext in) {
		Integer ret = ASContractFactory.getInstance().generateASContract().drop(((TContract)in.getData()).getId());
		return new LightContext(Event.CREATE_CONTRACT, ret);
	}
}
