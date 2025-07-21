package br.com.one.forum_hub.DTO;

import br.com.one.forum_hub.model.CategoryCourse;
import br.com.one.forum_hub.model.Course;

public record DataCourseUpdate(
        String title,
        CategoryCourse category) {
    public DataCourseUpdate(Course course) {
        this(course.getTitle(), course.getCategory());
    }
}
