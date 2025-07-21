package br.com.one.forum_hub.reposity;

import br.com.one.forum_hub.model.CategoryCourse;
import br.com.one.forum_hub.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReposityCourse extends JpaRepository<Course, Long> {
    Page<Course> findByActiveTrue(Pageable pageable);

    Optional<Course> findByIdAndActiveTrue(Long id);

    boolean existsByTitleAndActiveTrue(String title);

    Page<Course> findAllByCategoryAndActiveTrue(CategoryCourse categoryCourse, Pageable pageable);
}
