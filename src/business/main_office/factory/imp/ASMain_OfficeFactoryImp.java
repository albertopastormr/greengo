package business.main_office.factory.imp;

import business.main_office.as.ASMain_Office;
import business.main_office.as.imp.ASMain_OfficeImp;
import business.main_office.factory.ASMain_OfficeFactory;

public class ASMain_OfficeFactoryImp extends ASMain_OfficeFactory {
    @Override


    public ASMain_Office generateASMain_Office() {
        return new ASMain_OfficeImp();
    }
}
