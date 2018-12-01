package business.mainoffice.factory;


import business.mainoffice.as.ASMainOffice;
import business.mainoffice.factory.imp.ASMainOfficeFactoryImp;

public abstract class ASMainOfficeFactory {
    private static ASMainOfficeFactory instance;

    public static synchronized ASMainOfficeFactory getInstance(){
        if(instance == null)
            instance =  new ASMainOfficeFactoryImp();
        return instance;
    }

    public abstract ASMainOffice generateASMainOffice();
}
