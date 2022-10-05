package engine.persistence;

import engine.model.CompletedQuestion;
import engine.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompletedQuestionRepository extends PagingAndSortingRepository<CompletedQuestion, Long> {
    Page<CompletedQuestion> findCompletedQuestionByUser(User user, Pageable paging);

}
