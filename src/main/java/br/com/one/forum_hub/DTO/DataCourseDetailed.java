package br.com.one.forum_hub.DTO;

import br.com.one.forum_hub.model.CategoryCourse;
import br.com.one.forum_hub.model.Course;

public record DataCourseDetailed(
        Long id,
        String title,
        CategoryCourse category) {
    public DataCourseDetailed(Course course) {
        this(course.getId(), course.getTitle(), course.getCategory());
    }
}
