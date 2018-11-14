package integration.transactionManager.transactionFactory.imp;

import integration.transaction.Transaction;
import integration.transaction.imp.TransactionMariaDB;
import integration.TransactionException;
import integration.transactionManager.transactionFactory.TransactionFactory;

public class TransactionFactoryImp extends TransactionFactory {

    @Override
    public Transaction generateTransaction() throws TransactionException {
        return new TransactionMariaDB();
    }
}
