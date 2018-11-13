package business;

public class IncorrectInputException extends Exception{
    private String s;

    public IncorrectInputException(){
        super();
    }

    public IncorrectInputException(String message){
        super(message);
        s = message;
    }

    public IncorrectInputException(String message, Throwable throwable){
        super(message,throwable);
        s = message;
    }

    @Override
    public String toString(){
        return s;
    }
}
