package engine.controller;

import engine.businesslayer.QuestionService;
import engine.errors.QuizNotExist;
import engine.model.Answer;
import engine.model.Question;
import engine.model.QuizAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {

    @Autowired
    QuestionService questionService;


    @PostMapping("/quizzes")
    private Question createNewQuestion(@Valid @RequestBody Question question) {

        question.setAnswer(
                question.getAnswer() == null ? new int[]{} : question.getAnswer() // if no correct answer create new Integer array
        );
        return questionService.save(question);
    }

    @GetMapping("/quizzes")
    private List<Question> getAllQuizzes() {
        return questionService.findAllQuestions();
    }

    @GetMapping("/quizzes/{id}")
    private Question getQuestion(@PathVariable long id) {
        return questionService.findById(id).orElseThrow(() -> new QuizNotExist("Quiz doesn't exist"));
    }



     @PostMapping("/quizzes/{id}/solve")
      public QuizAnswer solveQuiz(@PathVariable int id, @RequestBody Answer answer) {
        Answer correctAnswer = new Answer(questionService.findById(id).get().getAnswer());
        if (correctAnswer.equals(answer)) {
            return new QuizAnswer(true, "Congratulations, you're right!");
        } else {
            return new QuizAnswer(false, "Wrong answer! Please, try again.");
        }
    }
}
