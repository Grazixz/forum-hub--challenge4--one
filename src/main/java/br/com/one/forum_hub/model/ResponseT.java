package br.com.one.forum_hub.model;

import br.com.one.forum_hub.DTO.DataResponsePost;
import br.com.one.forum_hub.DTO.DataResponseUpdate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "responses")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ResponseT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User userR;
    @ManyToOne
    @JoinColumn(name = "id_topic")
    private Topic topic;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public ResponseT(DataResponsePost data, User user, Topic topic) {
        this.message = data.message();
        this.userR = user;
        this.topic = topic;
        this.creationDate = LocalDateTime.now();
    }

    public void update(DataResponseUpdate data) {
        if (data.message() != null) this.message = data.message();
    }
}
