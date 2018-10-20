package business.employee.factory;

import business.employee.as.ASEmployee;
import business.employee.factory.imp.ASEmployeeFactoryImp;

public abstract class  ASEmployeeFactory {
    private static ASEmployeeFactory instance;

    public static ASEmployeeFactory getInstance (){
        if (instance == null)
            instance = new ASEmployeeFactoryImp();
        return instance;
    }

    public abstract ASEmployee generateASEmployee();
}
