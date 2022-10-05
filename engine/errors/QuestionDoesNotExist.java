package engine.errors;

public class QuestionDoesNotExist extends RuntimeException{
    public QuestionDoesNotExist(String message) {
        super(message);
    }
}
