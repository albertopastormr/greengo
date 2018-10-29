package presentation;

import presentation.controller.LightContext;

public abstract class UI{

	private static UI instance = null;

	public static UI getInstance(){
		if(instance == null){
			instance = new UIimp();
		}

		return instance;
	}

	public abstract void execute();

	public abstract void update(LightContext lightContext);
}
