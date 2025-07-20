package br.com.one.forum_hub.reposity;

import br.com.one.forum_hub.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReposityUser extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Page<User> findByActiveTrue(Pageable pageable);
}
