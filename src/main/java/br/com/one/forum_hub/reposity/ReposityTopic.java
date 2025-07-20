package br.com.one.forum_hub.reposity;

import br.com.one.forum_hub.model.Topic;
import ch.qos.logback.core.testUtil.MockInitialContext;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReposityTopic extends JpaRepository<Topic, Long> {
    boolean existsByMessage(String message);
    boolean existsByTitle(String title);
}
