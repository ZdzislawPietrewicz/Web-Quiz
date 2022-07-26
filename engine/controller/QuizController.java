package engine.controller;

import engine.errors.QuizNotExist;
import engine.model.Answer;
import engine.model.QuizAnswer;
import engine.model.QuizQuestion;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {
    private List<QuizQuestion> quizQuestionList = new ArrayList<>();
    int identifier = 1; // value for the first question

    @PostMapping("/quizzes")
    private QuizQuestion createNewQuizQuestion(@Valid @RequestBody QuizQuestion question) {
        QuizQuestion quizQuestion = new QuizQuestion(identifier, question.getTitle(),
                question.getText(), question.getOptions(), question.getAnswer());
        quizQuestionList.add(quizQuestion);
        identifier++;
        return quizQuestion;
    }

    @GetMapping("/quizzes")
    private List<QuizQuestion> getAllQuizzes() {
        return quizQuestionList;
    }

    @GetMapping("/quizzes/{id}")
    private QuizQuestion getQuizQuestion(@PathVariable int id) {
        if (id <= quizQuestionList.size()) {
            return quizQuestionList.get(id - 1);
        } else throw new QuizNotExist("Quiz doesn't exist");

    }

    @GetMapping("/quiz")
    public void getQuizList() {

    }

    @PostMapping("/quizzes/{id}/solve")
    public QuizAnswer solveQuiz(@PathVariable int id, @RequestBody Answer answer) {
        Answer correctAnswer = new Answer(quizQuestionList.get(id-1).getAnswer());
        if (correctAnswer.equals(answer)) {
            return new QuizAnswer(true, "Congratulations, you're right!");
        } else {
            return new QuizAnswer(false, "Wrong answer! Please, try again.");
        }
    }
}
