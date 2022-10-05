package engine.errors;

public class UserNotAuthorisedOrNotExist extends RuntimeException{
    public UserNotAuthorisedOrNotExist(String message) {
        super(message);
    }
}
