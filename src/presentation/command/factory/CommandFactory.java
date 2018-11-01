package presentation.command.factory;


import presentation.command.Command;
import presentation.command.factory.imp.CommandFactoryImp;
import presentation.controller.LightContext;

public abstract class CommandFactory {
	private static CommandFactory instance = null;

	public static CommandFactory getInstance(){
		if(instance == null){
			instance = new CommandFactoryImp();
		}
		return instance;
	}

	public abstract Command generateCommand(LightContext context);
}
