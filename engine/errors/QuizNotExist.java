package engine.errors;

public class QuizNotExist extends RuntimeException{

    public QuizNotExist(String message) {
        super(message);
    }
}
