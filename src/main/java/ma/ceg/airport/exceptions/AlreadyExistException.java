package ma.ceg.airport;

public class AlreadyExistException extends RuntimeException{

    public AlreadyExistException(String message) {
        super(message);
    }
}
