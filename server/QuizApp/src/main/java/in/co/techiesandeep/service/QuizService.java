package in.co.techiesandeep.service;

import in.co.techiesandeep.model.Quiz;
import in.co.techiesandeep.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizService {

    Page<Quiz> findAllPublished(Pageable pageable);
    Page<Quiz> findAll(Pageable pageable);
    Quiz save(User user, Quiz quiz);

}
