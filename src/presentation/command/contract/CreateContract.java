package presentation.command.contract;

import business.ASException;
import business.contract.TContract;
import business.contract.as.ASContract;
import business.contract.factory.ASContractFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class CreateContract implements Command {
	@Override
	public LightContext execute(LightContext in) throws ASException, DAOException {
		Integer ret = ASContractFactory.getInstance().generateASContract().create((TContract)in.getData());
		return new LightContext(Event.CREATE_CONTRACT, ret);
	}
}
