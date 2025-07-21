package br.com.one.forum_hub.service;

import br.com.one.forum_hub.DTO.DataCoursePost;
import br.com.one.forum_hub.DTO.DataCourseUpdate;
import br.com.one.forum_hub.model.Course;
import br.com.one.forum_hub.reposity.ReposityCourse;
import br.com.one.forum_hub.service.exceptions.ExceptionData;
import br.com.one.forum_hub.service.exceptions.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private ReposityCourse reposityCourse;
    @Autowired

    List<Validate> validateCourse;
    public void checkPost(DataCoursePost data){
        validateCourse.forEach(v -> v.validatePostCourse(data));
    }
    public void checkUpdate(DataCourseUpdate data){
        validateCourse.forEach(v -> v.validateUpdateCourse(data));
    }

    public Course checkCourseExistsTopic(Long id) {
        Optional<Course> course = reposityCourse.findByIdAndActiveTrue(id);
        if (course.isPresent()){
            return course.get();
        } else throw new ExceptionData("Este curso não existe no sistema!");
    }
}
