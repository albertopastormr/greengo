package business.mainoffice.factory.imp;

import business.mainoffice.as.ASMainOffice;
import business.mainoffice.as.imp.ASMainOfficeImp;
import business.mainoffice.factory.ASMainOfficeFactory;

public class ASMainOfficeFactoryImp extends ASMainOfficeFactory {
    @Override


    public ASMainOffice generateASMainOffice() {
        return new ASMainOfficeImp();
    }
}
