package business;

public class IncorrectInputException extends Exception{
    public IncorrectInputException(){
        super();
    }

    public IncorrectInputException(String message){
        super(message);
    }

    public IncorrectInputException(String message, Throwable throwable){
        super(message,throwable);
    }
}
