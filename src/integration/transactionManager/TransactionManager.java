package integration.transactionManager;

import integration.Transaction.Transaction;
import integration.transactionManager.imp.TransactionManagerImp;

public abstract class TransactionManager {
    private static TransactionManager instance;

    public  static TransactionManager getInstance() {
        if (instance == null) {
            instance = new TransactionManagerImp();
        }

        return instance;
    }

    public abstract Transaction createTransaction();
    public abstract void removeTransaction();
    public abstract Transaction getTransaction();
}
