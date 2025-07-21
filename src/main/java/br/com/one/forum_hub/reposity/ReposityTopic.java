package br.com.one.forum_hub.reposity;

import br.com.one.forum_hub.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReposityTopic extends JpaRepository<Topic, Long> {
    boolean existsByTitle(String title);

    boolean existsByMessage(String message);

    Page<Topic> findAllByCourseId(Long id, Pageable pageable);

    Page<Topic> findAllByUserTId(Long id, Pageable pageable);
}
