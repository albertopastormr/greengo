package business.contract.factory;

import business.contract.as.ASContract;
import business.contract.factory.imp.ASContractFactoryImp;

public abstract class ASContractFactory {
    private static ASContractFactoryImp instance;

    public static ASContractFactory getInstance() {
        if (instance == null)
            instance = new ASContractFactoryImp();
        return instance;
    }

    public abstract ASContract generateASContract();
}
