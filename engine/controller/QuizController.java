package engine.controller;

import engine.errors.QuestionDeleted;
import engine.errors.QuestionDoesNotExist;
import engine.model.CompletedQuestionDTO;
import engine.businesslayer.CompletedQuestionService;
import engine.businesslayer.QuestionService;
import engine.businesslayer.UserService;
import engine.errors.QuizNotExist;
import engine.errors.UserNotAuthorisedOrNotExist;
import engine.model.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class QuizController {

    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;
    @Autowired
    CompletedQuestionService completedQuestionService;


    @PostMapping("/quizzes")
    private Question createNewQuestion(@Valid @RequestBody Question question, @AuthenticationPrincipal UserDetails userDetails) {
        if (userService.findUserByEmail(userDetails.getUsername()).isPresent()) {
            question.setAnswer(
                    question.getAnswer() == null ? new int[]{} : question.getAnswer() // if no correct answer create new Integer array
            );
            question.setUser(new User(userDetails.getUsername(), userDetails.getPassword()));
            return questionService.save(question);
        } else throw new UserNotAuthorisedOrNotExist("Not authorised or not exist");
    }

    @GetMapping("/quizzes")
    private Page<Question> getAllQuizzes(@RequestParam(defaultValue = "0") Integer page,
                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                         @RequestParam(defaultValue = "id") String sortBy) {

        return questionService.findAllQuestions(page, pageSize, sortBy);
    }

    @GetMapping("/quizzes/{id}")
    private Question getQuestion(@PathVariable long id) {
        return questionService.findById(id).orElseThrow(() -> new QuizNotExist("Quiz doesn't exist"));
    }


    @PostMapping("/quizzes/{id}/solve")
    public QuizAnswer solveQuiz(@PathVariable int id, @RequestBody Answer answer, @AuthenticationPrincipal UserDetails userDetails) {
        Answer correctAnswer = new Answer(questionService.findById(id).get().getAnswer());
        if (correctAnswer.equals(answer)) {
            completedQuestionService.save(
                    new CompletedQuestion(questionService.findById(id).get(),
                            new User(userDetails.getUsername(), userDetails.getPassword()),
                            LocalDateTime.now()));
            return new QuizAnswer(true, "Congratulations, you're right!");
        } else {
            return new QuizAnswer(false, "Wrong answer! Please, try again.");
        }
    }

    @DeleteMapping("/quizzes/{id}")
    public void deleteQuiz(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<Question> questionToDelete = questionService.findById(id);

      questionToDelete.ifPresentOrElse((question) -> {
                  if (question.getUser().getEmail().equals(userDetails.getUsername())) {
                      questionService.deleteQuestion(question);
                      throw new QuestionDeleted();
                  } else throw new UserNotAuthorisedOrNotExist("Not authorized");
              },
              () -> {
                  throw new QuestionDoesNotExist("question not exist");
              }
      );
  }
    @GetMapping("/quizzes/completed")
    public Page<CompletedQuestionDTO> showCompletedQuestion(@AuthenticationPrincipal UserDetails userDetails,
                                                            @RequestParam(defaultValue = "0") Integer page,
                                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                                            @RequestParam(defaultValue = "id") String sortBy) {
        return completedQuestionService.findAllCompletedQuestionsByUser(new User(userDetails.getUsername(), userDetails.getPassword()), page, pageSize, sortBy);
    }

    @DeleteMapping("/quizzes/completed/del")
    public void deleteAllCompleted() {
        completedQuestionService.deleteAllCompleted();
    }

    @GetMapping("/quizzes/AllCompleted")
    public Page<CompletedQuestion> showAllCompletedQuestions(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                                             @RequestParam(defaultValue = "id") String sortBy) {
        return completedQuestionService.findAllCompletedQuestions(page, pageSize, sortBy);
    }
}

