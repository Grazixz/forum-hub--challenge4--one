package br.com.one.forum_hub.DTO;

import br.com.one.forum_hub.model.Topic;

import java.time.LocalDateTime;

public record DataTopicDetailed(
        Long id,
        String title,
        String message,
        Long idUser,
        String author,
        Long idCourse,
        String course,
        LocalDateTime creationDate,
        boolean resolved) {

    public DataTopicDetailed(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getUserT().getId(), topic.getUserT().getNickname(), topic.getCourse().getId(), topic.getCourse().getTitle(), topic.getCreationDate(), topic.isResolved());
    }
}
