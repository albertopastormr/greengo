package presentation.controller;

import presentation.controller.imp.AppControllerImp;

public abstract class AppController {
    private static AppController instance;

    public static AppController getInstance(){
        if(instance == null){
            instance = new AppControllerImp();
        }
        return instance;
    }

    public abstract void execute(LightContext context);
}
