package br.com.one.forum_hub.model;

import br.com.one.forum_hub.DTO.DataCoursePost;
import br.com.one.forum_hub.DTO.DataCourseUpdate;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "courses")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(value = EnumType.STRING)
    private CategoryCourse category;
    @OneToMany(mappedBy = "course")
    private List<Topic> topics;
    private boolean active;

    public Course(DataCoursePost data) {
        this.title = data.title();
        this.category = data.category();
        this.active = true;
    }

    public void update(DataCourseUpdate data) {
        if (data.title() != null) this.title = data.title();
        if (data.category() != null) this.category = data.category();
    }

    public void delete() {
        this.active = false;
    }
}
