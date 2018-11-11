package business;

public class ASException extends Exception {
    private String s;

    public ASException() {
        super();
    }

    public ASException(String s) {
        super(s);
        this.s = s;
    }

    public ASException(String s, Throwable throwable) {
        super(s, throwable);
        this.s = s;
    }

    @Override
    public String toString(){
        return s;
    }
}

