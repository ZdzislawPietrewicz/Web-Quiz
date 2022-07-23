package engine.controller;

import engine.model.Quiz;
import engine.model.QuizAnswer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuizController {
    private final List<Quiz> quizList = List.of(
            new Quiz("The Java Logo", "What is depicted on the Java logo?",
                    new String[]{"Robot", "The Leaf", "Cup of coffee", "Bug"}));

    @GetMapping("/api/quiz")
    public List<Quiz> getQuizList() {
        return quizList;
    }

    @PostMapping("/api/quiz")
    public QuizAnswer solveQuiz(@RequestParam int answer) {
        if(answer==2){
            return new QuizAnswer(true, "Congratulations, you're right!");
        } else
            return new QuizAnswer(false,"Wrong answer! Please, try again.");
    }
}
