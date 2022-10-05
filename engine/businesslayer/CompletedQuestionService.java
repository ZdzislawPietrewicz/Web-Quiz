package engine.businesslayer;

import engine.model.CompletedQuestion;
import engine.model.CompletedQuestionDTO;
import engine.model.User;
import engine.persistence.CompletedQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CompletedQuestionService {
    @Autowired
    CompletedQuestionRepository completedQuestionRepository;

    public CompletedQuestion save(CompletedQuestion completedQuestion) {
        return completedQuestionRepository.save(completedQuestion);
    }

    public Page<CompletedQuestion> findAllCompletedQuestions(Integer page, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(page, pageSize, Sort.by("completedAt").descending());
        Page<CompletedQuestion> pagedResults = completedQuestionRepository.findAll(paging);
        return pagedResults;
    }

    public void deleteAllCompleted() {
        completedQuestionRepository.deleteAll();
    }

    public Page<CompletedQuestionDTO> findAllCompletedQuestionsByUser(User user, Integer page, Integer pageSize, String sortBy ) {
        Pageable paging = PageRequest.of(page, pageSize, Sort.by("completedAt").descending());
        return completedQuestionRepository.findCompletedQuestionByUser(user, paging).map(CompletedQuestionService::convertCompletionToDto);

    }
    private static CompletedQuestionDTO convertCompletionToDto(CompletedQuestion completedQuestion) {
        CompletedQuestionDTO completedQuestionDTO = new CompletedQuestionDTO();
        completedQuestionDTO.setQuizId(completedQuestion.getQuestion().getId());
        completedQuestionDTO.setCompletedAt(completedQuestion.getCompletedAt());
        return completedQuestionDTO;
    }


}
