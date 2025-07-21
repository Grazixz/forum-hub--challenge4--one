package br.com.one.forum_hub.DTO;

import br.com.one.forum_hub.model.CategoryCourse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataCoursePost(
        @NotBlank
        String title,
        @NotNull
        CategoryCourse category) {
}
