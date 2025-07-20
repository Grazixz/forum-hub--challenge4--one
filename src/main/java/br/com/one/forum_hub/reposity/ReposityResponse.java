package br.com.one.forum_hub.reposity;

import br.com.one.forum_hub.model.ResponseT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReposityResponse extends JpaRepository<ResponseT, Long> {
    Page<ResponseT> findAllByTopicId(Long id, Pageable pageable);
}
