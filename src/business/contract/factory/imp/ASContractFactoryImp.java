package business.contract.factory.imp;

import business.contract.ASContract;
import business.contract.imp.ASContractImp;
import business.contract.factory.ASContractFactory;

public class ASContractFactoryImp extends ASContractFactory {

    @Override
    public ASContract generateASContract() {
        return new ASContractImp();
    }
}
