package integration;

public class DAOException extends Exception {
    private String s;
    public DAOException() {
        super();
    }

    public DAOException(String s) {
        super(s);
        this.s = s;
    }

    public DAOException(String s, Throwable throwable) {
        super(s, throwable);
        this.s = s;
    }

    @Override
    public String toString(){
        return s;
    }
}
