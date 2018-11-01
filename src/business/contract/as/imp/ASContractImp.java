package business.contract.as.imp;

import business.contract.TContract;
import business.contract.as.ASContract;

import java.util.Collection;

public class ASContractImp implements ASContract {

    /*This is related to JPA*/

    @Override
    public Integer create(TContract contract) {
        return null;
    }

    @Override
    public Integer drop(Integer id) {
        return null;
    }

    @Override
    public Integer update(TContract contract) {
        return null;
    }

    @Override
    public TContract show(Integer id) {
        return null;
    }

    @Override
    public Collection<TContract> showAll() {
        return null;
    }
}
