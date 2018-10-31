package presentation;

import presentation.controller.LightContext;

import javax.swing.*;

public abstract class UI extends JFrame{

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
