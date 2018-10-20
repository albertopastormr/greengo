package business.employee.factory.imp;

import business.employee.as.ASEmployee;
import business.employee.as.imp.ASEmployeeImp;
import business.employee.factory.ASEmployeeFactory;

public class ASEmployeeFactoryImp extends ASEmployeeFactory {

    @Override
    public ASEmployee generateASEmployee() {
        return new ASEmployeeImp();
    }
}
