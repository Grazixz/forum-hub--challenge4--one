package br.com.one.forum_hub.controller;

import br.com.one.forum_hub.DTO.*;
import br.com.one.forum_hub.model.CategoryCourse;
import br.com.one.forum_hub.model.Course;
import br.com.one.forum_hub.reposity.ReposityCourse;
import br.com.one.forum_hub.reposity.ReposityTopic;
import br.com.one.forum_hub.service.CourseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/curso")
@SecurityRequirement(name = "bearer-key")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private ReposityCourse reposityCourse;
    @Autowired
    private ReposityTopic reposityTopic;
    @PostMapping("/adicionar")
    @Transactional
    public ResponseEntity createCourse(@RequestBody @Valid DataCoursePost data, UriComponentsBuilder uriBuilder) {
        courseService.checkPost(data);
        Course course = new Course(data);
        reposityCourse.save(course);
        var uri = uriBuilder.path("/curso/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataCourseDetailed(course));
    }
    @GetMapping("/{id}")
    public ResponseEntity viewCourse(@PathVariable Long id) {
        Optional<Course> course = reposityCourse.findByIdAndActiveTrue(id);
        if (course.isPresent()){
            return ResponseEntity.ok().body(new DataCourseDetailed(course.get()));
        } else return ResponseEntity.notFound().build();
    }
    @GetMapping("/lista")
    public ResponseEntity<Page<DataCourseList>> courseList(@PageableDefault(size = 10)Pageable pageable) {
        var page = reposityCourse.findByActiveTrue(pageable)
                .map(DataCourseList::new);

        return ResponseEntity.ok(page);
    }
    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity updateCourse(@PathVariable Long id, @RequestBody DataCourseUpdate data) {
        Optional<Course> courseOptional = reposityCourse.findByIdAndActiveTrue(id);
        if (courseOptional.isPresent()){
            courseService.checkUpdate(data);
            Course course = courseOptional.get();
            course.update(data);
            return ResponseEntity.ok(new DataCourseDetailed(course));
        } else return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/deletar/{id}")
    @Transactional
    public ResponseEntity deleteCourse(@PathVariable Long id){
        Optional<Course> courseOptional = reposityCourse.findByIdAndActiveTrue(id);
        if (courseOptional.isPresent()){
            Course course = courseOptional.get();
            course.delete();
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();
    }
    @GetMapping("/categoria/{idCategory}")
    public ResponseEntity<Page<DataCourseList>> CategoryCourse(@PathVariable int idCategory, @PageableDefault(size = 10) Pageable pageable) {
        CategoryCourse categoryCourse = CategoryCourse.values()[idCategory];
        var page = reposityCourse.findAllByCategoryAndActiveTrue(categoryCourse, pageable)
                .map(DataCourseList::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/{id}/topicos")
    public ResponseEntity<Page<DataTopicList>> topicsCourses(@PathVariable Long id, @PageableDefault(size = 10, sort = {"creationDate"}) Pageable pageable) {
        Optional<Course> courseOptional = reposityCourse.findByIdAndActiveTrue(id);
        if (courseOptional.isPresent()) {
            var page = reposityTopic.findAllByCourseId(id, pageable)
                    .map(DataTopicList::new);
            return ResponseEntity.ok(page);
        } else return ResponseEntity.notFound().build();
    }
}
