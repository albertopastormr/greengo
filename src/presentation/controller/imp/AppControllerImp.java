package presentation.controller.imp;

import presentation.command.Command;
import presentation.command.factory.CommandFactory;
import presentation.controller.AppController;
import presentation.controller.Event;
import presentation.controller.LightContext;
import presentation.controller.ViewDispatcher;

public class AppControllerImp extends AppController {

    @Override
    public void execute(LightContext context){
        try {
            Command command = CommandFactory.getInstance().generateCommand(context);
            LightContext response = command.execute(context);
            ViewDispatcher.getInstance().execute(response);
        }
        catch(Exception e){
            ViewDispatcher.getInstance().execute(new LightContext(Event.ERROR, e));
        }
    }

}
