package integration.transactionManager.transactionFactory.imp;

import integration.transactionManager.Transaction;
import integration.transactionManager.imp.TransactionMariaDB;
import integration.transactionManager.transactionFactory.TransactionFactory;

public class TransactionFactoryImp extends TransactionFactory {

    @Override
    public Transaction generateTransaction() {
        return new TransactionMariaDB();
    }
}
