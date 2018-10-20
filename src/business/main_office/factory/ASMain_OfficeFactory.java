package business.main_office.factory;


import business.main_office.as.ASMain_Office;
import business.main_office.factory.imp.ASMain_OfficeFactoryImp;

public abstract class ASMain_OfficeFactory {
    private static ASMain_OfficeFactory instance;

    public static ASMain_OfficeFactory getInstance(){
        if(instance == null)
            instance =  new ASMain_OfficeFactoryImp();
        return instance;
    }

    public abstract ASMain_Office generateASMain_Office();
}
