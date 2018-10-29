package presentation.controller.imp;

import presentation.UI;
import presentation.controller.LightContext;
import presentation.controller.ViewDispatcher;

public class ViewDispatcherImp extends ViewDispatcher {
	@Override
	public void execute(LightContext context) {
		UI.getInstance().update(context);
	}
}
