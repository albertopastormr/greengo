package integration.transactionManager.transactionFactory.imp;

import integration.Transaction.Transaction;
import integration.Transaction.imp.TransactionMariaDB;
import integration.TransactionException;
import integration.transactionManager.transactionFactory.TransactionFactory;

public class TransactionFactoryImp extends TransactionFactory {

    @Override
    public Transaction generateTransaction() throws TransactionException {
        return new TransactionMariaDB();
    }
}
