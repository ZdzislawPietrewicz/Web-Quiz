package engine.controller;

import engine.model.Quiz;
import engine.model.QuizAnswer;
import engine.model.QuizQuestion;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizController {
    private final List<Quiz> quizList = new ArrayList<>();
    private final List<QuizQuestion> quizQuestionList = new ArrayList<>();

    @PostMapping("/api/quizzes")
    private QuizQuestion createNewQuizQuestion(@RequestBody Quiz quiz) {
        QuizQuestion quizQuestion = new QuizQuestion(quizList.size() + 1, quiz.getTitle(),
                quiz.getText(), quiz.getOptions());
        quizList.add(quiz);
        quizQuestionList.add(quizQuestion);
        return quizQuestion;
    }

    @GetMapping("/api/quizzes")
    private List<QuizQuestion> getAllQuizzes() {
        return quizQuestionList;
    }

    @GetMapping("/api/quizzes/{id}")
    private QuizQuestion getQuizQuestion(@PathVariable int id) {
        return quizQuestionList.get(id - 1);
    }

    @GetMapping("/api/quiz")
    public void getQuizList() {

    }

    @PostMapping("/api/quiz")
    public QuizAnswer solveQuiz(@RequestParam int answer) {
        if (answer == 2) {
            return new QuizAnswer(true, "Congratulations, you're right!");
        } else
            return new QuizAnswer(false, "Wrong answer! Please, try again.");
    }
}
