package presentation.controller;

import presentation.controller.imp.ViewDispatcherImp;

public abstract class ViewDispatcher {
	private static ViewDispatcher instance = null;

	public static ViewDispatcher getInstance(){
		if(instance == null){
			instance = new ViewDispatcherImp();
		}
		return instance;
	}

	public abstract void execute(LightContext context);

}
