package business.mainoffice.factory.imp;

import business.mainoffice.as.ASMain_Office;
import business.mainoffice.as.imp.ASMain_OfficeImp;
import business.mainoffice.factory.ASMainOfficeFactory;

public class ASMainOfficeFactoryImp extends ASMainOfficeFactory {
    @Override


    public ASMain_Office generateASMain_Office() {
        return new ASMain_OfficeImp();
    }
}
