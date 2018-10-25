package business.mainoffice.factory.imp;

import business.mainoffice.as.ASMain_Office;
import business.mainoffice.as.imp.ASMain_OfficeImp;
import business.mainoffice.factory.ASMain_OfficeFactory;

public class ASMain_OfficeFactoryImp extends ASMain_OfficeFactory {
    @Override


    public ASMain_Office generateASMain_Office() {
        return new ASMain_OfficeImp();
    }
}
