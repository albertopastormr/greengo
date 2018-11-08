package integration;

public class TransactionException extends Exception {

    public TransactionException() {
        super();
    }

    public TransactionException(String s) {
        super(s);
    }

    public TransactionException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
