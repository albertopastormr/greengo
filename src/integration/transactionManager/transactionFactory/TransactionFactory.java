package integration.transactionManager.transactionFactory;

import integration.transaction.Transaction;
import integration.TransactionException;
import integration.transactionManager.transactionFactory.imp.TransactionFactoryImp;

public abstract class TransactionFactory {
    private static TransactionFactory instance;

    public static TransactionFactory getInstance() {
        if(instance == null) {
            instance = new TransactionFactoryImp();
        }
        return instance;
    }

    public abstract Transaction generateTransaction() throws TransactionException;

}
