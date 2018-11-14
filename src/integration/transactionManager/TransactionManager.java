package integration.transactionManager;

import integration.transaction.Transaction;
import integration.TransactionException;
import integration.transactionManager.imp.TransactionManagerImp;

public abstract class TransactionManager {
    private static TransactionManager instance;

    public  static TransactionManager getInstance() {
        if (instance == null) {
            instance = new TransactionManagerImp();
        }

        return instance;
    }

    public abstract Transaction createTransaction() throws TransactionException;
    public abstract void removeTransaction();
    public abstract Transaction getTransaction();
}
