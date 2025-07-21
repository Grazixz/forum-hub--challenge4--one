package br.com.one.forum_hub.model;

import br.com.one.forum_hub.DTO.DataTopicPost;
import br.com.one.forum_hub.DTO.DataTopicUpdate;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @Column(unique = true)
    private String message;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User userT;
    @ManyToOne
    @JoinColumn(name = "id_course")
    private Course course;
    @Column(name = "creation_Date")
    private LocalDateTime creationDate;
    @OneToMany(mappedBy = "topic")
    private List<ResponseT> responses;
    private boolean resolved;

    public Topic(DataTopicPost data, User user, Course course) {
        this.title = data.title();
        this.message = data.message();
        this.userT = user;
        this.course = course;
        this.creationDate = LocalDateTime.now();
        this.resolved = false;
    }

    public void update(DataTopicUpdate data, Course course) {
        if (data.title() != null) this.title = data.title();
        if (data.message() != null) this.message = data.message();
        if (data.idCourse() != null) this.course = course;
        if (data.resolved()) this.resolved = true;
    }
}
