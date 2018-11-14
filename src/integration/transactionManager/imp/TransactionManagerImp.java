package integration.transactionManager.imp;

import integration.transaction.Transaction;
import integration.TransactionException;
import integration.transactionManager.TransactionManager;
import integration.transactionManager.transactionFactory.TransactionFactory;

import java.util.concurrent.ConcurrentHashMap;

public class TransactionManagerImp extends TransactionManager {
    private ConcurrentHashMap<Thread, Transaction> chm;

    public TransactionManagerImp(){
        this.chm = new ConcurrentHashMap<>();
    }

    @Override
    public Transaction createTransaction() throws TransactionException {
        Thread currentThread = Thread.currentThread();

        Transaction tr = this.chm.get(currentThread);

        if (tr == null) {
            tr = TransactionFactory.getInstance().generateTransaction();
            this.chm.put(currentThread, tr);
        }

        return tr;
    }

    @Override
    public void removeTransaction() {
        this.chm.remove(Thread.currentThread());
    }

    @Override
    public Transaction getTransaction() {
        return this.chm.get(Thread.currentThread());
    }
}
