package engine.businesslayer;

import engine.model.Question;
import engine.persistence.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private final QuestionRepository questionRepository;


    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question save(Question questionToSave){
        return questionRepository.save(questionToSave);
    }
    public List<Question> findAllQuestions(){
        return (List<Question>) questionRepository.findAll();
    }
    public Optional<Question> findById(long id){
        return questionRepository.findById(id);
    }
}
