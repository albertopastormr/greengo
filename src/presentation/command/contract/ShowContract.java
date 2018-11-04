package presentation.command.contract;

import business.ASException;
import business.contract.TContract;
import business.contract.factory.ASContractFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class ShowContract implements Command {
	@Override
	public LightContext execute(LightContext in) throws ASException, DAOException {
		TContract ret = ASContractFactory.getInstance().generateASContract().show(((TContract)in.getData()).getId());
		return new LightContext(Event.SHOW_CONTRACT, ret);
	}
}
