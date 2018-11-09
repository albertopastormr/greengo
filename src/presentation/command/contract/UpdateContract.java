package presentation.command.contract;

import business.ASException;
import business.contract.TContract;
import business.contract.factory.ASContractFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class UpdateContract implements Command {
	@Override
	public LightContext execute(LightContext in)  throws ASException {
		Integer ret = ASContractFactory.getInstance().generateASContract().update((TContract)in.getData());
		return new LightContext(Event.UPDATE_CONTRACT, ret);
	}
}
