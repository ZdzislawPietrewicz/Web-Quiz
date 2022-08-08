package engine.errors;

public class EmailNotCorrectOrPasswordToShort extends RuntimeException{
    public EmailNotCorrectOrPasswordToShort(String message) {
        super(message);
    }
}
