package in.co.techiesandeep.service;

import in.co.techiesandeep.model.Quiz;
import in.co.techiesandeep.model.User;
import in.co.techiesandeep.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Override
    public Page<Quiz> findAllPublished(Pageable pageable) {
        return quizRepository.findByIsPublishedTrue(pageable);
    }

    @Override
    public Page<Quiz> findAll(Pageable pageable) {
        return quizRepository.findAll(pageable);
    }

    @Override
    public Quiz save(User user, Quiz quiz) {
        quiz.setCreatedBy(user);
        return quizRepository.save(quiz);
    }
}
