package business;

public class ASException extends Exception {
    public ASException() {
        super();
    }

    public ASException(String s) {
        super(s);
    }

    public ASException(String s, Throwable throwable) {
        super(s, throwable);
    }
}

