package engine.businesslayer;

import engine.model.Question;
import engine.persistence.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private final QuestionRepository questionRepository;


    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question save(Question questionToSave) {
        return questionRepository.save(questionToSave);
    }

    public Page<Question> findAllQuestions(Integer page, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(page, pageSize, Sort.by(sortBy));
        Page<Question> pagedResult = questionRepository.findAll(paging);
            return pagedResult;
    }

    public Optional<Question> findById(long id) {
        return questionRepository.findById(id);
    }


    public void deleteQuestion(Question question) {
        questionRepository.delete(question);
    }
    public void deleteById(long id){
        questionRepository.deleteById(id);
    }
}
