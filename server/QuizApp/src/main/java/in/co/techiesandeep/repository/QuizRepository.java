package in.co.techiesandeep.repository;

import in.co.techiesandeep.model.Quiz;
import in.co.techiesandeep.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    Page<Quiz> findByIsPublishedTrue(Pageable pageable);

    Page<Quiz> findByCreatedBy(User user, Pageable pageable);

    @Query("select q from Quiz q where q.name like %?1%")
    Page<Quiz> searchByName(String name, Pageable pageable);
}
