package engine.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(QuizNotExist.class)
    public ResponseEntity<CustomErrorMessage> handleQuizNotExist(QuizNotExist e, WebRequest webRequest) {
        CustomErrorMessage customErrorMessage = new CustomErrorMessage(e.getMessage());
        return new ResponseEntity<>(customErrorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<CustomErrorMessage> handleUserAlreadyExist(UserAlreadyExist e, WebRequest webRequest) {
        CustomErrorMessage customErrorMessage = new CustomErrorMessage(e.getMessage());
        return new ResponseEntity<>(customErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailNotCorrectOrPasswordToShort.class)
    public ResponseEntity<CustomErrorMessage> handleEmailAndPasswordInputException(EmailNotCorrectOrPasswordToShort e, WebRequest webRequest) {
        CustomErrorMessage customErrorMessage = new CustomErrorMessage(e.getMessage());
        return new ResponseEntity<>(customErrorMessage, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotAuthorisedOrNotExist.class)
    public ResponseEntity<CustomErrorMessage> handleUserNotAuthorisedOrNotExist(UserNotAuthorisedOrNotExist e, WebRequest webRequest){
        CustomErrorMessage customErrorMessage=new CustomErrorMessage(e.getMessage());
        return new ResponseEntity<>(customErrorMessage, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(QuestionDoesNotExist.class)
    public ResponseEntity<CustomErrorMessage> handleQuestionNotExist(QuestionDoesNotExist e, WebRequest webRequest){
        CustomErrorMessage customErrorMessage=new CustomErrorMessage(e.getMessage());
        return new ResponseEntity<>(customErrorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(QuestionDeleted.class)
    public ResponseEntity handleQuestionDeleted(QuestionDeleted e, WebRequest webRequest){
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}

