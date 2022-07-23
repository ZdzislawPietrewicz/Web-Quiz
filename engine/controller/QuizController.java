package engine.controller;

import engine.model.Quiz;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

}
